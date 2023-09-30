package com.example.royxatdanotish.Mudels

import android.net.Uri

class User{
    var id:Int? = null
    var nameSourname:String = ""
    var phone:String = ""
    var country:String = ""
    var manzil:String = ""
    var password:String = ""
    var image:String = ""
    var image2:Int = 0



    constructor(
        nameSourname: String,
        phone: String,
        country: String,
        manzil: String,
        password: String,
        image: String,
        image2: Int
    ) {
        this.nameSourname = nameSourname
        this.phone = phone
        this.country = country
        this.manzil = manzil
        this.password = password
        this.image = image
        this.image2 = image2
    }

    constructor(
        nameSourname: String,
        phone: String,
        country: String,
        manzil: String,
        password: String,
        image: String
    ) {
        this.nameSourname = nameSourname
        this.phone = phone
        this.country = country
        this.manzil = manzil
        this.password = password
        this.image = image
    }

    constructor(
        nameSourname: String,
        phone: String,
        country: String,
        manzil: String,
        password: String,
        image2: Int
    ) {
        this.nameSourname = nameSourname
        this.phone = phone
        this.country = country
        this.manzil = manzil
        this.password = password
        this.image2 = image2
    }

    constructor(
        id: Int?,
        nameSourname: String,
        phone: String,
        country: String,
        manzil: String,
        password: String,
        image: String
    ) {
        this.id = id
        this.nameSourname = nameSourname
        this.phone = phone
        this.country = country
        this.manzil = manzil
        this.password = password
        this.image = image
    }


}

