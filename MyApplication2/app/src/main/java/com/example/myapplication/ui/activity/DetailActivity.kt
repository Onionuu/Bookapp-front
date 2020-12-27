package com.example.myapplication.ui.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Photo
import com.example.myapplication.PhotoAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_good_detail.*
import java.net.URI

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_good_detail)
        val list=ArrayList<Photo>()
        list.add( Photo(Uri.parse("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2422437459,525040718&fm=26&gp=0.jpg")))
        list.add( Photo(Uri.parse("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2422437459,525040718&fm=26&gp=0.jpg")))
        val adapter=PhotoAdapter(list)
        good_detail_recycle.layoutManager = LinearLayoutManager(this)
        good_detail_recycle.adapter=adapter

    }
}