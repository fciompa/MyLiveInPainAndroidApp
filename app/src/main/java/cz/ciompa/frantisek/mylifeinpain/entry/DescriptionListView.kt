package cz.ciompa.frantisek.mylifeinpain.entry

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DescriptionListView : DialogFragment(), DialogInterface.OnClickListener {
    private lateinit var items: Array<String>

    private lateinit var viewModel: EntryViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        viewModel = arguments?.get(ARG) as EntryViewModel

        val builder = AlertDialog.Builder(requireActivity())
        items = arrayOf("11", "22", "33")
        builder.setTitle("Description list")
        builder.setItems(items, this)
        return builder.create()

    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        viewModel.description = items[which]
    }

    companion object {

        const val ARG = "EntryViewModel"

        fun newInstance(viewModel: EntryViewModel): DescriptionListView {
            val bundle = Bundle()
            bundle.putSerializable(ARG, viewModel)

            val frg = DescriptionListView()
            frg.arguments = bundle

            return frg
        }
    }
}