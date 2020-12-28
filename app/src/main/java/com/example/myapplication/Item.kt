package com.example.myapplication

class Item {
    internal var stationName: String? = null
    internal var dataTime: String? = null
    internal var so2Value: String? = null
    internal var coValue: String? = null
    internal var o3Value: String? = null
    internal var no2Value: String? = null
    internal var pm10Value: String? = null
    internal var pm25Value: String? = null
    internal var khaiValue: String? = null
    internal var khaiGrade: String? = null
    internal var so2Grade: String? = null
    internal var coGrade: String? = null
    internal var o3Grade: String? = null
    internal var no2Grade: String? = null
    internal var pm10Grade: String? = null
    internal var pm25Grade: String? = null
    internal var location: String? = null
    internal var colorViewText10: String? = null
    internal var colorViewColor10: String? = null
    internal var colorViewText25: String? = null
    internal var colorViewColor25: String? = null

    fun setStationName(stationName: String) {
        this.stationName = stationName
    }

    fun setDataTime(dataTime: String) {
        this.dataTime = dataTime
    }

    fun setSo2Value(so2Value: String) {
        this.so2Value = so2Value
    }

    fun setCoValue(coValue: String) {
        this.coValue = coValue
    }

    fun setO3Value(o3Value: String) {
        this.o3Value = o3Value
    }

    fun setNo2Value(no2Value: String) {
        this.no2Value = no2Value
    }

    fun setPm10Value(pm10Value: String) {
        this.pm10Value = pm10Value
    }

    fun setPm25Value(pm25value: String) {
        this.pm25Value = pm25value
    }

    fun setKhaiValue(khaiValue: String) {
        this.khaiValue = khaiValue
    }

    fun setKhaiGrade(khaiGrade: String) {
        this.khaiGrade = khaiGrade
    }

    fun setSo2Grade(so2Grade: String) {
        this.so2Grade = so2Grade
    }

    fun setCoGrade(coGrade: String) {
        this.coGrade = coGrade
    }

    fun setO3Grade(o3Grade: String) {
        this.o3Grade = o3Grade
    }

    fun setNo2Grade(no2Grade: String) {
        this.no2Grade = no2Grade
    }

    fun setPm10Grade(pm10Grade: String) {
        this.pm10Grade = pm10Grade
    }

    fun setPm25Grade(pm25Grade: String) {
        this.pm25Grade = pm25Grade
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun getPm10Value() : String? {
        return pm10Value
    }

    fun getPm25Value() : String? {
        return pm25Value
    }
}
