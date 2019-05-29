package cz.ciompa.frantisek.mylifeinpain.repository

import cz.ciompa.frantisek.mylifeinpain.storage.DescriptionEntity

data class DescriptionRep(
    val id: Int = 0,
    val value: String = ""
) {
    constructor(d: DescriptionEntity) : this(
        d.id,
        d.value
    )

    fun getEntity() = DescriptionEntity(id, value)
}
