package com.example.myapplication

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class GoodsAdapter(activity: Activity, val resourceId: Int, data: List<Goods>) : ArrayAdapter<Goods>(activity, resourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val goodsImage: ImageView = view.findViewById(R.id.GoodsImage)
            val goodsName: TextView = view.findViewById(R.id.GoodsName)
            val goodsPrice: TextView = view.findViewById(R.id.GoodsPrice)
            viewHolder = ViewHolder(goodsImage, goodsName,goodsPrice)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val goods = getItem(position) // 获取当前项的Goods实例
        if (goods != null) {

            val file = goods.image
            val bitmap = BitmapFactory.decodeFile(file.toString())
            viewHolder.GoodsImage.setImageBitmap(bitmap)
            viewHolder.GoodsName.text = goods.name
            viewHolder.GoodsPrice.text=goods.price.toString()
        }
        return view
    }

    inner class ViewHolder(val GoodsImage: ImageView, val GoodsName: TextView,val GoodsPrice:TextView)

}