package cz.ciompa.frantisek.mylifeinpain.entry

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class LocationListView : DialogFragment(), DialogInterface.OnClickListener {
    private lateinit var items: Array<String>

    private lateinit var viewModel: EntryViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        viewModel = arguments?.get(ARG) as EntryViewModel

        val builder = AlertDialog.Builder(requireActivity())
        items = arrayOf("aa", "bb", "cc")
        builder.setTitle("Location list")
        builder.setItems(items, this)
        return builder.create()

    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        viewModel.location = items[which]
    }

    companion object {

        const val ARG = "EntryViewModel"

        fun newInstance(viewModel: EntryViewModel): LocationListView {
            val bundle = Bundle()
            bundle.putSerializable(ARG, viewModel)

            val frg = LocationListView()
            frg.arguments = bundle

            return frg
        }
    }
}