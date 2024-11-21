package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import com.journeyapps.barcodescanner.CaptureActivity

class ScanQRActivity : CaptureActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_activity) // QR code scanning layout
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val qrResult = data.getStringExtra(com.google.zxing.client.android.Intents.Scan.RESULT)

            qrResult?.let {
                // Automatically send the result back to PaymentActivity
                val resultIntent = Intent()
                resultIntent.putExtra("QR_RESULT", it)
                setResult(RESULT_OK, resultIntent)
                finish() // Close ScanQRActivity and return to PaymentActivity
            }
        }
    }
}
