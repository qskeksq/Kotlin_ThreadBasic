package database.kotlin.flow9.net.threadbasic.strictmode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import database.kotlin.flow9.net.threadbasic.BuildConfig.DEBUG
import database.kotlin.flow9.net.threadbasic.R
import java.io.FileOutputStream
import java.io.IOException

class StrictModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (DEBUG) {
            val threadPolicy = StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDialog()
                    .penaltyLog()
                    .build()
            StrictMode.setThreadPolicy(threadPolicy)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strict_mode)
    }

    fun btnFile(v: View) {
        try {
            val fos = FileOutputStream("test.txt")
            val str = "strict mode test"
            fos.write(str.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: IOException) {

        }

    }
}
