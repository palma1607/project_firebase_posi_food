package br.com.up.posifood.repository

import br.com.up.posifood.model.MenuItem
import br.com.up.posifood.model.Store
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StoreRepository {

    fun getAll(callback: (ArrayList<Store>) -> Unit) {

        val database = Firebase.firestore
        val collection: Task<QuerySnapshot> = database.collection("Stores").get()
        collection.addOnSuccessListener { documents ->

            val listStores = ArrayList<Store>()
            for (document in documents) {
                val name = document["name"] as String
                val menu = document["menu"] as ArrayList<HashMap<String, Any>>
                val location = document["location"] as GeoPoint
                val store = Store(location, name)

                for (menuItem in menu) {
                    val menuName = menuItem["name"] as String
                    val price = menuItem["price"] as Double
                    val description = menuItem["description"] as String

                    val menuItem = MenuItem(description, menuName, price)

                    store.menu.add(menuItem)
                }

                listStores.add(store)
            }
            callback(listStores)
        }
    }
}