package cz.ciompa.frantisek.mylifeinpain.entry

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerView : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var time: Date
    private lateinit var viewModel: EntryViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        viewModel = arguments?.get(ARG) as EntryViewModel
        time = Date(viewModel.date.time)
        val hour = time.hours
        val minute = time.minutes

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        time.hours = hourOfDay
        time.minutes = minute
        viewModel.date = time
    }

    companion object {

        val ARG = "EntryViewModel"

        fun newInstance(viewModel: EntryViewModel): TimePickerView {
            val bundle = Bundle()
            bundle.putSerializable(ARG, viewModel)

            val frg = TimePickerView()
            frg.setArguments(bundle)

            return frg
        }
    }
}