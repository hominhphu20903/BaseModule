package com.phuhm.basemodule.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.data.model.Question
import com.phuhm.basemodule.data.model.QuestionResult
import com.phuhm.basemodule.databinding.ActivityJsonBinding
import com.phuhm.basemodule.dialog.FailedDialog
import com.phuhm.basemodule.dialog.SuccessDialog
import com.phuhm.basemodule.event.JsonEvents
import com.phuhm.basemodule.extensions.gone
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setStatusBarColor
import com.phuhm.basemodule.viewmodel.JsonViewModel
import kotlinx.coroutines.launch

class JsonActivity : BaseActivity<ActivityJsonBinding>() {
    private val jsonViewModel: JsonViewModel by viewModels { JsonViewModel.Factory }

    override fun inflateBinding(inflater: LayoutInflater): ActivityJsonBinding {
        return ActivityJsonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observeViewModels()
        handleEvents()
    }

    private fun initViews() {
        initToolbar()
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_json)
    }


    private fun observeViewModels() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jsonViewModel.question.collect { question ->
                    if(question == null) return@collect
                    initViewQuestion(question)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jsonViewModel.core.collect { core ->
                    initCore(core)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jsonViewModel.questionResult.collect { result ->
                    when(result) {
                        is QuestionResult.Success -> {
                            showSuccessDialog()
                        }

                        is QuestionResult.Failed -> {
                            showFailedDialog()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                jsonViewModel.isCompleted.collect { isCompleted ->
                    if(isCompleted) {
                        onCompletedQuestion()
                    }
                }
            }
        }
    }

    private fun initViewQuestion(question: Question) {
        clearSelectedButtons()
        binding.tvQuestion.text = question.question

        if(question.options.size == 2) {
            binding.btnOptionIndex0.text = question.options[0]
            binding.btnOptionIndex1.text = question.options[1]
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initCore(core: Int) {
        binding.tvCore.text = "$core${getString(R.string.txt_max_core)}"
    }

    private fun showSuccessDialog() {
        val dialog = SuccessDialog(this@JsonActivity)
        dialog.setOnDismissListener {
            jsonViewModel.onEvent(JsonEvents.OnNextQuestion)
        }
        dialog.show()
    }

    private fun showFailedDialog() {
        val dialog = FailedDialog(this@JsonActivity)
        dialog.setOnDismissListener {
            jsonViewModel.onEvent(JsonEvents.OnNextQuestion)
        }
        dialog.show()
    }

    private fun onCompletedQuestion() {
        binding.tvQuestion.text = getString(R.string.txt_you_are_done)
        binding.btnOptionIndex0.gone()
        binding.btnOptionIndex1.gone()
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.btnOptionIndex0.setOnSingleClickListener {
            clearSelectedButtons()
            binding.btnOptionIndex0.isSelected = true
            jsonViewModel.onEvent(JsonEvents.OnSubmitQuestion(0))
        }

        binding.btnOptionIndex1.setOnSingleClickListener {
            clearSelectedButtons()
            binding.btnOptionIndex1.isSelected = true
            jsonViewModel.onEvent(JsonEvents.OnSubmitQuestion(1))
        }
    }

    private fun clearSelectedButtons() {
        binding.btnOptionIndex0.isSelected = false
        binding.btnOptionIndex1.isSelected = false
    }
}