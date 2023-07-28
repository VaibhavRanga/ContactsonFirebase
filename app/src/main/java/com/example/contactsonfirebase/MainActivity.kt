package com.example.contactsonfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsonfirebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val arrayContacts = ArrayList<ContactModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = Firebase.firestore

        firestore
            .collection("contacts")
            .get()
            .addOnSuccessListener {
                for (doc in it) {
                    val contactModel = doc.toObject(ContactModel::class.java)
                    arrayContacts.add(contactModel)
                    Log.d("TAG", "Document id: ${doc.id}, data: ${doc.data}")
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter = MainActivityRecyclerAdapter(arrayContacts)
            }
            .addOnFailureListener {
                Toast.makeText(this@MainActivity, "Data retrieval failed", Toast.LENGTH_SHORT)
                    .show()
            }

        binding.buttonSave.setOnClickListener {
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val email = binding.editTextEmail.text.toString()
            if (
                firstName.isNotBlank() ||
                lastName.isNotBlank() ||
                phoneNumber.isNotBlank() ||
                email.isNotBlank()
            ) {
                val contact = ContactModel(
                    firstName, lastName, phoneNumber, email
                )
                firestore
                    .collection("contacts")
                    .add(contact)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@MainActivity,
                            "Data uploaded successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("TAG", "Data added with id: ${it.id}")
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Data upload failed", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("TAG", "Error adding data: ${it.message}")
                    }
            }
        }
    }
}
