package com.example.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    //Module-level variable
    lateinit var countViewModel: CountViewModel
    //Create an instance of the shared preferences
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialise the ViewModel
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        //Initialize the Shared Preferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)


        imageViewLike.setOnClickListener{
            countViewModel.countLike++
            textViewLike.text = countViewModel.countLike.toString()
        }

        imageView2Dislike.setOnClickListener{
            countViewModel.countDislike++
            textViewDislike.text = countViewModel.countDislike.toString()
        }
    }

    override fun onStart(){
        Log.d("MainActivity", "OnStart")
        super.onStart()
    }

    override  fun onResume(){
        Log.d("MainActivity", "OnResume")

        countViewModel.countLike = sharedPreferences.getInt(getString(R.string.like),0)
        textViewLike.text = countViewModel.countLike.toString()
        countViewModel.countDislike = sharedPreferences.getInt(getString(R.string.dislike),0)
        textViewDislike.text = countViewModel.countDislike.toString()

        super.onResume()
    }

    override fun onPause(){
        Log.d("MainActivity", "OnPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), countViewModel.countLike)
            putInt(getString(R.string.dislike), countViewModel.countDislike)
            commit()
        }
        super.onPause()
    }

    override fun onStop(){
        Log.d("MainActivity", "OnStop")
        super.onStop()
    }

    override fun onDestroy(){
        Log.d("MainActivity", "OnDestroy")
        super.onDestroy()
    }
}
