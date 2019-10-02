package com.example.reliancehmo.models

import android.text.TextUtils

class Provider {
    /**
        "name": "" | required,
        "state":"Oshogbo" | required,
        "address":"Oshogbo west" | required
        "type":"" | required,
        "imageUrl": "https://via.placeholder.com/400x200" | required,
        "rating":3.5
     */

    var name: String = ""

    var state: String = ""

    var address: String = ""

    var location: Location? = null

    var type: String = ""

    var imageUrl: String = ""

    var rating: Double = 0.0

    fun hasLocation (): Boolean {
        return (TextUtils.isEmpty(state) && TextUtils.isEmpty(address));
    }

}