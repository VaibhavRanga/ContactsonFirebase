package com.example.contactsonfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsonfirebase.databinding.MainActivityContactsListItemBinding

class MainActivityRecyclerAdapter(
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
        holder.bind(currentContact)
    }

    class MyViewHolder(private val binding: MainActivityContactsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentContact: ContactModel) {
            binding.contact = currentContact
            with(binding) {

                //space between first name and last name is achieved by the following line of code
                textViewFirstName.text = "${currentContact.firstName} "
            }
        }
    }
}
