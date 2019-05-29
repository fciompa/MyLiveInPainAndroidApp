package cz.ciompa.frantisek.mylifeinpain.location


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.ciompa.frantisek.mylifeinpain.R

/**
 * A simple [Fragment] subclass.
 *
 */
class frgLocationList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_location_list, container, false)
    }


}
