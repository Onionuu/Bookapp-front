//package com.example.myapplication.ui.shouye;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.myapplication.R;
//
//import java.util.ArrayList;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import java.util.logging.LogRecord;
//
//
//public class image_switch extends AppCompatActivity {
//    private ArrayList<ImageView> imageViews= new ArrayList<>();
//    private ArrayList<View>dots=new ArrayList<>();
//    private ViewPager vp;
//    private int image[]={R.drawable.xiazai1,R.drawable.xiazai2,R.drawable.xiazai3};
//    private int oldPosition=0;
//    private int currentItem;
//    private ScheduledExecutorService scheduledExecutorService;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//        //获取图片资源
//        for (int i=0;i<image.length;i++){
//            ImageView imageView=new ImageView(this);
//            imageView.setImageResource(image[i]);
//            imageViews.add(imageView);
//        }
//        //显示的点的集合
//        dots.add(findViewById(R.id.p1));
//        dots.add(findViewById(R.id.p2));
//        dots.add(findViewById(R.id.p3));
//
//        vp=findViewById(R.id.viewContent);
//        vp.setAdapter(new ViewPagerAdapter());
//        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                //点的效果切换
//                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
//                dots.get(position).setBackgroundResource(R.drawable.dot_focus);
//                oldPosition=position;
//                currentItem=position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//    class ViewPagerAdapter extends PagerAdapter {
//        @Override
//        public int getCount() {
//            return imageViews.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return view==object;
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            View v=imageViews.get(position);
//            container.removeView(v);
//        }
//
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            View v=imageViews.get(position);
//            container.addView(v);
//            return v;
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,2, TimeUnit.SECONDS);
//    }
//    class ViewPagerTask implements Runnable{
//
//        @Override
//        public void run() {
//            currentItem=(currentItem+1)%image.length;
//            handler.obtainMessage().sendToTarget();
//        }
//    }
//    @SuppressLint("HandlerLeak")
//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            vp.setCurrentItem(currentItem);
//        }
//    };
//}