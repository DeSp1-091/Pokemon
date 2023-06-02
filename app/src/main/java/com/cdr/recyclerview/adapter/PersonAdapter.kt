package com.cdr.recyclerview.adapter

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cdr.recyclerview.R
import com.cdr.recyclerview.databinding.ItemPersonBinding
import com.cdr.recyclerview.model.Person


class PersonDiffUtil(
    private val oldList: List<Person>,
    private val newList: List<Person>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPerson = oldList[oldItemPosition]
        val newPerson = newList[newItemPosition]
        return oldPerson.id == newPerson.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPerson = oldList[oldItemPosition]
        val newPerson = newList[newItemPosition]
        return oldPerson == newPerson
    }
}

interface PersonActionListener {
    fun onPersonGetId(person: Person)

}

class PersonAdapter(private val personActionListener: PersonActionListener) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(), View.OnClickListener {

    var data: List<Person> = emptyList()
        set(newValue) {
            val personDiffUtil = PersonDiffUtil(field, newValue)
            val personDiffUtilResult = DiffUtil.calculateDiff(personDiffUtil)
            field = newValue
            personDiffUtilResult.dispatchUpdatesTo(this@PersonAdapter)
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)


        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position]
        val context = holder.itemView.context

        with(holder.binding) {
            holder.itemView.tag = person




            nameTextView.text = person.name


            Glide.with(context).load(person.photo)
                .circleCrop()
                .error(R.drawable.ic_person)
                .placeholder(R.drawable.ic_person).into(imageView)
        }
    }

    override fun onClick(view: View) {
        val person: Person = view.tag as Person


    }





    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)
}