package cz.ictsystem.mypaindiary.others


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cz.ictsystem.mypaindiary.R
import kotlinx.android.synthetic.main.view_others.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ViewOthers : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_others, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonDescriptionList.setOnClickListener {
            it.findNavController().navigate(R.id.action_nav_others_to_nav_description_list)
        }

        buttonLocationList.setOnClickListener {
            it.findNavController().navigate(R.id.action_nav_others_to_nav_location_list)
        }

    }

}
