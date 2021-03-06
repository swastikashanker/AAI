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

        setTitle("Parking Monitoring")

        update_data()

    }
    private fun update_data()
    {
        val pie = AnyChart.pie()


        val occupiedref = FirebaseDatabase.getInstance().getReference("Occupied")


        Log.e("TAG", "IS $occupiedref")

        occupiedref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val data = ArrayList<DataEntry>()

                val occupied = p0.getValue(String::class.java)
                val occupied1 = occupied!!.toInt()
                val unoccupied = 10 - occupied1


                val unoccupied1 = unoccupied
                Log.e("Occupied", "Occupied $occupied")
                Log.e("unoccc","$unoccupied")

                data.add(ValueDataEntry("Occupied space",occupied1 as Int))
                data.add(ValueDataEntry("Unoccupied space",unoccupied1 as Int))


                // data.add(ValueDataEntry("Free space",))
                pie.data(data)


                val anyChartView = findViewById<AnyChartView>(R.id.chart_parking)

                anyChartView.setChart(pie)

                Toast.makeText(applicationContext,"Occupied space is $occupied", Toast.LENGTH_SHORT).show()
//               pie.data(data)


//                anyChartView.clear()
            }

        })
    }


    private var thread: Thread? = null

    private fun feedMultiple() {

        if (thread != null)
            thread!!.interrupt()

        val runnable = Runnable {
            //call the update function
            update_data()
        }

        thread = Thread(Runnable {
            for (i in 0..999) {

                // Don't generate garbage runnables inside the loop.
                runOnUiThread(runnable)

                try {
                    Thread.sleep(25)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        })

        thread!!.start()
    }

    override fun onPause() {
        super.onPause()

        if (thread != null) {
            thread!!.interrupt()
        }
    }
}