package com.example.aai.activty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aai.R
import kotlinx.android.synthetic.main.activity_main.*

import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.aai.fragments.ParkingFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnParking.setOnClickListener {

            val parking = Intent(baseContext,ParkingActivity::class.java)
            startActivity(parking)


        }

        btnProximity.setOnClickListener{
            val proximity = Intent(baseContext,ProximityActivity::class.java)
            startActivity(proximity)

        }

        btnCctv.setOnClickListener{
            val cctv=Intent(baseContext,CctvActivity::class.java)
            startActivity(cctv)
        }


        btnGate.setOnClickListener {
            val gate = Intent(baseContext,GatesActivity::class.java)
            startActivity(gate)
        }

    }
}
