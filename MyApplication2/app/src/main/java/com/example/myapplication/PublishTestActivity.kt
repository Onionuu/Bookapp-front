package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.EditPostModel
import com.google.gson.JsonObject


import kotlinx.android.synthetic.main.publish_test.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.beahugs.imagepicker.utils.ImageSelector
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class PublishTestActivity : AppCompatActivity() {
    companion object {
        private val REQUEST_CODE=0x00000011
        private val PERMISSION_WRITE_EXTERNAL_REQUEST_CODE = 0x00000012
    }

    private lateinit var rvImage: RecyclerView
    private lateinit var mAdapter: ImageAdapter
    private lateinit var images:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.publish_test)
        publishButton.setOnClickListener(){
            publish()
        }
        rvImage=findViewById(R.id.rv_image)
        rvImage.layoutManager = GridLayoutManager(this, 3)
        mAdapter = ImageAdapter(this)
        rvImage.adapter = mAdapter
        addImageButton.setOnClickListener(){
            ImageSelector.builder()
                .useCamera(false) // 使用拍照
                .setCrop(false)  // 使用图片剪切
                //.setCropRatio(1.0f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
                .setSingle(false)  //设置是否单选
                .canPreview(true) //是否点击放大图片查看,，默认为true
                .setMaxSelectCount(9)//如果设置大于0
                .start(this, REQUEST_CODE); // 打开相册
        }
    }

//    override fun onClick(v: View?) {
//        when (v!!.id) {
//            R.id.addImageButton ->
//                //多选(最多9张)
//                ImageSelector.builder()
//                    //.useCamera(add_camera.isChecked()) // 使用拍照
//                    //.setCrop(add_crop.isChecked())  // 使用图片剪切
//                    //.setCropRatio(1.0f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
//                    //.setSingle(add_single.isChecked())  //设置是否单选
//                    .canPreview(true) //是否点击放大图片查看,，默认为true
//                    .setMaxSelectCount(9)//如果设置大于0
//                    .start(this, REQUEST_CODE); // 打开相册
//        }
//    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.e("TAG", "onActivityResult: " )
    Log.e("TAG", "resultCode: "+requestCode )
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && data != null) {
             images = (data.getStringArrayListExtra(ImageSelector.SELECT_RESULT) as ArrayList<String>)
            Log.e("TAG", images.toString())
            var isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE,false)
            Log.e("TAG", "hererererre ")
            mAdapter?.refresh(images)

        }
    }
    fun publish(){
        val editPostModel= EditPostModel()
        var  partMap = HashMap<String,RequestBody>()
        partMap.put("publisherid", "4".toRequestBody())
        partMap.put("title",titleText.text.toString().toRequestBody())
        partMap.put("detail",detailText.text.toString().toRequestBody())
        partMap.put("price",priceText.text.toString().toRequestBody())
        var list=ArrayList<MultipartBody.Part>()
        var selectList=ArrayList<File>()
        for (f in images){
            selectList.add(File(f))
        }
        for (file in selectList){
            var requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
            var formData=MultipartBody.Part.createFormData("file",file.name,requestBody)
            list.add(formData);

        }
        editPostModel.uploadPost(partMap,list).enqueue(object :Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val list= response.body()?.string()
                val jsonObject=JSONObject(list)
                if (list!=null){
                    val status=jsonObject.getString("status")
                    if (status=="success"){
                        val intent=Intent(this@PublishTestActivity,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@PublishTestActivity, "发布成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }

}