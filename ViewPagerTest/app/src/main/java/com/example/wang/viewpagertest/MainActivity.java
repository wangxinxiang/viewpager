package com.example.wang.viewpagertest;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wang.viewpagertest.util.ImageUtil;
import com.example.wang.viewpagertest.util.MyLogger;
import com.example.wang.viewpagertest.view.RotateDownPageTransformer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageView> imageViewList;
    private List<Integer> mImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);

        initImage();
        initImageView();
        viewPager.setOffscreenPageLimit(3);
        int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp=viewPager.getLayoutParams();
        if (lp==null){
            lp=new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        }else {
            lp.width= pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(-50);
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
        viewPager.setPageTransformer(true,new RotateDownPageTransformer(viewPager));
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewList.get(position));
                Logger.d("PagerAdapter");
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=imageViewList.get(position);
                container.addView(imageView,position);
                return imageView;
            }
        });
    }

    private void initImage() {
        mImageList = new ArrayList<>();
        mImageList.add(R.drawable.first);
        mImageList.add(R.drawable.second);
        mImageList.add(R.drawable.third);
        mImageList.add(R.drawable.fourth);
        mImageList.add(R.drawable.fifth);
    }

    private void initImageView() {
        imageViewList=new ArrayList<>();
        for (int i = 0; i < mImageList.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            Bitmap bitmap = ImageUtil.readBitMap(this, mImageList.get(i));
            Bitmap bitmap = ImageUtil.getReverseBitmapById(mImageList.get(i), this);
            imageView.setImageBitmap(bitmap);
            imageViewList.add(imageView);
            MyLogger.d("test","MyLogger");
        }
    }
}
