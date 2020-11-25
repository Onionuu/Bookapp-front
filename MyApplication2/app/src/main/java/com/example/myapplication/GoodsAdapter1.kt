package com.example.myapplication

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoodsAdapter1 (val goodslist:List<Goods>):RecyclerView.Adapter<GoodsAdapter1.Viewholder>(){
    inner class Viewholder(view:View):RecyclerView.ViewHolder(view){
        val goodsImage:ImageView=view.findViewById(R.id.GoodsImage1)
        val goodsName: TextView = view.findViewById(R.id.GoodsName1)
        val goodsPrice: TextView = view.findViewById(R.id.GoodsPrice1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goods_item1,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val goods = goodslist[position]
        val file = goods.image
        val bitmap = BitmapFactory.decodeFile(file.toString())
        holder.goodsImage.setImageBitmap(bitmap)
        holder.goodsName.text=goods.name
        holder.goodsPrice.text=goods.price.toString()
    }

    override fun getItemCount(): Int {
        return goodslist.size
    }
}