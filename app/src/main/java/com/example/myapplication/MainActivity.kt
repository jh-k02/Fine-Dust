package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    private var requestUrl: String? = null
    internal var list: ArrayList<Item>? = null
    internal var data: Item? = null
    internal var cityName: String? = null
    internal var locationName = LocationName()
    internal var tm: TM? = null
    var flag = false

    var dataTime: TextView? = null
    var so2Value: TextView? = null
    var coValue: TextView? = null
    var o3Value: TextView? = null
    var no2Value: TextView? = null
    var pm10Value: TextView? = null
    var pm25Value: TextView? = null
    var khaiValue: TextView? = null
    var khaiGrade: TextView? = null
    var so2Grade: TextView? = null
    var coGrade: TextView? = null
    var o3Grade: TextView? = null
    var no2Grade: TextView? = null
    var pm10Grade: TextView? = null
    var pm25Grade: TextView? = null
    var location: TextView? = null
    var colorView10: TextView? = null
    var statusImage10: ImageView? = null
    var colorView25: TextView? = null
    var statusImage25: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataTime = findViewById(R.id.date)
        so2Value = findViewById(R.id.so2value)
        coValue = findViewById(R.id.covalue)
        o3Value = findViewById(R.id.o3value)
        no2Value = findViewById(R.id.no2value)
        pm10Value = findViewById(R.id.pm10value)
        pm25Value = findViewById(R.id.pm25value)
        khaiValue = findViewById(R.id.khaivalue)
        khaiGrade = findViewById(R.id.khaigrade)
        so2Grade = findViewById(R.id.so2grade)
        coGrade = findViewById(R.id.cograde)
        o3Grade = findViewById(R.id.o3grade)
        no2Grade = findViewById(R.id.no2grade)
        pm10Grade = findViewById(R.id.pm10grade)
        pm25Grade = findViewById(R.id.pm25grade)
        location = findViewById(R.id.location)
        colorView10 = findViewById(R.id.colorview10)
        statusImage10 = findViewById(R.id.statusImage10)
        colorView25 = findViewById(R.id.colorview25)
        statusImage25 = findViewById(R.id.statusImage25)

        val intent = intent
        locationName = intent.getSerializableExtra("locationName") as LocationName

        if (locationName.flag == 0)
            title = locationName.dongName!! + " 미세먼지 보기"
        else if (locationName.flag == 1)
            title = "위치입력으로 미세먼지 보기"
        else if (locationName.flag == 2)
            title = "GPS로 미세먼지 보기"
        flag = false

        val myAsyncTask = MyAsyncTask()
        myAsyncTask.execute()

        val backBtn = findViewById<View>(R.id.backBtn) as Button
        backBtn.setOnClickListener {
            flag = false
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        }

        while(!flag) {
        }

        //flag = false
        colorView10!!.setText(data!!.colorViewText10)
        colorView25!!.setText(data!!.colorViewText25)
        location!!.setText(data!!.location)
        dataTime!!.setText(data!!.dataTime)
        so2Value!!.setText(data!!.so2Value)
        coValue!!.setText(data!!.coValue)
        o3Value!!.setText(data!!.o3Value)
        no2Value!!.setText(data!!.no2Value)
        pm10Value!!.setText(data!!.pm10Value)
        pm25Value!!.setText(data!!.pm25Value)
        khaiValue!!.setText(data!!.khaiValue)
        khaiGrade!!.setText(data!!.khaiGrade)
        so2Grade!!.setText(data!!.so2Grade)
        coGrade!!.setText(data!!.coGrade)
        o3Grade!!.setText(data!!.o3Grade)
        no2Grade!!.setText(data!!.no2Grade)
        pm10Grade!!.setText(data!!.pm10Grade)
        pm25Grade!!.setText(data!!.pm25Grade)
    }

    inner class MyAsyncTask : AsyncTask<String, Void, String>() {
        @SuppressLint("WrongThread")
        override fun doInBackground(vararg strings: String): String? {
            if (locationName.flag > 0) {
                requestUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getTMStdrCrdnt?umdName=" + locationName.dongName + "&pageNo=1&numOfRows=10&ServiceKey=zXaa7l1Wh2qmyhPGtB6BG2LFQ0pHiiVB56qUQukvxRYJ2Q30fpe1sR2BUyaLkZ04f2TUyi848uM43B3qkTVhtQ%3D%3D"
                try {
                    var b_sggName = false
                    var b_tmX = false
                    var b_tmY = false

                    val url = URL(requestUrl)

                    val t_connection = url.openConnection()
                    t_connection.readTimeout = 3000
                    val t_inputStream = t_connection.getInputStream()

                    val factory = XmlPullParserFactory.newInstance()
                    val parser = factory.newPullParser()
                    parser.setInput(InputStreamReader(t_inputStream, "UTF-8"))

                    var eventType = parser.eventType

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        when (eventType) {
                            XmlPullParser.START_DOCUMENT -> tm = TM()

                            XmlPullParser.END_DOCUMENT -> {
                            }

                            XmlPullParser.END_TAG -> {
                            }

                            XmlPullParser.START_TAG -> {
                                if (parser.name == "sggName") b_sggName = true
                                if (parser.name == "tmX") b_tmX = true
                                if (parser.name == "tmY") b_tmY = true
                            }

                            XmlPullParser.TEXT -> if (b_sggName) {
                                tm!!.sggName = parser.text
                                b_sggName = false
                            } else if (b_tmX) {
                                if (locationName.guName == tm!!.sggName)
                                    tm!!.tmX = parser.text
                                b_tmX = false
                            } else if (b_tmY) {
                                if (locationName.guName == tm!!.sggName)
                                    tm!!.tmY = parser.text
                                b_tmY = false
                            }
                        }
                        eventType = parser.next()

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                requestUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=" + tm!!.tmX + "&tmY=" + tm!!.tmY + "&ServiceKey=zXaa7l1Wh2qmyhPGtB6BG2LFQ0pHiiVB56qUQukvxRYJ2Q30fpe1sR2BUyaLkZ04f2TUyi848uM43B3qkTVhtQ%3D%3D"
                try {
                    var b_stationName = false
                    var check = true

                    val url = URL(requestUrl)

                    val t_connection = url.openConnection()
                    t_connection.readTimeout = 3000
                    val t_inputStream = t_connection.getInputStream()

                    val factory = XmlPullParserFactory.newInstance()
                    val parser = factory.newPullParser()
                    parser.setInput(InputStreamReader(t_inputStream, "UTF-8"))

                    var eventType = parser.eventType

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        when (eventType) {
                            XmlPullParser.START_DOCUMENT -> {
                            }

                            XmlPullParser.END_DOCUMENT -> {
                            }

                            XmlPullParser.END_TAG -> {
                            }

                            XmlPullParser.START_TAG -> if (parser.name == "stationName") b_stationName = true

                            XmlPullParser.TEXT -> if (b_stationName && check) {
                                cityName = parser.text
                                b_stationName = false
                                check = false
                            }
                        }
                        eventType = parser.next()

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else
                cityName = locationName.dongName

            requestUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=$cityName&dataTerm=month&pageNo=1&numOfRows=1&ServiceKey=zXaa7l1Wh2qmyhPGtB6BG2LFQ0pHiiVB56qUQukvxRYJ2Q30fpe1sR2BUyaLkZ04f2TUyi848uM43B3qkTVhtQ%3D%3D&ver=1.3"
            try {
                var b_stationName = false
                var b_dataTime = false
                var b_so2Value = false
                var b_coValue = false
                var b_o3Value = false
                var b_no2Value = false
                var b_pm10Value = false
                var b_pm25Value = false
                var b_khaiValue = false
                var b_khaiGrade = false
                var b_so2Grade = false
                var b_coGrade = false
                var b_o3Grade = false
                var b_no2Grade = false
                var b_pm10Grade = false
                var b_pm25Grade = false
                var b_txtResult = true

                val url = URL(requestUrl)

                val t_connection = url.openConnection()
                t_connection.readTimeout = 3000
                val t_inputStream = t_connection.getInputStream()

                val factory = XmlPullParserFactory.newInstance()
                val parser = factory.newPullParser()
                parser.setInput(InputStreamReader(t_inputStream, "UTF-8"))

                val tag: String
                var eventType = parser.eventType

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_DOCUMENT -> list = ArrayList()

                        XmlPullParser.END_DOCUMENT -> {
                        }

                        XmlPullParser.END_TAG -> if (parser.name == "item" && data != null) {
                            list!!.add(data!!)

                            if (b_txtResult) {
                                data!!.setLocation(cityName!!)
                                b_txtResult = false
                            }
                        }

                        XmlPullParser.START_TAG -> {
                            if (parser.name == "item") {
                                data = Item()
                            }
                            if (parser.name == "stationName") b_stationName = true
                            if (parser.name == "dataTime") b_dataTime = true
                            if (parser.name == "so2Value") b_so2Value = true
                            if (parser.name == "coValue") b_coValue = true
                            if (parser.name == "o3Value") b_o3Value = true
                            if (parser.name == "no2Value") b_no2Value = true
                            if (parser.name == "pm10Value") b_pm10Value = true
                            if (parser.name == "pm25Value") b_pm25Value = true
                            if (parser.name == "khaiValue") b_khaiValue = true
                            if (parser.name == "khaiGrade") b_khaiGrade = true
                            if (parser.name == "so2Grade") b_so2Grade = true
                            if (parser.name == "coGrade") b_coGrade = true
                            if (parser.name == "o3Grade") b_o3Grade = true
                            if (parser.name == "no2Grade") b_no2Grade = true
                            if (parser.name == "pm10Grade") b_pm10Grade = true
                            if (parser.name == "pm25Grade") b_pm25Grade = true
                        }

                        XmlPullParser.TEXT -> if (b_stationName) {
                            data!!.setStationName(parser.text)
                            b_stationName = false
                        } else if (b_dataTime) {
                            data!!.setDataTime(parser.text)
                            b_dataTime = false
                        } else if (b_so2Value) {
                            data!!.setSo2Value(parser.text)
                            b_so2Value = false
                        } else if (b_coValue) {
                            data!!.setCoValue(parser.text)
                            b_coValue = false
                        } else if (b_o3Value) {
                            data!!.setO3Value(parser.text)
                            b_o3Value = false
                        } else if (b_no2Value) {
                            data!!.setNo2Value(parser.text)
                            b_no2Value = false
                        } else if (b_pm10Value) {
                            data!!.setPm10Value(parser.text)
                            if (data!!.getPm10Value() == " " || data!!.getPm10Value() == "-") {
                                data!!.colorViewText10 = "정보없음"
                                data!!.colorViewColor10 = "black"
                                colorView10!!.setBackgroundResource(R.color.black)
                                statusImage10!!.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm10Value()) <= 30) {
                                data!!.colorViewText10 = "좋음 : " + data!!.getPm10Value()
                                data!!.colorViewColor10 = "blue"
                                colorView10!!.setBackgroundResource(R.color.blue)
                                statusImage10!!.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm10Value()) <= 80) {
                                data!!.colorViewText10 = "보통 : " + data!!.getPm10Value()
                                data!!.colorViewColor10 = "green"
                                colorView10!!.setBackgroundResource(R.color.green)
                                statusImage10!!.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm10Value()) <= 150) {
                                data!!.colorViewText10 = "나쁨 : " + data!!.getPm10Value()
                                data!!.colorViewColor10 = "orange"
                                colorView10!!.setBackgroundResource(R.color.orange)
                                statusImage10!!.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                            } else {
                                data!!.colorViewText10 = "매우나쁨 : " + data!!.getPm10Value()
                                data!!.colorViewColor10 = "red"
                                colorView10!!.setBackgroundResource(R.color.red)
                                statusImage10!!.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                            }
                            b_pm10Value = false
                        } else if (b_pm25Value) {
                            data!!.setPm25Value(parser.text)
                            if (data!!.getPm25Value() == " " || data!!.getPm25Value() == "-") {
                                data!!.colorViewText25 = "정보없음"
                                data!!.colorViewColor25 = "black"
                                colorView25!!.setBackgroundResource(R.color.black)
                                statusImage25!!.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm25Value()) <= 15) {
                                data!!.colorViewText25 = "좋음 : " + data!!.getPm25Value()
                                data!!.colorViewColor25 = "blue"
                                colorView25!!.setBackgroundResource(R.color.blue)
                                statusImage25!!.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm25Value()) <= 35) {
                                data!!.colorViewText25 = "보통 : " + data!!.getPm25Value()
                                data!!.colorViewColor25 = "green"
                                colorView25!!.setBackgroundResource(R.color.green)
                                statusImage25!!.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
                            } else if (Integer.parseInt(data!!.getPm25Value()) <= 75) {
                                data!!.colorViewText25 = "나쁨 : " + data!!.getPm25Value()
                                data!!.colorViewColor25 = "orange"
                                colorView25!!.setBackgroundResource(R.color.orange)
                                statusImage25!!.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                            } else {
                                data!!.colorViewText25 = "매우나쁨 : " + data!!.getPm25Value()
                                data!!.colorViewColor25 = "red"
                                colorView25!!.setBackgroundResource(R.color.red)
                                statusImage25!!.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                            }
                            b_pm25Value = false
                        } else if (b_khaiValue) {
                            data!!.setKhaiValue(parser.text)
                            b_khaiValue = false
                        } else if (b_khaiGrade) {
                            data!!.setKhaiGrade(parser.text)
                            b_khaiGrade = false
                        } else if (b_so2Grade) {
                            data!!.setSo2Grade(parser.text)
                            b_so2Grade = false
                        } else if (b_coGrade) {
                            data!!.setCoGrade(parser.text)
                            b_coGrade = false
                        } else if (b_o3Grade) {
                            data!!.setO3Grade(parser.text)
                            b_o3Grade = false
                        } else if (b_no2Grade) {
                            data!!.setNo2Grade(parser.text)
                            b_no2Grade = false
                        } else if (b_pm10Grade) {
                            data!!.setPm10Grade(parser.text)
                            b_pm10Grade = false
                        } else if (b_pm25Grade) {
                            data!!.setPm25Grade(parser.text)
                            b_pm25Grade = false
                        }
                    }
                    eventType = parser.next()

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            flag = true
            return null
        }
    }
}
