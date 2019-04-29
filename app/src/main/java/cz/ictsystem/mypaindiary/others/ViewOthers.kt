package cz.ictsystem.mypaindiary.others


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cz.ictsystem.mypaindiary.R
import cz.ictsystem.mypaindiary.databinding.ViewOthersBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class ViewOthers : Fragment() {

    lateinit var binding: ViewOthersBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewOthersBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonDescriptionList.setOnClickListener {
            navController.navigate(R.id.action_nav_others_to_nav_description_list)
        }

        binding.buttonLocationList.setOnClickListener {
            navController.navigate(R.id.action_nav_others_to_nav_location_list)
        }

    }

}
