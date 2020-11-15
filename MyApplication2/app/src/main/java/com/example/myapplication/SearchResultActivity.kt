package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSONArray
import com.example.myapplication.model.ResearchResultModel
import kotlinx.android.synthetic.main.search_result.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import com.example.myapplication.util.BaseUtil.hexStringToBytes

class SearchResultActivity: AppCompatActivity() {
    private val goodsList = ArrayList<Goods>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result)
        val words=intent.getStringExtra("words")

        //initGoods() // 初始化数据

        val researchResultModel= ResearchResultModel()
        if (words != null) {
            researchResultModel.getResearchResult(words).enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@SearchResultActivity, "搜索失败请稍后重试", Toast.LENGTH_SHORT).show()
                }
                @SuppressLint("SdCardPath")
                override fun onResponse(

                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val jsonData=response.body()?.string()
                    println("this is$jsonData")
                    if (jsonData!=null){
                        var jsonArray: JSONArray? =null
                        try {
                            jsonArray= JSONArray.parse(jsonData) as JSONArray?
                        }catch(e: Exception){
                            e.printStackTrace()
                        }

                        if (jsonArray != null) {
                            for(i in 0 until jsonArray.size){
                                val jsonObject = jsonArray.getJSONObject(i)
                                val primaryImage=hexStringToBytes(jsonObject.getString("primaryImage"))
                                println(primaryImage)
                                val title=jsonObject.getString("title")
                                val price=jsonObject.getString("price")

                                val file=
                                    File("/sdcard/Pictures/"+System.currentTimeMillis()+".png");
                                if(!file.exists()){
                                    try {
                                        file.createNewFile()
                                    }catch (e:Exception){
                                        e.printStackTrace()
                                    }
                                }

                                val bufferedInputStream= ByteArrayInputStream(primaryImage)
                                val fileOutputStream = FileOutputStream(file);
                                var i:Int=bufferedInputStream.read()
                                while (i!=-1){
                                    fileOutputStream.write(i)
                                    i=bufferedInputStream.read()
                                }
                                fileOutputStream.flush()
                                bufferedInputStream.close()
                                fileOutputStream.close()
                                val goods= Goods(title,file,price)
                                goodsList.add(goods)
                            }
                        }
                    }
                }

            })
        }
        val adapter = GoodsAdapter(this, R.layout.goods_item, goodsList)
        listView.adapter = adapter
    }

//    private fun initGoods() {
//        repeat(3) {
//            goodsList.add(Goods("学而思秘籍", R.drawable.xueersi,23))
//            goodsList.add(Goods("中学教材全解", R.drawable.jiaocaiquanjie,45))
//            goodsList.add(Goods("高考必刷题", R.drawable.bishuati,56))
//            goodsList.add(Goods("五年中考三年模拟", R.drawable.zhongkao,60))
//        }
//    }

}