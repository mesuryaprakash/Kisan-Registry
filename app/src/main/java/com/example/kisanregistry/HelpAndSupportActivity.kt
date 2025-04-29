package com.example.kisanregistry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HelpAndSupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Handle back navigation
        }

        val contactUsCard: CardView = findViewById(R.id.contactUsCard)
        contactUsCard.setOnClickListener {
            showContactDetails()
        }

        val appGuideCard: CardView = findViewById(R.id.appGuideCard)
        appGuideCard.setOnClickListener {
            // Implement logic to open App Guide section/activity
            Toast.makeText(this, "App Guide clicked", Toast.LENGTH_SHORT).show()
            // Example: startActivity(Intent(this, AppGuideActivity::class.java))
        }

        // The FAQs are now directly visible in the layout,
        // so we might not need a specific click listener for the entire FAQs card
        // unless you want to implement a "See More FAQs" or similar action.
        val faqsCard: CardView = findViewById(R.id.faqsCard)
        faqsCard.setOnClickListener {
            // Optional: Implement action when the entire FAQs card is clicked
            Toast.makeText(this, "FAQs clicked", Toast.LENGTH_SHORT).show()
            // Example: startActivity(Intent(this, AllFAQsActivity::class.java))
        }
    }

    private fun showContactDetails() {
        // You can customize how you want to display the contact details.
        // For a simple approach, you can use a dialog or update a section
        // within the HelpAndSupportActivity layout.

        val email1 = "anshu747946@gmail.com"
        val email2 = "Chaitanyaharish080@gmail.com"
        val email3 = "thesuryasingh9@gmail.com"
        val phone = "+91 1234567890"

        val message = "Contact us via email:\n$email1\n$email2\n$email3\n\nOr call us at:\n$phone"

        // Option 1: Show a simple AlertDialog
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Contact Details")
            .setMessage(message)
            .setPositiveButton("Okay", null)
            .show()

        // Option 2: Update a TextView in your layout (if you add one for contact details)
        // val contactDetailsTextView: TextView = findViewById(R.id.contactDetailsTextView)
        // contactDetailsTextView.text = message
        // contactDetailsTextView.visibility = View.VISIBLE
    }
}