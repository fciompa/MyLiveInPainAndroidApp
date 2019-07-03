package cz.ciompa.frantisek.mylifeinpain.domain

import cz.ciompa.frantisek.mylifeinpain.repository.PropertyRep
import cz.ciompa.frantisek.mylifeinpain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class PropertiesImpl(val repository: Repository) : Properties {

    init {
        runBlocking(Dispatchers.IO) {
            if (repository.property(propNameNewInstallation) == null) {
                repository.insertProperty(PropertyRep(0, propNameNewInstallation, true.toString()))
            }
        }
    }

    override fun isNewInstallation(): Boolean {
        val property = repository.property(propNameNewInstallation)
            ?: throw RuntimeException("$propNameNewInstallation has no value")
        return property.value.toBoolean()
    }

    override suspend fun setNewInstallation(newInstallation: Boolean) {
        val property = repository.property(propNameNewInstallation)
            ?: throw RuntimeException("$propNameNewInstallation has no value")
        repository.insertProperty(PropertyRep(property.id, propNameNewInstallation, property.toString()))
    }

    companion object {
        private const val propNameNewInstallation = "newInstallation"
    }

}