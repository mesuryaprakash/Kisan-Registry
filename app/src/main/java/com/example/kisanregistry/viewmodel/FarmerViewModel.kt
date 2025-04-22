package com.example.kisanregistry.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kisanregistry.data.model.farmer
import com.example.kisanregistry.data.dao.FarmerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FarmerViewModel(private val farmerDao: FarmerDao) : ViewModel() {

    val allFarmers: LiveData<List<farmer>> = farmerDao.getAllFarmers()

    fun insertFarmer(farmer: farmer) {
        viewModelScope.launch(Dispatchers.IO) {
            farmerDao.insertFarmer(farmer)
        }
    }

    fun deleteFarmer(farmer: farmer) {
        viewModelScope.launch(Dispatchers.IO) {
            farmerDao.deleteFarmer(farmer)
        }
    }

}