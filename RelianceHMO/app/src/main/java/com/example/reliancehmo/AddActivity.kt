package com.example.reliancehmo

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.reliancehmo.models.Id
import com.example.reliancehmo.models.Provider
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_add.*
import java.lang.Exception
import java.util.*
import java.util.logging.Logger

class AddActivity : AppCompatActivity() {

    private lateinit var name: String

    private var rating: Double = 0.0

    private lateinit var type: String

    private lateinit var imageUrl: String

    private lateinit var state: String

    private lateinit var address: String


    lateinit var nameET: EditText

    lateinit var ratingET: EditText

    lateinit var typeET: EditText

    lateinit var imageUrlET: EditText

    lateinit var stateET: EditText

    lateinit var addressET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        progressDialog = ProgressDialog(this)

        nameET = findViewById(R.id.name_et)
        nameET.setText(UUID.randomUUID().toString())
        ratingET = findViewById(R.id.rating_et)
        typeET = findViewById(R.id.type_et)
        imageUrlET = findViewById(R.id.url_et)
        stateET = findViewById(R.id.state_et)
        addressET = findViewById(R.id.address_et)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            submit()
        }
    }

    lateinit var progressDialog: ProgressDialog

    fun submit () {
        runOnUiThread {
            // val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Adding Provider...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        name = nameET.text.toString()
        rating = ratingET.text.toString().toDouble()
        type = typeET.text.toString()
        imageUrl = imageUrlET.text.toString()
        state = stateET.text.toString()
        address = addressET.text.toString()

        val provider = Provider()
        provider.name = name
        provider.rating = rating
        provider.address = address
        provider.type = type
        provider.imageUrl = imageUrl
        provider.state = state

        Log.d(TAG, Gson().toJson(provider))

        AsyncTask.execute {
            val call = APIFactory.getService().addProvider(provider)
            val ex = call.execute()

            try {
                runOnUiThread {
                    if (ex.isSuccessful && ex.body()?.isSuccess() == true) {
                        val res = ex.body()
                        Log.d(TAG, Gson().toJson(res))
                        Toast.makeText(this, "Successfully added provider...", Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        onErrors()
                    }

                    try {
                        progressDialog.dismiss()
                    } catch (e: Exception) {
                    }
                }
            }
            catch (e: Exception) {
                onErrors()
            }
        }
    }

    fun onErrors () {
        runOnUiThread {
            try {
                Toast.makeText(this, "Error searching providers...", Toast.LENGTH_LONG)
                    .show()
            } catch (e: Exception) { }
        }
    }

    companion object {
        val TAG = AddActivity.javaClass.simpleName
    }

}
