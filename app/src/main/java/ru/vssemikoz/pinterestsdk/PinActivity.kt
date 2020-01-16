package ru.vssemikoz.pinterestsdk

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.pinterest.android.pdk.PDKCallback
import com.pinterest.android.pdk.PDKClient
import com.pinterest.android.pdk.PDKException
import com.squareup.picasso.Picasso
import org.json.JSONObject

class PinActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        getPins()
    }

    private fun getPins() {
        var params = HashMap<String, String>();
        params[PDKClient.PDK_QUERY_PARAM_FIELDS] = "image"

        PDKClient.getInstance().getPath("me/pins/", params, object : PDKCallback(){

            override fun onSuccess(response: JSONObject) {
                super.onSuccess(response)
                var listOfURLs = parse(response)
                setImageByUrl(listOfURLs)
            }

            override fun onFailure(exception: PDKException) {
                super.onFailure(exception)
                Log.d("M_MainActivity", exception.detailMessage)
            }
        })

    }
    fun parse(response : JSONObject): ArrayList<String> {
        val resultData = ArrayList<String>()
        val data = response.getJSONArray("data")
        for (i in 0 until data.length()) {
            val image: String = data.getJSONObject(i)
                .getJSONObject("image")
                .getJSONObject("original")
                .getString("url")

            resultData.add(image)
        }
        return resultData
    }


    fun setImageByUrl(urlList: ArrayList<String>?){
        val listOfImageView  = ArrayList<ImageView>()
        listOfImageView.add(findViewById(R.id.imageView))
        listOfImageView.add(findViewById(R.id.imageView2))
        listOfImageView.add(findViewById(R.id.imageView3))
        listOfImageView.add(findViewById(R.id.imageView4))
        listOfImageView.add(findViewById(R.id.imageView5))
        listOfImageView.add(findViewById(R.id.imageView6))

        for (i in 0 until listOfImageView.size){
            Picasso.with(this).load(urlList?.get(i)).into(listOfImageView[i])
        }

    }
}