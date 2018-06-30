package database.kotlin.flow9.net.threadbasic.looper

import android.os.Handler
import android.os.HandlerThread

class MainLooperThread2(name: String, handler: Handler) : HandlerThread(name)
