package cz.ictsystem.mypaindiary.domain

interface Repository {
    fun loadEntries(): List<Entry>
}