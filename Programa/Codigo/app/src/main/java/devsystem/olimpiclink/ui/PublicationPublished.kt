package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.PublishedPublicationBinding

class PublicationPublished @JvmOverloads constructor (
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private var login_user : String? = null
    private var text_publication : String? = null
    val binding = PublishedPublicationBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }
    private fun setLayout(attrs: AttributeSet?){
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.PublicationPublished
            )

            login_user = attributes.getString(R.styleable.PublicationPublished_tv_login_user)
            text_publication = attributes.getString(R.styleable.PublicationPublished_tv_text_publication)
            binding.tvUsername.text = login_user
            binding.tvTextPublication.text = text_publication

            attributes.recycle()
        }
    }

    fun setTeste(login : String, text_publication : String, date_publication : String){
        binding.tvUsername.text = login
        binding.tvTextPublication.text = text_publication
        binding.tvDatePublication.text = date_publication
    }
}