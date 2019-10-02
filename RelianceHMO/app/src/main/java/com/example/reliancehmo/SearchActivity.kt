package com.example.reliancehmo

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.reliancehmo.models.Provider
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_search.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import java.lang.Exception


class SearchActivity : AppCompatActivity() {

    private lateinit var searchQuery: String

    lateinit var searchQueryET: AppCompatAutoCompleteTextView

    private lateinit var providers: List<Provider>
    private lateinit var names: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)

        progressDialog = ProgressDialog(this)

        populateAutocomplete()
        searchQueryET = findViewById(R.id.search_query)

        fab.setOnClickListener { view ->
            submit()
        }
    }

    lateinit var progressDialog: ProgressDialog

    fun submit () {
        runOnUiThread {
            progressDialog.setTitle("Searching Providers...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        searchQuery = searchQueryET.text.toString()

        AsyncTask.execute {
            val call = APIFactory.getService().getProviders(searchQuery)
            val ex = call.execute()

            runOnUiThread {
                try {
                    if (ex.isSuccessful && ex.body()?.isSuccess() == true) {
                        val res = ex.body()
                        showProviders(res!!.data)
                        Log.d(AddActivity.TAG, Gson().toJson(res))
                        if (res!!.data.isEmpty()) {
                            Toast.makeText(this, "No providers found...", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Successfully found provider...",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    } else {
                        onErrors()
                    }

                    try {
                        progressDialog.dismiss()
                    } catch (e: Exception) {}
                }
                catch (e: Exception) {
                    onErrors()
                }
            }
        }
    }

    fun onErrors () {
        runOnUiThread {
            try {
                Toast.makeText(this, "Error searching providers...", Toast.LENGTH_LONG)
                    .show()
            } catch (e: Exception) {
            }
        }
    }

    fun showProviders (providers: List<Provider>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("providers", Gson().toJson(providers))
        intent.putExtra("query", searchQuery)
        startActivity(intent)
        finish()
    }

    fun doAutocomplete () {
        searchQueryET = findViewById(R.id.search_query)
        val adapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, names)
        searchQueryET.apply {
            threshold = 1
            setAdapter(adapter)
        }
    }

    fun populateAutocomplete () {
        AsyncTask.execute {
            val call = APIFactory.getService().getProviders("")
            val ex = call.execute()

            runOnUiThread {
                if (ex.isSuccessful && ex.body()?.isSuccess() == true) {
                    val res = ex.body()
                    Log.d(AddActivity.TAG, Gson().toJson(res))
                    Log.d(AddActivity.TAG, "Successfully fetched autocomplete results")
                    providers = res!!.data
                    names = getNames(providers)
                    doAutocomplete()
                } else {
                    Log.d(AddActivity.TAG, "Error fetching autocomplete results")
                }
            }
        }
    }

    fun getNames (providers: List<Provider>): List<String> {
        val names = ArrayList<String>()

        for (provider in providers) {
            names.add(provider.name)
        }

        return names
    }

}
