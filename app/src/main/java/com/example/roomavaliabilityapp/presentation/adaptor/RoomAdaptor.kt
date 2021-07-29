package com.example.roomavaliabilityapp.presentation.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomavaliabilityapp.R
import com.example.roomavaliabilityapp.data.pojo.RoomObject
import java.text.SimpleDateFormat

class RoomAdaptor(private val roomList: List<RoomObject>) :
    RecyclerView.Adapter<RoomAdaptor.roomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roomViewHolder {
        val roomViewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_room,
            parent, false
        )
        return roomViewHolder(roomViewHolder)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: roomViewHolder, position: Int) {
        val currentItem = roomList[position]
        val context = holder.name.context
        val createdAt = SimpleDateFormat("dd/MM/yyyy HH:mm").format(
            SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(currentItem.createdAt)
                ?: "01/01/1990 00:00"
        )

        holder.id.text = context.getString(R.string.id, currentItem.id)
        holder.name.text = context.getString(R.string.room_name, currentItem.name)
        holder.createdAt.text = context.getString(R.string.created_at, createdAt)
        holder.maxOccupancy.text =
            context.getString(R.string.max_occupancy, currentItem.maxOccupancy.toString())
        holder.isOccupied.text =
            context.getString(R.string.is_occupied, currentItem.isOccupied.toString())
    }

    override fun getItemCount() = roomList.size

    class roomViewHolder(roomItem: View) : RecyclerView.ViewHolder(roomItem) {
        val id: TextView = roomItem.findViewById(R.id.room_id)
        val name: TextView = roomItem.findViewById(R.id.room_name)
        val createdAt: TextView = roomItem.findViewById(R.id.room_created_at)
        val maxOccupancy: TextView = roomItem.findViewById(R.id.room_max_occupancy)
        val isOccupied: TextView = roomItem.findViewById(R.id.room_is_occupied)
    }
}