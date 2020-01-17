package ru.vssemikoz.pinterestsdk

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinterest.android.pdk.PDKCallback
import com.pinterest.android.pdk.PDKClient
import com.pinterest.android.pdk.PDKException
import kotlinx.android.synthetic.main.activity_pin.*
import org.json.JSONObject

class PinActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        getPins()
    }


    private fun getUserList(listOfURLs: ArrayList<String>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val imageItemList = ArrayList<ImageItem>()

        imageItemList.addAll(ImageItem.getUrlList(listOfURLs))
        val myAdapter = PinAdapter(imageItemList)
        recyclerView.adapter = myAdapter
    }

    private fun getPins() {
        val params = HashMap<String, String>()
        params[PDKClient.PDK_QUERY_PARAM_FIELDS] = "image"

        PDKClient.getInstance().getPath("me/pins/", params, object : PDKCallback(){

            override fun onSuccess(response: JSONObject) {
                super.onSuccess(response)
                val listOfURLs = parseForUrl(response)
                getUserList(listOfURLs)
            }

            override fun onFailure(exception: PDKException) {
                super.onFailure(exception)
                Log.d("M_MainActivity", exception.detailMessage)
            }
        })

    }
    fun parseForUrl(response : JSONObject): ArrayList<String> {
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

}