package cz.ciompa.frantisek.mylifeinpain.entries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.ciompa.frantisek.mylifeinpain.R
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry

class EntriesAdapter internal constructor(context: Context) : RecyclerView.Adapter<EntriesAdapter.EntryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entries = emptyList<Entry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = inflater.inflate(R.layout.view_entries_item, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val current = entries[position]
        holder.wordItemView.text = current.description
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    internal fun setEntities(entries: List<Entry>) {
        this.entries = entries
        notifyDataSetChanged()
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView4456)
    }

}