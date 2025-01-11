package com.phuhm.basemodule.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.phuhm.basemodule.R
import com.phuhm.basemodule.adpater.TodoAdapter
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityCallApiBinding
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setStatusBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.viewmodel.CallApiViewModel
import kotlinx.coroutines.launch

class CallApiActivity : BaseActivity<ActivityCallApiBinding>() {
    private val callApiViewModel: CallApiViewModel by viewModels { CallApiViewModel.Factory }
    private var todoAdapter: TodoAdapter? = null

    override fun inflateBinding(inflater: LayoutInflater): ActivityCallApiBinding {
        return ActivityCallApiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
        observeViewModels()
    }

    private fun initViews() {
        initToolbar()
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
        initRecyclerViewTodos()
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_call_api)
    }

    private fun initRecyclerViewTodos() {
        todoAdapter = TodoAdapter()
        binding.rcvTodos.apply {
            layoutManager = LinearLayoutManager(this@CallApiActivity)
            adapter = todoAdapter
        }
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            callApiViewModel.getTodos()
        }
    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                callApiViewModel.loading.collect {
                    binding.swipeRefreshLayout.isRefreshing = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                callApiViewModel.todos.collect {
                    todoAdapter?.submitList(it)
                }
            }
        }
    }
}