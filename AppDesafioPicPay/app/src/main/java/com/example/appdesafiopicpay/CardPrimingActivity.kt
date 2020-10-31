package com.example.appdesafiopicpay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_card_priming.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blank.*

class CardPrimingActivity : AppCompatActivity() {
    private lateinit var viewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_priming)

        viewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)

        viewModel.onTextChange.observe(this, { color ->
            color?.Let{
                tvTest.setTextColor(color)
            }
        })

        CCEntryButton.setOnClickListener {
            val gotoMainActivity = Intent(this, MainActivity::class.java)
            startActivity(gotoMainActivity)
        }
        CPBackButton.setOnClickListener{
            exit
        }
    }

}

}
