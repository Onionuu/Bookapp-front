package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.example.myapplication.ui.shouye.IndexFragment
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity1 :AppCompatActivity() {

    private var radioGroup: RadioGroup? = null
    private var mTransaction: FragmentTransaction? = null

    lateinit var syFragment: Fragment
    lateinit var sqFragment: Fragment
    lateinit var xxFragment: Fragment
    lateinit var wdFragment: Fragment
    val VIEW_SHOUYE_INDEX = 0
    val VIEW_SHEQU_INDEX = 1
    val VIEW_XIAOXI_INDEX = 2
    val VIEW_WODE_INDEX = 3
    private var temp_position_index = -1
    var VIEW_LAST_INDEX = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main1)

        initView()
        id_home.setOnClickListener(){
            transview(syFragment!!, VIEW_SHOUYE_INDEX)
        }

    }
    private fun initView() {
        radioGroup = findViewById(R.id.id_navcontent)
        //绑定四个单选框
        syFragment = IndexFragment.getNewInstance()
        //sqFragment = SheQuFragment.getNewInstance()
        //xxFragment = XiaoXiFragment.getNewInstance()
        //wdFragment = WoDeFragment.getNewInstance()
        //初始化选择，并选择首页作为默认选项
        transview(syFragment , VIEW_SHOUYE_INDEX)
    }

    /**
     * 根据多选框选择的项，将对应的页面跟换成对应的页面
     * nowindex:当前选择的下标
     * choose：选择替换的framment
     */
    private fun transview(choose: Fragment, nowindex: Int) {
        if (temp_position_index != nowindex) {
            VIEW_LAST_INDEX = temp_position_index
            mTransaction = supportFragmentManager.beginTransaction()
            mTransaction!!.replace(R.id.id_fragment_content, choose)
            mTransaction!!.commit()
            temp_position_index = nowindex
        }
    }

    /**
     * 当执行点击动作的时候的响应
     */
    fun switchView(view: View) {
        when (view.id) {
            R.id.id_home -> transview(syFragment!!, VIEW_SHOUYE_INDEX)
//            R.id.id_service -> transview(sqFragment, MainActivity.VIEW_SHEQU_INDEX)
//            R.id.id_nav_btxiaoxi -> transview(xxFragment, MainActivity.VIEW_XIAOXI_INDEX)
//            R.id.id_nav_btwode -> transview(wdFragment, MainActivity.VIEW_WODE_INDEX)
            R.id.id_publish -> {
//                if (null == publish) {
//                    publish = Publish(this)
//                }
//                publish.showPublish(view)
//            }
                val intent = Intent(this, PublishTestActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * 通过下标找到对应的ID
     */
//    private fun getID(nowindex: Int): Int {
//        when (nowindex) {
//            VIEW_SHOUYE_INDEX -> return R.id.id_nav_btshouye
//            //MainActivity1.VIEW_SHEQU_INDEX -> return R.id.id_nav_btshequ
//            //MainActivity1.VIEW_XIAOXI_INDEX -> return R.id.id_nav_btxiaoxi
//            //MainActivity1.VIEW_WODE_INDEX -> return R.id.id_nav_btwode
//        }
//        return R.id.id_nav_btshouye
//    }
}