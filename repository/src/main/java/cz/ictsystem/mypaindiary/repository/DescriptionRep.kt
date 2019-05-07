package cz.ictsystem.mypaindiary.repository

import cz.ictsystem.mypaindiary.storage.DescriptionEntity

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
