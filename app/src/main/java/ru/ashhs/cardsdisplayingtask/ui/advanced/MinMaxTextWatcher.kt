package ru.ashhs.cardsdisplayingtask.ui.advanced

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by AleksanderSh on 10.10.2017.
 *
 * TextWatcher realization for numeric-type of EditText view. Execute appropriate functions if
 * new number is in/out of bounds of given range.
 */
class MinMaxTextWatcher(private val min: Int, private val max: Int,
                        private val outOfBoundsAction: () -> Unit,
                        private val inBoundsAction: () -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val number: Int? = s.toString().toIntOrNull()

        if (number == null || number < min || max < number) {
            outOfBoundsAction()
        } else {
            inBoundsAction()
        }
    }
}