package cz.ciompa.frantisek.mylifeinpain

import androidx.lifecycle.LiveData

interface BindAbleAdapter<T> {
    fun setData(items: LiveData<List<T>>)
}