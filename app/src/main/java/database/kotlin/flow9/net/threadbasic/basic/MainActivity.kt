package database.kotlin.flow9.net.threadbasic.basic

import android.bluetooth.BluetoothA2dp
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import database.kotlin.flow9.net.threadbasic.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val mHandler = MyHandler()
    // thread pool member
    private var mThreadPool: ExecutorService? = null
    private val CORE_POOL_SIZE = 5
    private val MAXIMUM_POOL_SIZE = 64
    private val KEEP_ALIVE_TIME = 0L

    var thread1: BasicThreadOne? = null
    var thread2: Thread? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Basic Thread
        thread1 = BasicThreadOne(mHandler)
        thread1!!.start()
        thread2 = Thread(BasicThreadTwo(mHandler))
        thread2!!.start()

        // send message over handler
        update.setOnClickListener(View.OnClickListener {
            mHandler.sendEmptyMessage(0)
            mHandler.sendEmptyMessageAtTime(0, System.currentTimeMillis() + 1000)
            mHandler.sendEmptyMessageDelayed(0, 1000)

            mHandler.sendMessage(Message.obtain())
            mHandler.sendMessageAtTime(Message.obtain(), System.currentTimeMillis() + 1000)
            mHandler.sendMessageAtFrontOfQueue(Message.obtain())
            mHandler.sendMessageDelayed(Message.obtain(), 1000)

            mHandler.post(BasicThreadTwo(mHandler))
            mHandler.post { }
        })

        // ThreadPool
        mThreadPool = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                LinkedBlockingQueue()
        )
    }

    public override fun onPause() {
        super.onPause()
        finishBackgroundThread()
    }

    private fun finishBackgroundThread() {
        thread1!!.interrupt()
        thread1 = null
        thread2!!.interrupt()
        thread2 = null
    }

    private fun executeThreadPool() {
        mThreadPool!!.execute { }
    }

    private fun shutdownThreadPool() {
        mThreadPool!!.shutdown()
    }

    internal class MyHandler : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {

            } else if (msg.what == 2) {

            }
        }
    }
}
