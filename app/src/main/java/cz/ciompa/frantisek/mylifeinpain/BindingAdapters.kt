package cz.ciompa.frantisek.mylifeinpain

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}