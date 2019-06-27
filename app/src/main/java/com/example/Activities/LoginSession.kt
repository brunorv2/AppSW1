package com.example.Activities


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.Activities.webservice.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.Activities.Login.LoginData
import com.example.Activities.Login.Login
import com.example.Activities.Utils.SharedPreference


class LoginSession : AppCompatActivity() {

    private var user: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebService.set("http://18.221.71.36:80/")
        user=findViewById(R.id.userID)
        password=findViewById(R.id.passwordID)

        this.validateSessionUser()

    }


    public override fun onResume() {
        super.onResume()
        this.validateSessionUser()
    }



    fun Login(view: View) {

    var userTXT= user!!.getText().toString()
    var paswordTXT= password!!.getText().toString()

 /*    Toast.makeText(this,"Usuario: "+userTXT,Toast.LENGTH_LONG).show()
     Toast.makeText(this,"Password: "+paswordTXT,Toast.LENGTH_LONG).show()

 */
        WebService.get().create(Login::class.java)
            .LoginFun(
                userTXT,
                paswordTXT
            )
            .enqueue(object : Callback<LoginData> {
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {

               var UserData= response.body()!!.userData

               var login=UserData.auth.toString()
               var worker=UserData.worker.toString()
                    if (login=="1"){
                        val intent = Intent(applicationContext, MenuAct::class.java)
                        intent.putExtra("gender","m")
                        intent.putExtra("user",userTXT)
                        intent.putExtra("worker",worker)

                        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
                        sharedPreference.save("userSession",userTXT)
                        sharedPreference.save("workerSession",worker)
                        startActivity(intent)


                    }else{
                        Toast.makeText(applicationContext,"Credenciales Incorrectos",Toast.LENGTH_LONG).show()
                    }



                }


                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    Log.i("LogReply", t.toString())
                    Toast.makeText(applicationContext,"Error de conexion...intente nuevamente",Toast.LENGTH_LONG).show()

                }
            })


 /*    Log.i("user", userTXT)
     Log.i("pass", paswordTXT)

*/
    }



    fun validateSessionUser(){

        val sharedPreference: SharedPreference =SharedPreference(applicationContext)
        val userSession:String?=sharedPreference.getValueString("serviceSession")


        if(userSession!=null){

            val userTXT: String?= user!!.getText().toString()
            val paswordTXT: String?= password!!.getText().toString()

            val intent = Intent(applicationContext, MenuAct::class.java)
            intent.putExtra("gender","m")
            intent.putExtra("user",userTXT)
            intent.putExtra("worker",paswordTXT)
            startActivity(intent)
        }



    }



}




