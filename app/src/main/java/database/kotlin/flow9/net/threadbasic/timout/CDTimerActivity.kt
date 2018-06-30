package database.kotlin.flow9.net.threadbasic.timout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import database.kotlin.flow9.net.threadbasic.R

class CDTimerActivity : AppCompatActivity() {

    internal var mHandler = Handler()
    internal var startTime = NOT_STARTED
    internal var mCount = 20
    internal var TIME_INTERVAL = 1000
    internal var messageView: TextView? = null

    internal var countDownRunnable: Runnable = object : Runnable {
        override fun run() {
            val currentTime = SystemClock.uptimeMillis()

            if (startTime == NOT_STARTED) {
                startTime = currentTime
            }

            // 지난 시간 millisecond 단위
            val interval = (currentTime - startTime).toInt()
            // 초 단위로 환산
            val delayCount = interval / TIME_INTERVAL
            // 만약 1000 millisecond 보다 더 지났으면 TIME_INTERVAL 인 1000으로 나눈 나머지 값 만큼 빨리 다음 runnable 을 호출해 줘야 한다.
            val rest = TIME_INTERVAL - interval % TIME_INTERVAL

            if (mCount - delayCount > 0) {
                messageView?.text = "count down : " + (mCount - delayCount)
                mHandler.postDelayed(this, rest.toLong())
            } else {
                messageView?.text = "countdown completed"
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdtimer)
        messageView = findViewById(R.id.messageView)
        val button = findViewById(R.id.btn_start)
        button.setOnClickListener(View.OnClickListener {
            mHandler.removeCallbacks(countDownRunnable)
            mCount = 20
            startTime = NOT_STARTED
            mHandler.post(countDownRunnable)
        })

    }

    companion object {
        private val NOT_STARTED = -1L
    }

}


