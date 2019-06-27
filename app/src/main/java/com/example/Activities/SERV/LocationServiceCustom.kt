package com.example.Activities.SERV

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.example.Activities.Utils.SharedPreference
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.common.api.GoogleApiClient
import android.os.Bundle
import android.support.annotation.Nullable
import com.google.android.gms.location.LocationRequest




class LocationServiceCustom : Service() {


    val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 2000
    val UPDATE_FASTEST_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private  var runService: String = "run"
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val MY_PERMISSION_FINE_LOCATION = 101
    var locationRequest = LocationRequest()

    private  var latitude: Double= 0.0
    private  var longitude: Double= 0.0
    private  var altitude: Double= 0.0
    private  var accuracyDevice: Double= 0.0
    private  var angle: Double= 0.0
    private  var speed: Double= 0.0
    private  var imei: String=""


    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        //service


        // Do a periodic task
        mHandler = Handler()
        mRunnable = Runnable { refreshLocation() }
        mHandler.postDelayed(mRunnable, 5000)
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



    private fun refreshLocation(){
   fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    this.getLocationNow()
    }


    private  fun getLocationNow(){



        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            this.fusedLocationProviderClient!!.lastLocation.addOnSuccessListener {location ->

                var show: String =location.latitude.toString()+","+location.longitude.toString()+"->>"+location.accuracy.toString();

                Toast.makeText(applicationContext,show, Toast.LENGTH_LONG).show()
                Log.i("DebugLocation", show)
             /*   if (location != null) {

                    //update UI
                    tvLatitude.text = location.latitude.toString()
                    tvLongitude.text = location.longitude.toString()
                    if (location.hasAccuracy()) {
                        tvAccuracy.text = location.accuracy.toString()
                    } else {
                        tvAccuracy.text = "No Accuracy Available"
                    }
                    if (location.hasAltitude()) {
                        tvAltitude.text = location.altitude.toString()
                    } else {
                        tvAltitude.text = "No Altitude Available"
                    }
                    if (location.hasSpeed()) {
                        tvSpeed.text = location.speed.toString()
                    } else {
                        tvSpeed.text = "No Speed Available"
                    }
                }*/


                mHandler.postDelayed(mRunnable, 5000)

            }
        }

    }



}