package cz.ciompa.frantisek.mylifeinpain.entries


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import cz.ciompa.frantisek.mylifeinpain.R
import cz.ciompa.frantisek.mylifeinpain.databinding.ViewEntriesBinding
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.DomainImpl

/**
 * inspiration for improvement:
 * - https://github.com/kozmi55/Bindable-Adapter-Example
 * - https://github.com/phunware/blog-android-clean-coding-approach
 * - https://github.com/alphamu/android-MVVM-DataBinding-RecyclerViewExample
 *
 */
class EntriesView : Fragment() {

    private lateinit var binding: ViewEntriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_entries, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.RecyclerViewEntries.adapter = EntriesAdapter(requireContext())
        binding.RecyclerViewEntries.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))

        binding.viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(DomainImpl.getInstance(requireContext()))
        ).get(EntriesViewModel::class.java)
    }

    class ViewModelFactory(private var domain: Domain) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            try {
                return EntriesViewModel(domain) as T
            } catch (e: java.lang.InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
    }
}
