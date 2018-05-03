package com.test.fixapp_tech.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.fixapp_tech.R;

import java.util.ArrayList;

public class MyJobListAdapter  extends ArrayAdapter{
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<MyJobsModel> myJobList;

    public MyJobListAdapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<MyJobsModel> myJobList) {
        super(context, layoutResId, myJobList);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.myJobList = myJobList;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        MyJobsModel item = myJobList.get(position);

        ImageView picStatus = itemLayout.findViewById(R.id.imageViewStatus_Myjob);
        ImageView picProduct = itemLayout.findViewById(R.id.imageViewProduct_Myjob);
        TextView orderTitle = itemLayout.findViewById(R.id.textViewTitle_Myjob);
        TextView orderDetail = itemLayout.findViewById(R.id.textViewDetail_Myjob);
        TextView orderDate = itemLayout.findViewById(R.id.textViewDate_Myjob);

        orderTitle.setText(item.title);
        orderDetail.setText(item.detail);
        orderDate.setText(item.date);
        picProduct.setImageResource(item.pictureProduct);
        picStatus.setImageResource(item.pictureStatus);

        return itemLayout;
    }
}
