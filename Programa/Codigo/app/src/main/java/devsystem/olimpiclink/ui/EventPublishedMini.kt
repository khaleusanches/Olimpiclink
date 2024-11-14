package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding

class EventPublishedMini @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr){
    private val binding = EventPublishedMiniBinding.inflate(LayoutInflater.from(context), this, true)
    init{
        setLayout(attributeSet)
    }
    private fun setLayout(attributeSet: AttributeSet?){
        attributeSet?.let { attrs ->
            val attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.EventPublishedMini
            )
            attributes.recycle()
        }
    }
}