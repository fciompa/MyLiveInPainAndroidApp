package cz.ictsystem.mypaindiary.domain.entity

import cz.ictsystem.mypaindiary.repository.DescriptionRep


data class Description(
    val id: Int = 0,
    val value: String = ""
) {
    constructor(d: DescriptionRep) : this(
        d.id,
        d.value
    )

    fun getRep() = DescriptionRep(id, value)
}
