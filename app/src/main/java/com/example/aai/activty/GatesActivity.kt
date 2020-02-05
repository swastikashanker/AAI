package com.example.aai.activty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.aai.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class GatesActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gates)

        val enteringref=FirebaseDatabase.getInstance().getReference("Entering")
        val leavingref=FirebaseDatabase.getInstance().getReference("Leaving")

        enteringref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
              val entering= p0.getValue(Long::class.java)
                val data = ArrayList<DataEntry>()
                Log.e("Entering","$entering")
                makeText(applicationContext,"No. of people entering are $entering", LENGTH_SHORT).show()
                val anyChartView = findViewById<AnyChartView>(R.id.chart_gates)
                val cartesian = AnyChart.column()
                data.add(ValueDataEntry("Entering",entering))

                val column = cartesian.column(data)
                column.data(data)

                anyChartView.setChart(cartesian)
            }


        })


        enteringref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val entering= p0.getValue(Long::class.java)
                val data = ArrayList<DataEntry>()
                Log.e("Entering","$entering")
                makeText(applicationContext,"No. of people entering are $entering", LENGTH_SHORT).show()
                val anyChartView = findViewById<AnyChartView>(R.id.chart_gates)
                val cartesian = AnyChart.column()
                data.add(ValueDataEntry("Entering",entering))

                val column = cartesian.column(data)
                column.data(data)

                anyChartView.setChart(cartesian)
            }


        })








    }
}