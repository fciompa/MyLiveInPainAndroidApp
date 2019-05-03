package cz.ictsystem.mypaindiary.domain

import androidx.lifecycle.LiveData

interface Properties {
    fun isNewInstallation(): LiveData<Boolean>
    fun isNewInstallationValue(): Boolean
    fun setNewInstallation(firstQuery: Boolean)
}