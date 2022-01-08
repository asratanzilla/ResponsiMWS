package com.mws.responsimws

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_book.*
import org.json.JSONObject

class ManageBookActivity : AppCompatActivity() {
    lateinit var i: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_book)

        i = intent

        if(i.hasExtra("editmode")){

            if(i.getStringExtra("editmode").equals("1")){

                onEditMode()

            }

        }
        btnCreate.setOnClickListener {
            create()
        }

        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL",DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }

    }

    private fun onEditMode() {
        txtJudulBuku.setText(i.getStringExtra("judul_buku"))
        txtNamaPengarang.setText(i.getStringExtra("nama_pengarang"))
        txtTahunTebit.setText(i.getStringExtra("tahun_terbit"))
        txtPenerbit.setText(i.getStringExtra("penerbit"))
        txtIdBuku.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("id_buku",txtIdBuku.text.toString())
            .addBodyParameter("judul_buku",txtJudulBuku.text.toString())
            .addBodyParameter("nama_pengarang",txtNamaPengarang.text.toString())
            .addBodyParameter("tahun_terbit",txtTahunTebit.text.toString())
            .addBodyParameter("penerbit",txtPenerbit.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageBookActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString().toString())
                    Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                }


            })
    }
    private fun update(){

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
            .addBodyParameter("Id_buku",txtIdBuku.text.toString())
            .addBodyParameter("judul_buku",txtJudulBuku.text.toString())
            .addBodyParameter("nama_pengarang",txtNamaPengarang.text.toString())
            .addBodyParameter("tahun_terbit",txtTahunTebit.text.toString())
            .addBodyParameter("penerbit",txtPenerbit.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageBookActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString().toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"?id_buku="+txtIdBuku.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageBookActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString().toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
}