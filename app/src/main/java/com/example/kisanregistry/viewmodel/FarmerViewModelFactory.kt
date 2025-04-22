package com.example.kisanregistry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kisanregistry.data.dao.FarmerDao

class FarmerViewModelFactory(private val farmerDao: FarmerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FarmerViewModel::class.java)) {
            return FarmerViewModel(farmerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}