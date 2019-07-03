package cz.ciompa.frantisek.mylifeinpain.entries


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cz.ciompa.frantisek.mylifeinpain.R
import cz.ciompa.frantisek.mylifeinpain.databinding.ViewEntriesBinding
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.DomainImpl

/**
 * A simple [Fragment] subclass.
 *
 */
class ViewEntries : Fragment() {

    private lateinit var bind: ViewEntriesBinding
    private lateinit var viewModelEntries: ViewModelEntries

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.view_entries, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ctx = activity?.applicationContext
        if (ctx != null) {
            val recyclerView = bind.RecyclerViewEntries
            val entriesAdapter = EntriesAdapter(ctx)
            recyclerView?.adapter = entriesAdapter
            recyclerView?.layoutManager = LinearLayoutManager(context)

            viewModelEntries = ViewModelProviders.of(this, ViewModelFactory(DomainImpl.getInstance(ctx)))
                .get(ViewModelEntries::class.java)
            viewModelEntries.entries.observe(this, Observer {
                it?.let {
                    bind.emptyList = it.size == 0
                    entriesAdapter.setEntities(it)
                }
            })
        }
    }

    class ViewModelFactory(private var domain: Domain) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            try {
                return ViewModelEntries(domain) as T
            } catch (e: java.lang.InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }

        }
    }

}
