package com.example.myapplication.ui.shouye

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.*
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shouye.*
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
        repeat(3){
            goodslist.add(Goods("8成新口腔颌面外科学 人民卫生出版社",File("/sdcard/Pictures/xiazai2.jpg"),"￥10"))
            goodslist.add(Goods("9成新生物化学与生物分子学 人民卫生出版社 华师石牌面交交交交交",File("/sdcard/Pictures/xiazai1.jpg"),"￥9.5"))
            goodslist.add(Goods("8成新c程序设计",File("/sdcard/Pictures/xiazai3.jpg"),"￥10"))
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
        images.add("http://img.mp.itc.cn/upload/20160617/3fd573ef3a9a4fd0abec604cf8fd6364_th.png")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606313437590&di=0cc661ad0b2f5ff2f00d6173154cfa52&imgtype=0&src=http%3A%2F%2Fdpic.tiankong.com%2Fcs%2Fa0%2FQJ7104628743.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606313476010&di=d443415c73d69404aa39e6a5bc928824&imgtype=0&src=http%3A%2F%2Fwww.chinabuildingcentre.com%2Fuploadfile%2F2013%2F1106%2F20131106052826778.jpg")
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


        val search=view?.findViewById(R.id.index_search) as EditText
        search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event -> //当actionId == XX_SEND 或者 XX_DONE时都触发
            //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || event != null && KeyEvent.KEYCODE_ENTER == event.keyCode && KeyEvent.ACTION_DOWN == event.action
            ) {
                //处理事件

                val data=search.text.toString()
                val intent= Intent(context, SearchResultActivity::class.java)
                intent.putExtra("words",data)
                startActivity(intent)
            }
            false
        })
    }
}