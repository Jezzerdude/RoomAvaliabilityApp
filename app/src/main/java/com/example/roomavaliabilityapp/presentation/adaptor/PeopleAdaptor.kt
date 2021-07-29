package com.example.roomavaliabilityapp.presentation.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomavaliabilityapp.R
import com.example.roomavaliabilityapp.data.pojo.PeopleObject

class PeopleAdaptor(private val peopleList: List<PeopleObject>) :
    RecyclerView.Adapter<PeopleAdaptor.peopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): peopleViewHolder {
        val peopleViewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_people,
            parent, false
        )
        return peopleViewHolder(peopleViewHolder)
    }

    override fun onBindViewHolder(holder: peopleViewHolder, position: Int) {
        val currentItem = peopleList[position]
        val context = holder.firstname.context

        Glide.with(context)
            .load(currentItem.avatar)
            .into(holder.avatar)

        holder.id.text = context.getString(R.string.id, currentItem.id.toString())
        holder.firstname.text =
            context.getString(R.string.person_name, currentItem.firstName, currentItem.lastName)
        holder.jobTitle.text = context.getString(R.string.job_title, currentItem.jobTitle)
        holder.email.text = context.getString(R.string.email, currentItem.email)
        holder.phone.text = context.getString(R.string.phone, currentItem.phone)
    }

    override fun getItemCount() = peopleList.size

    class peopleViewHolder(peopleItem: View) : RecyclerView.ViewHolder(peopleItem) {
        val avatar: ImageView = peopleItem.findViewById(R.id.people_avatar)
        val id: TextView = peopleItem.findViewById(R.id.people_id)
        val firstname: TextView = peopleItem.findViewById(R.id.people_name)
        val jobTitle: TextView = peopleItem.findViewById(R.id.people_job_title)
        val email: TextView = peopleItem.findViewById(R.id.people_email)
        val phone: TextView = peopleItem.findViewById(R.id.people_phone)
    }
}