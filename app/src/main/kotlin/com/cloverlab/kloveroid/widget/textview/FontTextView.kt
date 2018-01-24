package com.cloverlab.kloveroid.widget.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.cloverlab.kloveroid.R

/**
 * It's able to set a font from imported resource font.
 *
 * @author  Jieyi Wu
 * @since   2018/01/24
 */
class FontTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TextView(context, attrs, defStyleAttr) {
    init {
        context.obtainStyledAttributes(attrs, R.styleable.FontTextView, defStyleAttr, 0).also {
            it.getString(R.styleable.FontTextView_textFont).let {
                typeface = Typeface.createFromAsset(context.assets, "fonts/$it")
            }
        }.recycle()
    }
}