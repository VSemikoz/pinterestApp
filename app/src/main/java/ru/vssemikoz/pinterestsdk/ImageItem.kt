package ru.vssemikoz.pinterestsdk
import java.io.Serializable

class ImageItem(val imageUrl: String) : Serializable {
    companion object {

        fun getUrlList(urlList: ArrayList<String>): List<ImageItem> {
            val imageItemList = ArrayList<ImageItem>()
            imageItemList.clear()
            for (url in urlList){
                imageItemList.add(ImageItem(url))
            }

            return imageItemList
        }
    }

}
