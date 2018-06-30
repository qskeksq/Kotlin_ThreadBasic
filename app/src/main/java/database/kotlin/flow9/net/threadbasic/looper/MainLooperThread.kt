package database.kotlin.flow9.net.threadbasic.looper

import android.os.Handler
import android.os.Looper
import android.os.Message

class MainLooperThread : Thread() {

    internal var mHandler: Handler? = null

    override fun run() {
        // 핸들러를 생성하기 이전에 미리 루퍼를 준비해야 합니다
        Looper.prepare()
        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val data = msg.arg1
                if (msg.what == 0) {

                } else if (msg.what == 1) {

                }
            }
        }
        // 핸들러를 생성한 후 모니터링을 시작합니다.
        Looper.loop()
    }
}
