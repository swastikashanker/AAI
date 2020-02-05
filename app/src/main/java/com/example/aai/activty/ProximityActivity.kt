package com.example.aai.activty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        val dbref = FirebaseDatabase.getInstance().getReference("Proximity")

        Log.e("TAG", "IS $dbref")



        dbref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                val node = p0.getValue(Long::class.java)

                Log.e("node", "node is $node")

                Toast.makeText(applicationContext,"Proximity sensor value is $node", Toast.LENGTH_SHORT).show()
            }

        })

    }
}