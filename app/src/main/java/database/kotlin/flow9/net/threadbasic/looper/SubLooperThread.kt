package database.kotlin.flow9.net.threadbasic.looper

import android.os.Handler
import android.os.Message

import java.util.Random

class SubLooperThread(internal var mHandler: Handler) : Thread() {

    override fun run() {
        super.run()
        val random = Random()
        for (i in 0..9) {
            val data = random.nextInt(10)
            val msg = Message()
            if (data % 2 == 0) {
                msg.what = 0
            } else {
                msg.what = 1
            }
            msg.arg1 = data
            mHandler.sendMessage(msg)
        }
    }

}
