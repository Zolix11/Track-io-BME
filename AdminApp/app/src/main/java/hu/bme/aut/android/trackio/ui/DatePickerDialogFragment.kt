package hu.bme.aut.android.trackio.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import java.io.Serializable
import java.time.LocalDate
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dp =  DatePickerDialog(requireContext(), this, year, month, day)
        dp.datePicker.minDate=c.timeInMillis+86400000 //1nap millisecben
        return dp
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val result = DatePickerResult(year, month, dayOfMonth)
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle
            ?.set(AddChallengeFragment.DATE_SELECTED_KEY, result)
    }

    data class DatePickerResult(
        val year: Int,
        val month: Int,
        val dayOfMonth: Int,
    ) : Serializable


}

