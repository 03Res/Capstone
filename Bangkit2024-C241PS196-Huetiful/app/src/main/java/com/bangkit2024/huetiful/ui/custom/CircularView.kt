package com.bangkit2024.huetiful.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.bangkit2024.huetiful.R

class CircularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    private val mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val mCanvas = Canvas(mBitmap)
    private val mPaint = Paint()

    private val halfOfWidth = (mBitmap.width/2).toFloat()
    private val halfOfHeight = (mBitmap.height/2).toFloat()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = ContextCompat.getColor(context, R.color.red_700)
        mCanvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 10F, 50F, mPaint)
    }
}