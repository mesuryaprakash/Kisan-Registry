package com.example.kisanregistry.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kisanregistry.data.model.Farmer
import com.example.kisanregistry.data.dao.FarmerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FarmerViewModel(private val farmerDao: FarmerDao) : ViewModel() {

    val allFarmers: LiveData<List<Farmer>> = farmerDao.getAllFarmers()

    fun insertFarmer(farmer: Farmer) {
        viewModelScope.launch(Dispatchers.IO) {
            farmerDao.insertFarmer(farmer)
        }
    }

    fun deleteFarmer(farmer: Farmer) {
        viewModelScope.launch(Dispatchers.IO) {
            farmerDao.deleteFarmer(farmer)
        }
    }

}
