package cz.ictsystem.mypaindiary.domain

interface Repository {
    fun load(): List<Pain>
}