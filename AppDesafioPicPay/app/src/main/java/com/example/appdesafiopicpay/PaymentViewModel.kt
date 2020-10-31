package com.example.appdesafiopicpay

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentViewModel :ViewModel() {
    val onTextChange: MutableLiveData<Int> = MutableLiveData()

}