package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import cz.ciompa.frantisek.mylifeinpain.repository.PropertyRep
import cz.ciompa.frantisek.mylifeinpain.repository.Repository

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

    override fun setNewInstallation(newInstalationValue: Boolean) {
        repository.insertProperty(PropertyRep(0, propNameNewInstallation, newInstalationValue.toString()))
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