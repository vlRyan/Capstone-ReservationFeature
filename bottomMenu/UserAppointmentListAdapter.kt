package com.example.capstone.bottomMenu

// UserAppointmentListAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R

class UserAppointmentListAdapter(private val appointmentList: List<UserAppointmentData>) :
    RecyclerView.Adapter<UserAppointmentListAdapter.UserAppointmentViewHolder>() {

    inner class UserAppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.textFirstName)
        val lastName: TextView = itemView.findViewById(R.id.textLastName)
        val purpose: TextView = itemView.findViewById(R.id.textPurpose)
        val purok: TextView = itemView.findViewById(R.id.textPurok)
        val month: TextView = itemView.findViewById(R.id.textMonth)
        val day: TextView = itemView.findViewById(R.id.textDay)
        val time: TextView = itemView.findViewById(R.id.textTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAppointmentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_appoinment_item, parent, false)
        return UserAppointmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserAppointmentViewHolder, position: Int) {
        val currentItem = appointmentList[position]

        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.purpose.text = currentItem.purpose
        holder.purok.text = currentItem.purok
        holder.month.text = currentItem.month
        holder.day.text = currentItem.day
        holder.time.text = currentItem.time
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }
}
