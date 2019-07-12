package cz.ciompa.frantisek.mylifeinpain.entry

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerView : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var date: Date
    private lateinit var viewModel: EntryViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        viewModel = arguments?.get(ARG) as EntryViewModel
        date = Date(viewModel.date.time)
        val year = date.year + 1900
        val month = date.month
        val dayOfMonth = date.date

        return DatePickerDialog(activity, this, year, month, dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date.year = year - 1900
        date.month = month
        date.date = dayOfMonth
        viewModel.date = date
    }

    companion object {

        const val ARG = "EntryViewModel"

        fun newInstance(viewModel: EntryViewModel): DatePickerView {
            val bundle = Bundle()
            bundle.putSerializable(ARG, viewModel)

            val frg = DatePickerView()
            frg.arguments = bundle

            return frg
        }
    }
}