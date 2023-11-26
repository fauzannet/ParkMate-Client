package id.fzn.amikomparkingclient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity


class qrcode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode)
        val backButton = findViewById<Button>(R.id.back)
        val nims= intent.getStringExtra("nims")
        val nopols= intent.getStringExtra("nopols")
        val qrcodes = findViewById<ImageView>(R.id.qrcodes)
        val qrEncoder = QRGEncoder("$nims,$nopols", null, QRGContents.Type.TEXT, 500)
        qrcodes.setImageBitmap(qrEncoder.bitmap)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}