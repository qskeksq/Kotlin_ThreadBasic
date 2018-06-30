package database.kotlin.flow9.net.threadbasic.timout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.widget.Toast
import database.kotlin.flow9.net.threadbasic.R

class TimeoutActivity : AppCompatActivity() {

    internal var isBackPressed = false
    internal var mDialog: AlertDialog? = null

    internal var timeoutHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_DIALOG_TIMEOUT -> Toast.makeText(this@TimeoutActivity, "Dialog Timeout", Toast.LENGTH_LONG).show()
            }
        }
    }

    internal var backKeyHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_BACKKEY_TIMEOUT -> isBackPressed = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeout)
        // timeout dialog
        mDialog = AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle("Timeout Dialog")
                .setMessage("This dialog is time out test dialog")
                .setPositiveButton("OK") { dialogInterface, i ->
                    timeoutHandler.removeMessages(MESSAGE_DIALOG_TIMEOUT)
                    Toast.makeText(this@TimeoutActivity, "Timeout Canceled", Toast.LENGTH_LONG).show()
                }
                .create()
        mDialog?.show()
        timeoutHandler.sendEmptyMessageDelayed(MESSAGE_DIALOG_TIMEOUT, TIMEOUT_DIALOG_DELAY)
    }

    override fun onBackPressed() {
        if (!isBackPressed) {
            isBackPressed = true
            Toast.makeText(this, "BackKey Pressed", Toast.LENGTH_LONG).show()
            backKeyHandler.sendEmptyMessageDelayed(MESSAGE_BACKKEY_TIMEOUT, TIMEOUT_BACKKEY_DELAY)
        } else {
            backKeyHandler.removeMessages(MESSAGE_BACKKEY_TIMEOUT)
            super.onBackPressed()
        }
    }

    companion object {

        private val MESSAGE_DIALOG_TIMEOUT = 1
        private val TIMEOUT_DIALOG_DELAY: Long = 5000
        private val MESSAGE_BACKKEY_TIMEOUT = 2
        private val TIMEOUT_BACKKEY_DELAY: Long = 2000
    }

}
