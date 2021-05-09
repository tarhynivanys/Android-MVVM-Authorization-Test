package mt.think.testapplication.utils

import androidx.core.widget.addTextChangedListener
import mt.think.testapplication.model.TextInputLayoutToEditText

fun initViewsToRemoveErrorsAfterTextChanged(inputViewList: ArrayList<TextInputLayoutToEditText>) {
    inputViewList.forEach { textViewPair ->
        textViewPair.editText.addTextChangedListener {
            textViewPair.textInputLayout.error = null
        }
    }
}