package com.example.aai.activty

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.aai.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class CctvActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cctv)
        setTitle("CCTV Applications")
        update_data()

    }
    private fun update_data()
    {

        val peopleref = FirebaseDatabase.getInstance().getReference("People")


        Log.e("TAG", "IS $peopleref")

        peopleref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {


                val people = p0.getValue(String::class.java)

                Log.e("People NO. ", "People $people")
                val tv_people: TextView = findViewById(R.id.people_counter_tv) as TextView

                tv_people.setText("" + people)


//                val anyChartView = findViewById<AnyChartView>(R.id.chart_parking)



                Toast.makeText(applicationContext,"People entered are $people", Toast.LENGTH_SHORT).show()
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