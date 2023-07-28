package com.example.contactsonfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsonfirebase.databinding.MainActivityContactsListItemBinding

class MainActivityRecyclerAdapter(
    private val context: Context,
    private val arrayContacts: ArrayList<ContactModel>
) : RecyclerView.Adapter<MainActivityRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MainActivityContactsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayContacts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentContact = arrayContacts[position]
        holder.bind(currentContact, context)
    }

    class MyViewHolder(private val binding: MainActivityContactsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentContact: ContactModel, context: Context) {
            with(binding) {
                textViewFirstName.text = currentContact.firstName
                textViewLastName.text = " ${currentContact.lastName}"
                textViewPhoneNumber.text = currentContact.phoneNumber
                textViewEmail.text = currentContact.email
            }
        }
    }
}