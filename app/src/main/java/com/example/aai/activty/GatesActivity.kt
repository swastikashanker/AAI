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
        setTitle("Gate Monitoring")
        update_data()

    }

    private fun update_data()
    {
        val enteringref=FirebaseDatabase.getInstance().getReference("Entering")
        val leavingref=FirebaseDatabase.getInstance().getReference("Leaving")

        enteringref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val entering= p0.getValue(Long::class.java)

                Log.e("Entering","$entering")
                makeText(applicationContext,"No. of people entering are $entering", LENGTH_SHORT).show()


                leavingref.addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p1: DatabaseError) {

                    }

                    override fun onDataChange(p1: DataSnapshot) {

                        val leaving =p1.getValue(Long::class.java)
                        val data = ArrayList<DataEntry>()

                        Log.e("Leaving","$leaving")
                        makeText(applicationContext,"No. of people leaving are $leaving", LENGTH_SHORT).show()
                        val anyChartView = findViewById<AnyChartView>(R.id.chart_gates)
                        val cartesian = AnyChart.column()

                        data.add(ValueDataEntry("Entering",entering))
                        data.add(ValueDataEntry("leaving",leaving))
                        val column = cartesian.column(data)
                        column.data(data)
//                        anyChartView.clear()
                        anyChartView.setChart(cartesian)
                        data.removeAt(0)
                        data.removeAt(0)

                    }


                })


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