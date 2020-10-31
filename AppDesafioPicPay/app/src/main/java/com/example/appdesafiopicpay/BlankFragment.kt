package com.example.appdesafiopicpay

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)

        viewModel.onTextChange.observe(this, {})

        activity?.let {
            viewModel = ViewModelProvider(it).get(PaymentViewModel::class.java)
        }
        btBlue.setOnClickListener {
            viewModel.onTextChange.postValue(Color.BLUE)
        }
        btRed.setOnClickListener {
            viewModel.onTextChange.postValue(Color.RED)
        }
        btGreen.setOnClickListener {
            viewModel.onTextChange.postValue(Color.GREEN)
        }
    }
}