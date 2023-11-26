package id.fzn.amikomparkingclient

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.SparseArray
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var hasilplat: String
    private val MY_PERMISSIONS_REQUEST_CAMERA: Int = 100
    private lateinit var textRecognizer: TextRecognizer
    private val tag: String? = "MainActivity"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scanparkir = findViewById<Button>(R.id.parkir)

        requestForPermission()

        scanparkir.setOnClickListener {
            val intent = Intent(this, id.fzn.amikomparkingclient.scanqr::class.java)
                startActivity(intent)
    }

    }

    private fun requestForPermission() {

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.CAMERA
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.CAMERA
                )
            ) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(
                        Manifest.permission.CAMERA
                    ),
                    MY_PERMISSIONS_REQUEST_CAMERA
                )
            }
        } else {
        }
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun toast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    requestForPermission()
                }
                return
            }else -> {
        }
        }
    }

}
