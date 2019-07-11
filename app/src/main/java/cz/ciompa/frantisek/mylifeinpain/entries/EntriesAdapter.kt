package cz.ciompa.frantisek.mylifeinpain.entries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import cz.ciompa.frantisek.mylifeinpain.BindAbleAdapter
import cz.ciompa.frantisek.mylifeinpain.R
import cz.ciompa.frantisek.mylifeinpain.databinding.ViewEntriesItemBinding
import cz.ciompa.frantisek.mylifeinpain.domain.DomainImpl
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import cz.ciompa.frantisek.mylifeinpain.entry.EntryViewModel
import java.util.*

/**
 * implement ListAdapter!!!
 * https://developer.android.com/reference/android/support/v7/recyclerview/extensions/ListAdapter.html
 */
class EntriesAdapter internal constructor(private val context: Context) :
    RecyclerView.Adapter<EntriesAdapter.EntryViewHolder>(),
    BindAbleAdapter<Entry> {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entries = emptyList<Entry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = inflater.inflate(R.layout.view_entries_item, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.binding.viewModel = EntryViewModel(DomainImpl.getInstance(context), entries[position])
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun setData(items: LiveData<List<Entry>>) {
        val newItems: MutableList<Entry> = arrayListOf()
        newItems.add(Entry(0, Date(), 0, "New entry", "Note of new entry"))
        newItems.addAll(items.value ?: emptyList())
        entries = newItems
        notifyDataSetChanged()
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ViewEntriesItemBinding = DataBindingUtil.bind(itemView)!!
    }

}