package com.github.cesar1287.exercicioandroid1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.github.cesar1287.exercicioandroid1.model.RegisterAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val tabLayout by lazy {
        findViewById<TabLayout>(R.id.tabLayout)
    }
    private val viewpager by lazy {
        findViewById<ViewPager>(R.id.viewPager)
    }
    private lateinit var viewModel: MainViewModel
    private val socioFragment = SocioFragment()
    private val naosocioFragment = NaoSocioFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initComponents()
    }

    private fun initComponents() {

        viewpager.adapter = RegisterAdapter(listOf(
            getString(R.string.nao_socio),
            getString(R.string.socio),

        ), listOf(
            naosocioFragment,
            socioFragment,

        ),supportFragmentManager)

        tabLayout.setupWithViewPager(viewpager)
        viewModel.onDataSaved.observe(this, Observer {
            if (it == true) {
                tabLayout.getTabAt(1)?.select()
            }
        })
    }
}
