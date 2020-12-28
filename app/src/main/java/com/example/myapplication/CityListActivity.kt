package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.util.*
import kotlin.collections.List as List1

class CityListActivity : AppCompatActivity() {
    private var container: LinearLayout? = null

    internal var sidoName: String? = null
    private var requestUrl: String? = null
    internal var data: ListViewItem? = null
    internal var list: ArrayList<String>? = null
    internal var list_check = true
    internal var size = 0
    internal var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        val intent = intent
        sidoName = intent.getSerializableExtra("sidoName") as String

        title = sidoName

        val myAsyncTask = MyAsyncTask2()
        myAsyncTask.execute()

        container = findViewById<View>(R.id.layout) as LinearLayout
        context = this

        val viewBtn = findViewById<View>(R.id.viewBtn) as Button
        viewBtn.setOnClickListener {
            try {
                while (list_check) {
                    Thread.sleep(500)
                }

                val btn = arrayOfNulls<Button>(size)
                for (i in 0 .. size - 1) {
                    btn[i] = Button(context)
                    btn[i]!!.setText(list?.get(i))
                    btn[i]!!.setBackgroundResource(R.drawable.edge)
                    container!!.addView(btn[i])

                }

                for (i in 0 .. size - 1) {
                    val position = i
                    val locationName = LocationName()
                    locationName.dongName = btn[i]!!.getText() as String
                    btn[i]!!.setOnClickListener(View.OnClickListener {
                        val intent = Intent(this@CityListActivity, MainActivity::class.java)
                        intent.putExtra("locationName", locationName)
                        startActivity(intent)
                    })
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }


    }

    inner class MyAsyncTask2 : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg strings: String): String? {
            requestUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=$sidoName&pageNo=1&numOfRows=100&ServiceKey=zXaa7l1Wh2qmyhPGtB6BG2LFQ0pHiiVB56qUQukvxRYJ2Q30fpe1sR2BUyaLkZ04f2TUyi848uM43B3qkTVhtQ%3D%3D&ver=1.3"
            try {
                var b_stationName = false
                var b_size = false

                val url = URL(requestUrl)

                val connection_size = url.openConnection()
                connection_size.readTimeout = 3000
                val inputStream_size = connection_size.getInputStream()

                val factory_size = XmlPullParserFactory.newInstance()
                val parser_size = factory_size.newPullParser()
                parser_size.setInput(InputStreamReader(inputStream_size, "UTF-8"))

                var eventType = parser_size.eventType
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_TAG -> if (parser_size.name == "totalCount")
                            b_size = true

                        XmlPullParser.TEXT -> if (b_size) {
                            size = Integer.parseInt(parser_size.text)

                            b_size = false
                        }
                    }
                    eventType = parser_size.next()
                }

                val t_connection = url.openConnection()
                t_connection.readTimeout = 3000
                val t_inputStream = t_connection.getInputStream()

                val factory = XmlPullParserFactory.newInstance()
                val parser = factory.newPullParser()
                parser.setInput(InputStreamReader(t_inputStream, "UTF-8"))

                eventType = parser.eventType
                var count = 0
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_DOCUMENT -> {
                            //list = Array<String>(size, "")
                            list = ArrayList()
                        }

                        XmlPullParser.END_DOCUMENT -> {
                        }

                        XmlPullParser.END_TAG -> if (parser.name == "item" && data != null) {
                            list!!.add(data!!.name.toString())
                            //list!!.set(count, data!!.name.toString())
                            count++
                        }

                        XmlPullParser.START_TAG -> {
                            if (parser.name == "item") {
                                data = ListViewItem()
                            }
                            if (parser.name == "stationName") b_stationName = true
                        }

                        XmlPullParser.TEXT -> if (b_stationName) {
                            data!!.name = parser.text

                            b_stationName = false
                        }
                    }
                    eventType = parser.next()

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            list_check = false
            return null

        }
    }
}
