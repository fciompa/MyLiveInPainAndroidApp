package cz.ciompa.frantisek.mylifeinpain

import android.view.View
import androidx.databinding.BindingAdapter

class BindingAdapters {
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}