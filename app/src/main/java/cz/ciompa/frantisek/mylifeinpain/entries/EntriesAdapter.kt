package cz.ciompa.frantisek.mylifeinpain.entries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.ciompa.frantisek.mylifeinpain.BindAbleAdapter
import cz.ciompa.frantisek.mylifeinpain.R
import cz.ciompa.frantisek.mylifeinpain.databinding.ViewEntriesItemBinding
import cz.ciompa.frantisek.mylifeinpain.domain.DomainImpl
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import cz.ciompa.frantisek.mylifeinpain.entry.*
import java.util.*

class EntriesAdapter(private val context: Context, private val entryViewModelItemList: MutableMap<Int, ViewModel>) :
    ListAdapter<Entry, EntriesAdapter.EntryViewHolder>(EntryDiffCallback),
    BindAbleAdapter<Entry> {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = inflater.inflate(R.layout.view_entries_item, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {

        if (!entryViewModelItemList.contains(position)) {
            entryViewModelItemList[position] = EntryViewModel(DomainImpl.getInstance(context), getItem(position))
        }

        val entryViewModel = entryViewModelItemList[position] as EntryViewModel
        holder.binding.viewModel = entryViewModel
        holder.binding.executePendingBindings()

        entryViewModel.openDataPickerDialog.observe(context as LifecycleOwner, androidx.lifecycle.Observer {
            if (it.getContentIfNotHandled() == true) {
                val activity = context as AppCompatActivity
                DatePickerView.newInstance(entryViewModel).show(activity.supportFragmentManager, "datePicker")
            }
        })

        entryViewModel.openTimePickerDialog.observe(context as LifecycleOwner, androidx.lifecycle.Observer {
            if (it.getContentIfNotHandled() == true) {
                val activity = context as AppCompatActivity
                TimePickerView.newInstance(entryViewModel).show(activity.supportFragmentManager, "timePicker")
            }
        })

        entryViewModel.openLocationList.observe(context as LifecycleOwner, androidx.lifecycle.Observer {
            if (it.getContentIfNotHandled() == true) {
                val activity = context as AppCompatActivity
                LocationListView.newInstance(entryViewModel).show(activity.supportFragmentManager, "locationList")
            }
        })

        entryViewModel.openDescriptionList.observe(context as LifecycleOwner, androidx.lifecycle.Observer {
            if (it.getContentIfNotHandled() == true) {
                val activity = context as AppCompatActivity
                DescriptionListView.newInstance(entryViewModel).show(activity.supportFragmentManager, "descriptionList")
            }
        })
    }

    override fun setData(items: LiveData<List<Entry>>) {
        val newItems: MutableList<Entry> = arrayListOf()
        newItems.add(Entry(0, Date(), 0, "New entry", "Note of new entry"))
        newItems.addAll(items.value ?: emptyList())
        submitList(newItems)
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ViewEntriesItemBinding = DataBindingUtil.bind(itemView)!!
    }

    object EntryDiffCallback : ItemCallback<Entry>() {

        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return newItem == oldItem
        }
    }
}