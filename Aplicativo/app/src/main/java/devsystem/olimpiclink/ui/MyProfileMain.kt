package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater

import androidx.constraintlayout.widget.ConstraintLayout
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.EventPublishedMiniBinding
import devsystem.olimpiclink.databinding.MyProfileMainBinding

class MyProfileMain @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    val binding = MyProfileMainBinding.inflate(LayoutInflater.from(context), this, true)

    init{
        setLayout(attributeSet)
    }
    private fun setLayout(attributeSet: AttributeSet?){
        attributeSet?.let { attrs ->
            val attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.MyProfileMainBinding
            )
            attributes.recycle()
        }
    }
}
