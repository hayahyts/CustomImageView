package com.hayahyts.customimageview2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.custom_image.view.*

class CustomImage : FrameLayout {
    private var mCornerRadius: Int = 20
    private var mBorderColor: Int = Color.RED
    var mBorderWidth: Int = 10
    private var mDrawable: Drawable? = null
    var mBackgroundColor: Int = Color.GRAY

    // Constructors
    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        setupViews()
        applyXmlAttributes(attrs, defStyleAttr)
    }

    private fun setupViews() {
        LayoutInflater.from(context).inflate(R.layout.custom_image, this, true)
    }

    private fun applyXmlAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomImage, defStyleAttr, 0)
        if (a != null) {
            if (a.hasValue(R.styleable.CustomImage_android_src)) {
                val drawable = a.getDrawable(R.styleable.CustomImage_android_src)
                setImageDrawable(drawable)
            }
            if (a.hasValue(R.styleable.CustomImage_cornerRadius)) {
                val cornerRadius = a.getInt(R.styleable.CustomImage_cornerRadius, mCornerRadius)
                setCornerRadius(cornerRadius)
            }
            if (a.hasValue(R.styleable.CustomImage_mBorderWidth)) {
                val borderWidth = a.getInt(R.styleable.CustomImage_mBorderWidth, mBorderWidth)
                setBorderWidth(borderWidth)
            }
            if (a.hasValue(R.styleable.CustomImage_borderColor)) {
                val borderColor = a.getColor(R.styleable.CustomImage_borderColor, mBorderColor)
                setBorderColor(borderColor)
            }

        }
        a.recycle()
    }

    private fun redrawImage() {
        if (mDrawable != null) {
            Glide.with(context)
                    .load(mDrawable)
                    .apply(RequestOptions().transform(RoundedCorners(mCornerRadius)))
                    .into(imageView)
        }
    }

    // Public methods
    fun setImageDrawable(drawable: Drawable) {
        mDrawable = drawable
        redrawImage()
    }

    fun setCornerRadius(radius: Int) {
        mCornerRadius = radius
        redrawImage()
    }

    fun setBorderWidth(size: Int) {
        mBorderWidth = size
        redrawImage()
    }

    fun setBorderColor(@ColorInt color: Int) {
        mBorderColor = color
        redrawImage()
    }

    fun setFontSize(fontSize: Float) {
        layerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
    }

    fun setText(text: String) {
        layerText.text = text
    }

    fun alignText(direction: TextDirection) {
        when (direction) {
            TextDirection.TOP_LEFT -> alignTopLeft()
            TextDirection.TOP_RIGHT -> alignTopRight()
            else ->
                alignCenter()
        }
    }

    private fun alignCenter() {
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        layerText.layoutParams = params
    }

    private fun alignTopRight() {
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.END
        layerText.layoutParams = params
    }

    private fun alignTopLeft() {
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.START
        layerText.layoutParams = params
    }

    companion object {
        const val TAG = "CustomImage"
    }
}