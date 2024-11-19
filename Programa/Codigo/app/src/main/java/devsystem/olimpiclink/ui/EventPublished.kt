package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.EventPublishedBinding
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding

class EventPublished @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr){
    private val binding = EventPublishedBinding.inflate(LayoutInflater.from(context), this, true)
    init{
        setLayout(attributeSet)
    }
    private fun setLayout(attributeSet: AttributeSet?){
        attributeSet?.let { attrs ->
            val attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.EventPublished
            )
            attributes.recycle()
        }
    }
}