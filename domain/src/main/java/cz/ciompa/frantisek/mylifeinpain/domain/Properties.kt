package cz.ciompa.frantisek.mylifeinpain.domain

interface Properties {
    fun isNewInstallation(): Boolean
    suspend fun setNewInstallation(newInstallation: Boolean)
}