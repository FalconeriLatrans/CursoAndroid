package com.example.appdesafiopicpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    var cartoes = {
//        Visa: /^4[0-9]{12}(?:[0-9]{3})/,
//        Mastercard: /^5[1-5][0-9]{14}/,
//        Amex: /^3[47][0-9]{13}/,
//        DinersClub: /^3(?:0[0-5]|[68][0-9])[0-9]{11}/,
//        Discover: /^6(?:011|5[0-9]{2})[0-9]{12}/,
//        JCB: /^(?:2131|1800|35\d{3})\d{11}/
//    };
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputCardNumber.addTextChangedListener(MaskWatcher(inputCardNumber,"#### #### #### ####"))//ALT + ENTER
        //inputCardName.addTextChangedListener(MaskWatcher(inputCardName,"#### #### #### ####"))
        inputCardExpDate.addTextChangedListener(MaskWatcher(inputCardExpDate,"##/##"))
        //inputCardCVV.addTextChangedListener(MaskWatcher(inputCardCVV,"#### #### #### ####"))

        CCConfirmButton.visibility = View.VISIBLE
        CCConfirmButton.visibility = View.GONE

//ADICIONAR FRAGMENTS
//    val ft = supportFragmentManager.beginTransaction()
//    ft.replace(R.id.container, PaymentFragment())
//    ft.commit()

    }
}-