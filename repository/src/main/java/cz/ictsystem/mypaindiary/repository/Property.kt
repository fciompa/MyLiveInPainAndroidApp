package cz.ictsystem.mypaindiary.repository

import cz.ictsystem.mypaindiary.database.PropertyEntity

data class Property(
    val id: Int = 0,
    val name: String = "",
    val value: String = ""
) {
    constructor(p: PropertyEntity) : this(
        p.id,
        p.name,
        p.value
    )

    fun getEntity() = PropertyEntity(id, name, value)

}