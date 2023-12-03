package com.example.capstone.bottomMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R

class AppointmentsAdapter : RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder>() {

    private var appointmentsList: List<Appointment> = emptyList()

    fun setAppointments(appointments: List<Appointment>) {
        appointmentsList = appointments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.bind(appointment)
    }

    override fun getItemCount(): Int = appointmentsList.size

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val purposeTextView: TextView = itemView.findViewById(R.id.purposeTextView)

        fun bind(appointment: Appointment) {
            val fullName = "${appointment.firstName} ${appointment.lastName}"
            nameTextView.text = fullName
            purposeTextView.text = appointment.purpose
        }
    }
}