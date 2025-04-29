package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kisanregistry.databinding.ActivityUserProfileBinding

class UserProfile : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserProfileBinding

    private lateinit var layoutViewMode: LinearLayout
    private lateinit var layoutEditMode: LinearLayout
    private lateinit var btnSave: Button
    private lateinit var editProfile: ImageButton

    // EditText fields
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editLocation: EditText
    private lateinit var editVillage: EditText
    private lateinit var editAadhar: EditText
    private lateinit var editArea: EditText


    // View-only TextViews (optional, for dynamic updates)
    private lateinit var txtPhone: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtLocation: TextView
    private lateinit var txtVillage: TextView
    private lateinit var txtAadhar: TextView
    private lateinit var txtArea: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val backbtn: ImageView = findViewById(R.id.icon_menu)
        backbtn.setOnClickListener {
            val backIntent = Intent(this, HomeDashboard::class.java)
            startActivity(backIntent)
        }

        layoutViewMode = findViewById(R.id.layoutViewMode)
        layoutEditMode = findViewById(R.id.layoutEditMode)
        editProfile = findViewById(R.id.btnEdit)
        btnSave = findViewById(R.id.btnSave)

        editName = findViewById(R.id.et_name)
        editEmail = findViewById(R.id.et_email)
        editLocation = findViewById(R.id.et_address)
        editVillage = findViewById(R.id.et_village)
        editAadhar = findViewById(R.id.et_verification)
        editArea = findViewById(R.id.et_landsize)

//        txtEmail = layoutViewMode.findViewWithTag("txtEmail")
//        txtLocation = layoutViewMode.findViewWithTag("txtLocation")
//        txtVillage = layoutViewMode.findViewWithTag("txtVillage")
//        txtAadhar = layoutViewMode.findViewWithTag("txtAadhar")
//        txtArea = layoutViewMode.findViewWithTag("txtArea")

        editProfile.setOnClickListener {
            // Switch to edit mode
            layoutViewMode.visibility = View.GONE
            layoutEditMode.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {
            // Optional: Update TextViews if dynamically set
            txtEmail.text = editEmail.text.toString()
            txtLocation.text = editLocation.text.toString()
            txtVillage.text = editVillage.text.toString()
            txtAadhar.text = editAadhar.text.toString()
            txtArea.text = editArea.text.toString()

            // Switch back to view mode
            layoutEditMode.visibility = View.GONE
            layoutViewMode.visibility = View.VISIBLE

            Toast.makeText(this, "Changes Saved!", Toast.LENGTH_SHORT).show()
        }



    }

}