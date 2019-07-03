package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import cz.ciompa.frantisek.mylifeinpain.repository.PropertyRep
import cz.ciompa.frantisek.mylifeinpain.repository.Repository

class PropertiesImpl(val repository: Repository) : Properties {

    override fun isNewInstallation(): LiveData<Boolean> {
        return Transformations.map(repository.property(propNameNewInstallation)) {
            it?.value?.toBoolean() ?: true
        }
    }

    override fun isNewInstallationValue(): Boolean {

        val properties = repository.property(propNameNewInstallation)
        var value = getValueFromLiveData(properties)
        return value?.value?.toBoolean() ?: true
    }

    override suspend fun setNewInstallation(newInstallation: Boolean) {

        var value = getValueFromLiveData(repository.property(propNameNewInstallation))

        if (value != null) {
            value = PropertyRep(value.id, propNameNewInstallation, newInstallation.toString())
        } else {
            value = PropertyRep(0, propNameNewInstallation, newInstallation.toString())
        }

        repository.insertProperty(value)
    }

    private fun <T> getValueFromLiveData(liveData: LiveData<T>): T? {
        var value: T? = null
        var observer = Observer<T> {
            value = it
        }
        liveData.observeForever(observer)
        liveData.removeObserver(observer)
        return value
    }

    companion object {
        private const val propNameNewInstallation = "newInstallation"
    }

}