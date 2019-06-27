package com.example.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toast
import com.example.Activities.SERV.ServiceDemo
import com.example.Activities.Utils.SharedPreference
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_menu.*
import android.support.v4.content.ContextCompat
import android.telephony.TelephonyManager
import android.util.Log
import com.example.Activities.SERV.LocationServiceCustom


class MenuAct : AppCompatActivity() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val MY_PERMISSION_FINE_LOCATION = 101
    val PHONESTATS = 0x1
    var imeiDevice=""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        getPermissionImei(Manifest.permission.READ_PHONE_STATE, PHONESTATS)

        tbGps_Balanced.setOnClickListener {
            if (tbGps_Balanced.isChecked) {
                //using GPS only
                tvSensor.text = "GPS"

            } else {
                //using balanced power accuracy
                tvSensor.text = "Cell Tower and WiFi"
            }
        }

        tbLocationOnOff.setOnClickListener {
            if (tbLocationOnOff.isChecked) {
                //location update on
                tvUpdates.text = "On"

            } else {
                //location update off
                tvUpdates.text = "Off"
            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient!!.lastLocation.addOnSuccessListener {location ->

                if (location != null) {

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
                }

            }
        } else {
            //request permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_FINE_LOCATION)
            }
        }


        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
        sharedPreference.save("ImeiDevice",imeiDevice)


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_FINE_LOCATION ->
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted. No need to do anything
                } else {
                    Toast.makeText(applicationContext, "This app requires location permissions to be granted", Toast.LENGTH_SHORT).show()
                    finish()
                }
        }
    }


    override fun onStart() {
        super.onStart()

   /*     val bundle = intent.extras

        val user = bundle.get("user")
        val gender = bundle.get("gender")
        val worker = bundle.get("worker")

        val sharedPreference: SharedPreference = SharedPreference(applicationContext)

        val workerStr:String?=sharedPreference.getValueString("workerSession")

        var msg="Bienvenido "+workerStr

        if(gender=="f"){
            msg="Bienvenida"+workerStr
        }


        Toast.makeText(applicationContext,msg, Toast.LENGTH_LONG).show()

*/


        getPermissionImei(Manifest.permission.READ_PHONE_STATE, PHONESTATS)
        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
        sharedPreference.save("ImeiDevice",imeiDevice)




    }


    fun InitService(view: View) {

        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
        sharedPreference.save("serviceRun","run")
        startService(Intent(this, LocationServiceCustom::class.java))
    }



    fun StopService(view: View) {
        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
        sharedPreference.save("serviceRun","stop")
        stopService(Intent(this, LocationServiceCustom::class.java))
    }


    private fun getPermissionImei(permission: String, requestCode: Int?) {
        if (ContextCompat.checkSelfPermission(this@MenuAct, permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MenuAct, permission)) {

                ActivityCompat.requestPermissions(this@MenuAct, arrayOf(permission), requestCode!!)

            } else {

                ActivityCompat.requestPermissions(this@MenuAct, arrayOf(permission), requestCode!!)
            }
        } else {
            imeiDevice = getDataImei()
            Toast.makeText(this@MenuAct, "$permission El permiso a la aplicación esta concedido.", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getDataImei(): String {
        val telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Hacemos la validación de métodos, ya que el método getDeviceId() ya no se admite para android Oreo en adelante, debemos usar el método getImei()
            telephonyManager.imei
        } else {
            telephonyManager.deviceId
        }

    }



}
