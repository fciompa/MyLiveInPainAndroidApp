package cz.ictsystem.mypaindiary.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import cz.ictsystem.mypaindiary.repository.PropertyRep
import cz.ictsystem.mypaindiary.repository.Repository

class AppProperties(val repository: Repository) : Properties {

    override fun isNewInstallation(): LiveData<Boolean> {
        return Transformations.map(repository.loadProperty(propNameNewInstallation)) {
            it?.value?.toBoolean() ?: true
        }
    }

    override fun isNewInstallationValue(): Boolean {

        val properties = repository.loadProperty(Companion.propNameNewInstallation)
        var value = getValueFromLiveData(properties)
        return value?.value?.toBoolean() ?: true
    }

    override fun setNewInstallation(newInstalation: Boolean) {
        repository.insertProperty(PropertyRep(0, propNameNewInstallation, newInstalation.toString()))
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