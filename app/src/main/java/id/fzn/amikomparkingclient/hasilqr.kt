package id.fzn.amikomparkingclient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.HashMap

class hasilqr : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    private val tag: String? = "hasilqr"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasilqr)

        val bundle = intent.extras
        val hasilQR = bundle?.getString("hasil")
        val backButton = findViewById<Button>(R.id.back)
        val hasilsemua = findViewById<TextView>(R.id.hasilsemua)
        val hasil1 = findViewById<TextView>(R.id.hasil1)
        val hasil2 = findViewById<TextView>(R.id.hasil2)
        val hasil3 = findViewById<TextView>(R.id.hasil3)
        val hasil4 = findViewById<TextView>(R.id.hasil4)
        Log.d("HasilQR", "Nama : $hasilQR")
        //val decryptedText = crypt().decrypt("$hasilQR")
        //val namasplit = decryptedText.replace(".", " ")
        val mString = hasilQR!!.split(",").toTypedArray()
        val nama = mString[0]
        val npm = mString[1]
        val nopol = mString[2]
        val waktuin = mString[3]
        val waktuout = mString[4]
        hasil1.setText(mString[0])
        hasil2.setText(mString[1])
        hasil3.setText(mString[2])
        hasil4.setText(mString[3])

        val userRef = database.getReference("user")
        val npmsplit = npm.replace(".", "")

        Log.d(tag, npmsplit)
        Log.d(tag, "status false ke true")

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            userRef.child(npmsplit).child("status").setValue(true)

            Log.d(tag, "update data")
            val id = getRandomString(25)
            val dataMap: HashMap<String, Any> = HashMap()
            dataMap["nopol"] = nopol
            dataMap["waktuin"] = waktuin
            dataMap["waktuout"] = waktuout
            userRef.child(npmsplit).child("aktivitas").child("$nopol,$id").setValue(dataMap)

//            userRef.child(npmsplit).child("status").addListenerForSingleValueEvent(object: ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val booleanValue = dataSnapshot.getValue(Boolean::class.java)
//                }
//                override fun onCancelled(error: DatabaseError) {
//                    // An error occurred
//                }
//            })

        }

    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}