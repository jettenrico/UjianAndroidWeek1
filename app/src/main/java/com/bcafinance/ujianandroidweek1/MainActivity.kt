package com.bcafinance.ujianandroidweek1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {
    var orientation:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(LoginFragment.newInstance("", ""))
    }

    fun loadFragment(fragment: Fragment){
        val fragManager = supportFragmentManager.beginTransaction()
        fragManager.replace(R.id.vFragment, fragment)
        fragManager.commit()
        fragManager.addToBackStack(null)
    }
}