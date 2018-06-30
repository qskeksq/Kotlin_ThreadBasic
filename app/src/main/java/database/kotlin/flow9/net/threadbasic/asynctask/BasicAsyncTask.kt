package database.kotlin.flow9.net.threadbasic.asynctask

import android.os.AsyncTask

class BasicAsyncTask : AsyncTask<String, Int, Boolean>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg strings: String): Boolean? {
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(aBoolean: Boolean?) {
        super.onPostExecute(aBoolean)
    }

    override fun onCancelled(aBoolean: Boolean?) {
        super.onCancelled(aBoolean)
    }
}
