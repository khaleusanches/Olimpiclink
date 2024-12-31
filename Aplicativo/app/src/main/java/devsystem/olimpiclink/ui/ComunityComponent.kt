package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import devsystem.olimpiclink.R

class ComunityComponent @JvmOverloads constructor(
    context : Context,
    attr : AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attr, defStyleAttr) {

    init{
        setLayout(attr)
    }
    private fun setLayout(attr: AttributeSet?){
        attr?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ComunityComponent
            )
            attributes.recycle()
        }
    }
}