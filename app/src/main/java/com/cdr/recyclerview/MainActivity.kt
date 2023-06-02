package com.cdr.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdr.recyclerview.adapter.PersonActionListener
import com.cdr.recyclerview.adapter.PersonAdapter
import com.cdr.recyclerview.databinding.ActivityMainBinding
import com.cdr.recyclerview.model.Person
import com.cdr.recyclerview.model.PersonListener
import com.cdr.recyclerview.model.PersonService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val personService: PersonService
        get() = (applicationContext as App).personService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        adapter = PersonAdapter(object : PersonActionListener {
            override fun onPersonGetId(person: Person) =
                Toast.makeText(this@MainActivity, "Persons ID: ${person.id}", Toast.LENGTH_SHORT).show()
        })
        personService.addListener(listener)

        binding.recyclerView.layoutManager = manager 
        binding.recyclerView.adapter = adapter 
    }

    private val listener: PersonListener = {adapter.data = it}
}
