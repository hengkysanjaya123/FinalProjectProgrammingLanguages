package com.example.myapplication.Http

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


fun doAsync(
    strUrl: String,
    method: String,
    myParams: String,
    token: String,
    pb: ProgressBar? = null,
    OnComplete: (result: String) -> Unit
) {

    object : AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pb?.visibility = View.VISIBLE
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            pb?.visibility = View.GONE
            OnComplete(result)
        }

        override fun doInBackground(vararg params: String?): String {
            val con = URL(strUrl)
            var sb = StringBuilder()

            try{
                with(con.openConnection() as HttpURLConnection) {
                    requestMethod = method

                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Accept", "application/json")

                    if (!token.isEmpty()) {
                        setRequestProperty("Authorization", "$token")
                    }

                    if (!myParams.isEmpty()) {
                        val wr = DataOutputStream(outputStream)
                        wr.writeBytes(myParams)
                    }

                    var reader: BufferedReader? = null

                    val responseCode = getResponseCode()
                    if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
                        reader = BufferedReader(InputStreamReader(errorStream))
                    } else {
                        reader = BufferedReader(InputStreamReader(inputStream))
                    }

                    reader.forEachLine {
                        sb.append(it)
                    }

                    reader.close()
                }
            }
            catch (ex : Exception){
                return ex.toString()
            }

            return sb.toString()
        }
    }.execute()
}
