package com.phuhm.basemodule.customview


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.phuhm.basemodule.R

class GradientStrokeView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var startColor: Int = Color.RED
    private var endColor: Int = Color.BLUE
    private var backgroundColor: Int = Color.WHITE
    private var cornerRadius: Float = 0f
    private var strokeWidth: Float = 10f

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.GradientStrokeView,
            0, 0
        ).apply {
            try {
                startColor = getColor(R.styleable.GradientStrokeView_startColorStroke, startColor)
                endColor = getColor(R.styleable.GradientStrokeView_endColorStroke, endColor)
                backgroundColor = getColor(R.styleable.GradientStrokeView_backgroundColor, backgroundColor)
                cornerRadius = getDimension(R.styleable.GradientStrokeView_cornerRadius, cornerRadius)
                strokeWidth = getDimension(R.styleable.GradientStrokeView_strokeWidth, strokeWidth) // Đọc strokeWidth từ XML
            } finally {
                recycle()
            }
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Vẽ background
        paint.color = backgroundColor
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), cornerRadius, cornerRadius, paint)

        val gradient = LinearGradient(
            0f, 0f, width.toFloat(), 0f,
            startColor, endColor,
            Shader.TileMode.CLAMP
        )

        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        strokePaint.shader = gradient
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeWidth = strokeWidth

        val path = Path()
        val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)

        val halfStrokeWidth = strokeWidth / 2
        val adjustedRectF = RectF(halfStrokeWidth, halfStrokeWidth, width.toFloat() - halfStrokeWidth, height.toFloat() - halfStrokeWidth)
        path.reset()
        path.addRoundRect(adjustedRectF, cornerRadius, cornerRadius, Path.Direction.CW)

        // Vẽ đường viền
        canvas.drawPath(path, strokePaint)
    }
}

// Example
//<com.grownapp.flashalert.customview.GradientStrokeView
//android:id="@+id/bgUnchecked"
//android:layout_width="match_parent"
//android:layout_height="0dp"
//app:backgroundColor="@color/black_trans15"
//app:cornerRadius="@dimen/_10sdp"
//app:endColor="@android:color/transparent"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintTop_toTopOf="parent"
//app:startColor="@android:color/transparent"
//app:strokeWidth="@dimen/_1sdp" />