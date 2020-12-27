package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Goods

class GoodsAdapter1 (private var goodslist:MutableList<Goods>):RecyclerView.Adapter<GoodsAdapter1.Viewholder>(){
    inner class Viewholder(view:View):RecyclerView.ViewHolder(view){//将各个组件与数据进行绑定
        val goodsImage:ImageView=view.findViewById(R.id.GoodsImage1)
        val goodsName: TextView = view.findViewById(R.id.GoodsName1)
        val goodsPrice: TextView = view.findViewById(R.id.GoodsPrice1)
        fun bind(goods: Goods){
            goodsName.text=goods.title.capitalize()
            goodsPrice.text="￥"+goods.price.capitalize()
            Glide.with(goodsImage.context).load(goods.image).into(goodsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goods_item1,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val goods = goodslist[position]
//        println(goods.title)
//        val file = File(goods.image)
//        val bitmap = BitmapFactory.decodeFile(file.toString())
//        holder.goodsImage.setImageBitmap(bitmap)
//        holder.goodsName.text=goods.title
//        holder.goodsPrice.text=goods.price.toString()
        holder.bind(goods)
        holder.itemView.setOnClickListener (View.OnClickListener {
            fun onclick(view: View) {

            }
        })

    }

    override fun getItemCount(): Int {
        return goodslist.size
    }

    fun update(data:MutableList<Goods>){
        goodslist=data
        notifyDataSetChanged()
    }
}