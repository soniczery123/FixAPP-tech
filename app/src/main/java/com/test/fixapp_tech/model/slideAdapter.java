package com.test.fixapp_tech.model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

import com.test.fixapp_tech.R;

/**
 * Created by MSI-GL62 on 27/4/2561.
 */

public class slideAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater layoutInflater;
    public int[] slide_pic = {
        R.drawable.step1,
        R.drawable.step2,
        R.drawable.step3
    };

    public slideAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public int getCount(){
        return slide_pic.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == (RelativeLayout) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container , int position){
        layoutInflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

       ImageView img = (ImageView)view.findViewById(R.id.imageViewSlide);
        img.setBackgroundResource(slide_pic[position]);

        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}
