package com.example.Activities.SERV

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.Activities.Utils.SharedPreference
import java.util.*

class ServiceDemo : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private  var runService: String = "run"

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        //service

        // Do a periodic task
        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 2000)
        return Service.START_STICKY
    }

    override fun onDestroy() {
      //  super.onDestroy()
    //    toast("Service destroyed.")
     //
  //  mHandler.removeCallbacks(mRunnable)
   // super.onDestroy()



        try {
            val sharedPreference: SharedPreference = SharedPreference(applicationContext)
            runService= sharedPreference.getValueString("service")!!
        } catch (e: Exception) {
            Log.i("serviceDemo", e.toString())
        }





       if(runService=="stop"){
           Toast.makeText(applicationContext,"End Service", Toast.LENGTH_LONG).show()
           mHandler.removeCallbacks(mRunnable)
           super.onDestroy()
       }



    }






    // Custom method to do a task
    public fun showRandomNumber() {



        try {
            val rand = Random()
            val number = rand.nextInt(100)
           Toast.makeText(applicationContext,number, Toast.LENGTH_LONG).show()
        //    Log.i("serviceDemo", number.toString())
            mHandler.postDelayed(mRunnable, 2000)
        } catch (e: Exception) {
       //     Toast.makeText(applicationContext,e.toString(), Toast.LENGTH_LONG).show()
            Log.i("serviceDemo", e.toString())
        }


    }




}