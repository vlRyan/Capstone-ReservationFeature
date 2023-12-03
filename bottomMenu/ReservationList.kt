package com.example.capstone.bottomMenu

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ReservationList : AppCompatActivity() {
    private lateinit var appointmentList: RecyclerView
    private lateinit var appointBtn: Button
    private lateinit var backBtn: ImageButton
    private lateinit var fStore: FirebaseFirestore
    private lateinit var adapter: UserAppointmentListAdapter
    private val appointments = mutableListOf<UserAppointmentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        fStore = FirebaseFirestore.getInstance()

        backBtn = findViewById(R.id.back)
        backBtn.setOnClickListener {
            finish()
        }

        appointBtn = findViewById(R.id.AppointBtn)
        appointBtn.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }

        // Initialize RecyclerView and adapter
        appointmentList = findViewById(R.id.AppointmentList)
        adapter = UserAppointmentListAdapter(appointments)
        appointmentList.layoutManager = LinearLayoutManager(this)
        appointmentList.adapter = adapter

        // Fetch user appointments and update the RecyclerView
        fetchUserAppointments()
    }

    private fun fetchUserAppointments() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userEmail = currentUser.email

            // TODO: Query Firestore for user appointments using the user's email
            fStore.collection("Appointments")
                .whereEqualTo("user_email", userEmail) // Adjust the field and condition as per your Firestore data
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val querySnapshot: QuerySnapshot? = task.result
                        if (querySnapshot != null && !querySnapshot.isEmpty) {
                            for (document in querySnapshot.documents) {
                                val data = document.data
                                if (data != null) {
                                    val firstName = data["first_name"] as String
                                    val lastName = data["last_name"] as String
                                    val purpose = data["purpose"] as String
                                    val purok = data["purok"] as String
                                    val month = data["month"] as String
                                    val day = data["day"] as String
                                    val time = data["time"] as String

                                    val userAppointment = UserAppointmentData(
                                        firstName,
                                        lastName,
                                        purpose,
                                        purok,
                                        month,
                                        day,
                                        time
                                    )
                                    appointments.add(userAppointment)
                                }
                            }
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        // Handle the error
                    }
                }
        } else {
            // User is not authenticated, handle as needed
        }
    }
}
