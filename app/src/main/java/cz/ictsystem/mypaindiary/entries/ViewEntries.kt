package cz.ictsystem.mypaindiary.entries


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.ictsystem.mypaindiary.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ViewEntries : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_entries, container, false)
    }


}
