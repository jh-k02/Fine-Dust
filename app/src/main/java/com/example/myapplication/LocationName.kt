package com.example.myapplication

import java.io.Serializable

class LocationName : Serializable {
    var guName: String? = null
    var dongName: String? = null
    internal var flag: Int = 0       // 0: 지역 1: 위치입력 2: gps

    init {
        guName = null
        dongName = null
        flag = 0
    }
}
