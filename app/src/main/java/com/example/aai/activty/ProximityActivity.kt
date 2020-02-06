package com.example.aai.activty

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.aai.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProximityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)
        setTitle("Conveyor Belt Proximity")

        update_data()

    }

    private fun update_data()
    {

        val dbref = FirebaseDatabase.getInstance().getReference("Proximity")

        Log.e("TAG", "IS $dbref")



        dbref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                val node = p0.getValue(String::class.java)

                Log.e("node", "node is $node")

                val belt1: ImageView = findViewById((R.id.belt1)) as ImageView
                val belt2: ImageView = findViewById((R.id.belt2)) as ImageView
                val belt3: ImageView = findViewById((R.id.belt3)) as ImageView

                val c = node?.toInt()
                if (c == 1)
                {
                    belt1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.green_belt))
                    belt2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))
                    belt3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))
                }

                else if (c == 2)
                {
                    belt2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.green_belt))
                    belt1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))
                    belt3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))

                }
                else
                {
                    belt3.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.green_belt))
                    belt2.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))
                    belt1.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.red_belt))

                }
                Toast.makeText(applicationContext,"Current conveyor belt is $node", Toast.LENGTH_SHORT).show()
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