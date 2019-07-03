package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.lifecycle.LiveData

interface Properties {
    fun isNewInstallation(): LiveData<Boolean>
    fun isNewInstallationValue(): Boolean
    suspend fun setNewInstallation(newInstallation: Boolean)
}