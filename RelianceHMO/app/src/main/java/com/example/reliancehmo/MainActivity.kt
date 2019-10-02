package com.example.reliancehmo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.reliancehmo.models.Provider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        progressDialog = ProgressDialog(this)

        // Search result
        if (intent.hasExtra("providers")) {
            val listType = object : TypeToken<List<Provider>>() { }.type
            val providers = Gson().fromJson<List<Provider>>(intent!!.extras!!.getString("providers"), listType)
            val query = intent!!.extras!!.getString("query")
            title = "Results for \"$query\""
            renderProviders(providers)
        }
        // Return all
        else {
            renderProviders(null)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
            R.id.action_add -> {
                startActivity(Intent(this, AddActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    lateinit var progressDialog: ProgressDialog

    fun fetchProviders () {
        runOnUiThread {
            progressDialog.setTitle("Fetching Providers...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.show()
        }

        val searchQuery = ""

        AsyncTask.execute {
            val call = APIFactory.getService().getProviders(searchQuery)
            val ex = call.execute()
            runOnUiThread {
                if (ex.isSuccessful && ex.body()?.isSuccess() == true) {
                    val res = ex.body()
                    renderProviders(res!!.data)
                    Log.d(AddActivity.TAG, Gson().toJson(res))
                    Toast.makeText(this, "Successfully found provider...", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error searching providers...", Toast.LENGTH_LONG).show()
                }

                try {
                    progressDialog.dismiss()
                }
                catch (e: Exception) {}
            }
        }
    }

    fun renderProviders (providers: List<Provider>?) {
        runOnUiThread {
            if (providers == null) {
                fetchProviders()
            } else {
                val adapter = ProviderAdapter(this, providers)
                val manager = LinearLayoutManager(this)

                val providerRV = findViewById<RecyclerView>(R.id.provider_rv)
                providerRV.adapter = adapter
                providerRV.layoutManager = manager
            }
        }
    }

}
