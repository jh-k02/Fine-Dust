package com.example.myapplication

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.io.IOException
import java.util.Locale

class Main2Activity : AppCompatActivity() {
    internal var locationName : LocationName?= null
    internal var lm: LocationManager? = null
    internal val context: Context = this
    internal var connectivityManager: ConnectivityManager? = null

    private val lastKnownLocation: Location?
        get() {
            val providers = lm!!.getProviders(true)
            var bestLocation: Location? = null
            for (provider in providers) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                }
                val l = lm!!.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    bestLocation = l
                }
            }
            return bestLocation
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        title = "미세먼지"

        locationName = LocationName()
        val getBtn = findViewById<View>(R.id.getBtn) as Button
        val where_gu = findViewById<View>(R.id.where_gu) as EditText
        val where_dong = findViewById<View>(R.id.where_dong) as EditText
        getBtn.setOnClickListener {
            locationName!!.guName = where_gu.text.toString()
            locationName!!.dongName = where_dong.text.toString()

            if (locationName!!.guName == "" || locationName!!.dongName == "") {
                val alertDialogBuiler = AlertDialog.Builder(context)

                alertDialogBuiler.setTitle("입력 오류")

                alertDialogBuiler.setMessage("다시 입력하세요.")
                        .setCancelable(false)
                        .setPositiveButton("확인") { dialog, id -> dialog.cancel() }

                val alertDialog = alertDialogBuiler.create()

                alertDialog.show()
            } else {
                locationName!!.flag = 1
                val intent = Intent(this@Main2Activity, MainActivity::class.java)
                intent.putExtra("locationName", locationName)
                startActivity(intent)
            }
        }

        val getGBtn = findViewById<View>(R.id.getGBtn) as Button
        getGBtn.setOnClickListener {
            lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@Main2Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            } else {
                var location = lastKnownLocation

                while (location == null)
                    location = lastKnownLocation

                val longitude = location.longitude
                val latitude = location.latitude

                val gcd = Geocoder(baseContext, Locale.KOREA)
                val addresses: List<Address>
                try {
                    addresses = gcd.getFromLocation(latitude, longitude, 10)
                    if (addresses.size > 0) {
                        val currentLocationAddress = addresses[0]
                        locationName!!.guName = currentLocationAddress.locality
                        locationName!!.dongName = currentLocationAddress.thoroughfare

                        for (i in 0..9) {
                            locationName!!.dongName = currentLocationAddress.thoroughfare.replace(i.toString(), "")
                        }
                        locationName!!.flag = 2
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }


            val intent = Intent(this@Main2Activity, MainActivity::class.java)
            intent.putExtra("locationName", locationName)
            startActivity(intent)
        }

        val listBtn = findViewById<View>(R.id.listBtn) as Button
        listBtn.setOnClickListener {
            val intent = Intent(this@Main2Activity, ListActivity::class.java)
            startActivity(intent)
        }
    }

    /*private fun turnGPSOn(): Boolean {
        val gps = android.provider.Settings.Secure.getString(contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)

        if (!(gps.matches(".*gps.*".toRegex()) && gps.matches(".*network.*".toRegex()))) {
            val gsDialog = AlertDialog.Builder(this)
            gsDialog.setTitle("위치 서비스 설정")
            gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?")
            gsDialog.setPositiveButton("네") { dialog, which ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                startActivity(intent)
            }.setNegativeButton("종료", DialogInterface.OnClickListener { dialog, which ->
                val toast = Toast(this@Main2Activity)

                toast.makeText(this@Main2Activity, "GPS를 켜고 다시 시도해 주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                finish()
                return@OnClickListener
            }).create().show()
            return false
        } else {
            return true
        }
    }

    private fun checkInternetState(): Boolean {
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        assert(connectivityManager != null)
        if (!(connectivityManager!!.activeNetworkInfo != null && connectivityManager!!.activeNetworkInfo.isConnected)) {
            AlertDialog.Builder(this)
                    .setMessage("네트워크와 연결되어 있지 않습니다.")
                    .setCancelable(false)
                    .setPositiveButton("종료", DialogInterface.OnClickListener { dialog, which ->
                        val toast = Toast(this@Main2Activity)

                        toast.makeText(this@Main2Activity, "네트워크 연결 후 다시 시도해 주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                        finish()
                        return@OnClickListener
                    }).show()
            return false
        } else
            return true
    }*/
}
