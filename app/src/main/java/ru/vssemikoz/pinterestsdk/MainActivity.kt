package ru.vssemikoz.pinterestsdk


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pinterest.android.pdk.PDKCallback
import com.pinterest.android.pdk.PDKClient
import com.pinterest.android.pdk.PDKException
import com.pinterest.android.pdk.PDKResponse

class MainActivity : AppCompatActivity() {

    private val appID = "5077704636564027977"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton : Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener{
            onLogin()
        }

        PDKClient.configureInstance(this, appID)
        PDKClient.getInstance().onConnect(this)
        PDKClient.setDebugMode(true)

    }

    private fun onLogin() {

        val scopes: MutableList<String> = ArrayList()
        val intent = Intent(this, PinActivity::class.java)

        scopes.add(PDKClient.PDKCLIENT_PERMISSION_READ_PUBLIC)
        scopes.add(PDKClient.PDKCLIENT_PERMISSION_WRITE_PUBLIC)

        PDKClient.getInstance().login(this, scopes, object : PDKCallback(){
            override fun onSuccess(response: PDKResponse) {
                super.onSuccess(response)
                startActivity(intent)
            }
            override fun onFailure(exception: PDKException) {
                super.onFailure(exception)
                Log.e("M_MainActivity", exception.detailMessage)
            }
        })
    }


}
