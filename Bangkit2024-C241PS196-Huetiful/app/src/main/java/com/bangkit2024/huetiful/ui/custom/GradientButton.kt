package com.bangkit2024.huetiful.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.ui.custom.GradientButton

class GradientButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatButton(context, attrs) {

        init {
            val gradientShader = LinearGradient(
                0f, 0f,  // start point
                width.toFloat(), 0f,  // end point
                intArrayOf(Color.RED, Color.BLUE),  // colors
                floatArrayOf(0f, 1f),  // positions
                Shader.TileMode.CLAMP
            )

            val paint = Paint()
            paint.shader = gradientShader

            background = paint as Drawable
        }

}
