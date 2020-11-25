package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.shouye.IndexFragment
import java.lang.IllegalArgumentException

class Msg(val head: Int, val content:String, val name:String){
    //定义静态成员
};
class MessageFragment:Fragment() {
    var rootView: View? =null
    private val msgList = ArrayList<Msg>()
    lateinit var recyclerView : RecyclerView
    companion object{
        var messageFragment: MessageFragment? = null
        fun getNewInstance(): MessageFragment {
            if (messageFragment == null) {
                messageFragment = MessageFragment()
            }
            return messageFragment!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView==null){
            rootView=inflater.inflate(R.layout.fragment_message,container,false)
        }
        val parent: ViewGroup? = rootView?.parent as ViewGroup?
        if(parent!=null){
            parent.removeView(rootView)
        }


        return rootView
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
   //建立消息数据列表

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
          //隐藏顶部状态栏
        recyclerView= view!!.findViewById(R.id.recyclerView)
        initMsg()   //初始化
        recyclerView.layoutManager = LinearLayoutManager(context)  //布局为线性垂直
        val adapter = MsgAdapter(msgList)   //建立适配器实例
        recyclerView.adapter = adapter  //传入适配器
    }

    fun initMsg(){
        msgList.add(Msg(R.drawable.head1,"蔡师傅工作室2号店","支持面交，可以线下交易"))
        msgList.add(Msg(R.drawable.head2,"耗子尾汁","不讲武德"))
    }
}
class MsgAdapter(val msgList:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class tViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tHead: ImageView = view.findViewById(R.id.Head)
        val tName: TextView = view.findViewById(R.id.Name)
        val tMsg: TextView = view.findViewById(R.id.Text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val tView = LayoutInflater.from(parent.context).inflate(R.layout.msg_layout, parent, false)
        return tViewHolder(tView) //返回控件+布局
    }

    //对聊天控件的消息文本进行赋值
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is tViewHolder -> {
                holder.tHead.setBackgroundResource(msg.head)
                holder.tMsg.text = msg.name
                holder.tName.text = msg.content
            }
            else -> throw IllegalArgumentException()
        }
    }

    //返回项数
    override fun getItemCount(): Int {
        return msgList.size
    }
}