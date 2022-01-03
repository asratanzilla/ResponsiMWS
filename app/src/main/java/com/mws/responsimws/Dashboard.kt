package com.mws.responsimws

import Book
import RVAdapterBook
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

class Dashboard : AppCompatActivity() {
    var arrayList = ArrayList<Book>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.title = "Data Buku"

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        btnInput.setOnClickListener{
            startActivity(Intent(this, ManageBookActivity::class.java))
        }

    }


    override fun onResume() {
        super.onResume()
        loadAllBook()
    }

     private fun loadAllBook() {
         val loading = ProgressDialog(this)
         loading.setMessage("Memuat data...")
         loading.show()

         AndroidNetworking.get(ApiEndPoint.READ)
             .setPriority(Priority.MEDIUM)
             .build()
             .getAsJSONObject(object : JSONObjectRequestListener {
                 override fun onResponse(response: JSONObject?) {
                     arrayList.clear()

                     val jsonArray = response?.optJSONArray("result")
                     if (jsonArray?.length() == 0) {
                         loading.dismiss()

                         Toast.makeText(
                             applicationContext,
                             "Book data is empty, Add the data first",
                             Toast.LENGTH_SHORT
                         ).show()
                     }
                     for (i in 0 until jsonArray?.length()!!) {
                         val jsonObject = jsonArray?.optJSONObject(i)

                         arrayList.add(
                             Book(
                                 jsonObject.getString("idbukku"),
                                 jsonObject.getString("judulbuku"),
                                 jsonObject.getString("namapengarang"),
                                 jsonObject.getString("tahunterbit"),
                                 jsonObject.getString("penerbit")
                             )
                         )

                         if (jsonArray?.length() - 1 == i) {
                             loading.dismiss()
                             val adapter = RVAdapterBook(applicationContext, arrayList)
                             adapter.notifyDataSetChanged()
                             mRecyclerView.adapter = adapter
                         }
                     }
                 }

                 override fun onError(anError: ANError?) {
                     loading.dismiss()
                     Log.d("ONERROR", anError?.errorDetail?.toString().toString())
                     Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                 }

             })
     }
}