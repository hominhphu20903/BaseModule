package com.phuhm.basemodule.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.phuhm.basemodule.R;

@SuppressLint("AppCompatCustomView")
public class GradientTextView extends TextView {

    private boolean isVertical;
    private int startColor = Color.BLUE;
    private int endColor = Color.GREEN;

    public GradientTextView(Context context) {
        super(context);
        init(context, null);

    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);
            isVertical = attributes.getBoolean(R.styleable.GradientTextView_isVertical, false);
            startColor = attributes.getColor(R.styleable.GradientTextView_endColor, startColor);
            endColor = attributes.getColor(R.styleable.GradientTextView_startColor, endColor);
            attributes.recycle();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            Paint paint = getPaint();
            float width = paint.measureText(getText().toString());

            if (isVertical) {
                getPaint().setShader(new LinearGradient(0, 0, width, getLineHeight(),
                        endColor,
                        startColor,
                        Shader.TileMode.CLAMP));
            } else {
                getPaint().setShader(new LinearGradient(0, 0, 0, getLineHeight(),
                        endColor,
                        startColor,
                        Shader.TileMode.CLAMP));
            }
        }
    }
}

// Example
//<GradientTextView
//android:id="@+id/tvSplashTitle"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="12dp"
//android:alpha="0"
//android:fontFamily="@font/myriad_pro_bold"
//android:gravity="center"
//android:text="@string/app_name"
//android:textColor="@color/colorPrimaryApp"
//android:textSize="@dimen/_23sdp"
//app:endColor="#29B6F6"
//app:isVertical="true"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@id/gl_horizontal_50"
//app:startColor="#1A3BCF"
//tools:alpha="1" />
