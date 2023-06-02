package com.cdr.recyclerview.model

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

typealias PersonListener = (persons: List<Person>) -> Unit

class PersonService {

    private var persons = mutableListOf<Person>()

    private var listeners = mutableListOf<PersonListener>()

    init {
        val faker = Faker.instance()

        persons = (1..50).map {
            Person(
                id = it.toLong(),
                name = faker.pokemon().name(),
                photo = IMAGES[it % IMAGES.size],
            )
        }.toMutableList()
    }

    fun getPersons(): List<Person> = persons
    fun addListener(listener: PersonListener) {
        listeners.add(listener)
        listener.invoke(persons)
    }
    fun removeListener(listener: PersonListener) {
        listeners.remove(listener)
        listener.invoke(persons)
    }
    private fun notifyChanges() = listeners.forEach { it.invoke(persons) }

    companion object {
        private val IMAGES = mutableListOf(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/008.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png"
        )
    }
}

data class Person(
    val id: Long,
    val name: String,
    val photo: String,

)