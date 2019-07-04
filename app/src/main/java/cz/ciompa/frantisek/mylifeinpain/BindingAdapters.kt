package cz.ciompa.frantisek.mylifeinpain

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("data")
    @JvmStatic
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: LiveData<List<T>>) {
        if (recyclerView.adapter is BindAbleAdapter<*>) {
            items.observe(recyclerView.context as AppCompatActivity, Observer {
                (recyclerView.adapter as BindAbleAdapter<T>).setData(items)
            })
        }
    }
}