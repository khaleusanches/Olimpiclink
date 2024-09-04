package devsystem.olimpiclink.util

import android.annotation.SuppressLint
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import devsystem.olimpiclink.R

class CommonEvents {
    @SuppressLint("ClickableViewAccessibility")
    fun touchListenerGet(button : AppCompatButton) : View.OnTouchListener{
        return View.OnTouchListener{_, motionEvent ->
            when (motionEvent.action) {
                ACTION_DOWN -> {
                    button.setBackgroundResource(R.drawable.edit_text_selected)
                    button.setTextColor(button.context.getColor(R.color.laranja_splash))
                }
                ACTION_UP, ACTION_CANCEL -> {
                    button.setBackgroundResource(R.drawable.buttons_initial)
                    button.setTextColor(button.context.getColor(R.color.white))
                }
            }
            false
        }
    }

    fun focusChangedListenerGet(edit_text : EditText) : View.OnFocusChangeListener{
        return View.OnFocusChangeListener{ _, hasFocus ->
            if(hasFocus){
                edit_text.setBackgroundResource(R.drawable.edit_text_selected)
            }
            else{
                edit_text.setBackgroundResource(R.drawable.buttons_initial)
            }
            false
        }
    }
}