package com.example.myapplication.ui.shouye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.GlideImageLoader
import com.example.myapplication.Goods
import com.example.myapplication.GoodsAdapter1
import com.example.myapplication.R
import com.youth.banner.Banner
import java.io.File


class IndexFragment:Fragment() {
     var rootView: View? =null

    lateinit var banner: Banner

    private val goodslist=ArrayList<Goods>()

    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun initGoods(){
        repeat(9){

            goodslist.add(Goods("真好看",File("/sdcard/Pictures/touxiang.jpg"),"30"))
        }
    }


    companion object{
        var indexFragment: IndexFragment? = null
        fun getNewInstance(): IndexFragment {
            if (indexFragment == null) {
                indexFragment = IndexFragment()
            }
            return IndexFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView==null){
            rootView=inflater.inflate(R.layout.fragment_shouye,container,false)
        }
        val parent:ViewGroup? = rootView?.parent as ViewGroup?
        if(parent!=null){
            parent.removeView(rootView)
        }
        return rootView
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        //
        val images = ArrayList<String>()
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg")
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg")
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg")
        banner = view?.findViewById(R.id.banner) as Banner
        //设置图片加载器
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        //设置图片集合
        banner.setImages(images)
        //banner设置方法全部调用完毕时最后调用
        //banner设置方法全部调用完毕时最后调用
        banner.start()
        initGoods()
        recyclerView= view!!.findViewById(R.id.index_recycleview)
        val layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager=layoutManager
        val adapter1=GoodsAdapter1(goodslist)
        recyclerView.adapter=adapter1
    }
}