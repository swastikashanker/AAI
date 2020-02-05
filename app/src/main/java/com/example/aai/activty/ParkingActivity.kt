package com.example.aai.activty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
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

class ParkingActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        val pie = AnyChart.pie()


        val occupiedref = FirebaseDatabase.getInstance().getReference("Occupied")


        Log.e("TAG", "IS $occupiedref")




        occupiedref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val data = ArrayList<DataEntry>()

                val occupied = p0.getValue(Long::class.java)

                Log.e("Occupied", "Occupied $occupied")
                data.add(ValueDataEntry("Occupied space",occupied))
                data.add(ValueDataEntry("Free space",))
                pie.data(data)

                val anyChartView = findViewById<AnyChartView>(R.id.chart_parking)
                anyChartView.setChart(pie)
                Toast.makeText(applicationContext,"Occupied space is $occupied", Toast.LENGTH_SHORT).show()
            }

        })







//        val data = ArrayList<DataEntry>()
//
//        data.add(ValueDataEntry("Free Space", 12000))
//
//
//        pie.data(data)
//
//        val anyChartView = findViewById<AnyChartView>(R.id.any_chart_view)
//        anyChartView.setChart(pie)

    }
}