package br.com.up.posifood.model

import com.google.firebase.firestore.GeoPoint

data class Store(
    var location: GeoPoint,
    var name:String,
    var menu: ArrayList<MenuItem> = ArrayList()
)
