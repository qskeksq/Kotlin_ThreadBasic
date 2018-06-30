package database.kotlin.flow9.net.threadbasic.basic

import android.os.Handler
import android.os.Message

class BasicThreadOne internal constructor(private val mHandler: Handler) : Thread() {

    private var count = 0
    private var isRunning = true

    override fun run() {
        try {
            // count가 10이 될때까지 while문을 도는 태스크
            while (isRunning) {
                // 1초 간격으로 진행합니다
                Thread.sleep(1000)
                count++
                if (count == 10) {
                    val message = mHandler.obtainMessage()
                    message.what = 1
                    message.obj = "task completed"
                    mHandler.sendMessage(message)
                    isRunning = false
                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}