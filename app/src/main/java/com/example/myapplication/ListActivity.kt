package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        title = "지역으로 미세먼지 보기"

        val seoulBtn = findViewById<View>(R.id.seoulBtn) as Button
        seoulBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "서울")
            startActivity(intent)
        }

        val busanBtn = findViewById<View>(R.id.busanBtn) as Button
        busanBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "부산")
            startActivity(intent)
        }

        val daeguBtn = findViewById<View>(R.id.daeguBtn) as Button
        daeguBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "대구")
            startActivity(intent)
        }

        val incheonBtn = findViewById<View>(R.id.incheonBtn) as Button
        incheonBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "인천")
            startActivity(intent)
        }

        val gwangjuBtn = findViewById<View>(R.id.gwangjuBtn) as Button
        gwangjuBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "광주")
            startActivity(intent)
        }

        val daejeonBtn = findViewById<View>(R.id.daejeonBtn) as Button
        daejeonBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "대전")
            startActivity(intent)
        }

        val ulsanBtn = findViewById<View>(R.id.ulsanBtn) as Button
        ulsanBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "울산")
            startActivity(intent)
        }

        val gyeonggiBtn = findViewById<View>(R.id.gyeonggiBtn) as Button
        gyeonggiBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "경기")
            startActivity(intent)
        }

        val gangwonBtn = findViewById<View>(R.id.gangwonBtn) as Button
        gangwonBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "강원")
            startActivity(intent)
        }

        val chungbukBtn = findViewById<View>(R.id.chungbukBtn) as Button
        chungbukBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "충북")
            startActivity(intent)
        }

        val chungnamBtn = findViewById<View>(R.id.chungnamBtn) as Button
        chungnamBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "충남")
            startActivity(intent)
        }

        val jeonbukBtn = findViewById<View>(R.id.jeonbukBtn) as Button
        jeonbukBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "전북")
            startActivity(intent)
        }

        val jeonnamBtn = findViewById<View>(R.id.jeonnamBtn) as Button
        jeonnamBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "전남")
            startActivity(intent)
        }

        val gyeongbukBtn = findViewById<View>(R.id.gyeongbukBtn) as Button
        gyeongbukBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "경북")
            startActivity(intent)
        }

        val gyeongnamBtn = findViewById<View>(R.id.gyeongnamBtn) as Button
        gyeongnamBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "경남")
            startActivity(intent)
        }

        val jejuBtn = findViewById<View>(R.id.jejuBtn) as Button
        jejuBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "제주")
            startActivity(intent)
        }

        val sejongBtn = findViewById<View>(R.id.sejongBtn) as Button
        sejongBtn.setOnClickListener {
            val intent = Intent(this@ListActivity, CityListActivity::class.java)
            intent.putExtra("sidoName", "세종")
            startActivity(intent)
        }

    }


}
