package com.example.zz.parkpark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ZZ on 2015/5/10.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private String[] mDataset;
    private int[] mImgId;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;

        public Context mContext;
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.address_name);
            mImageView = (ImageView)v.findViewById(R.id.address_image);
            mContext = v.getContext();
            mView = v;
            /*v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PayActivity.class);
                    //Bundle bundle = new Bundle();
                    //bundle.putInt("POS", extra_pos);
                    //Toast.makeText(mContext, "List pos"+extra_pos, Toast.LENGTH_LONG).show();
                    //intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });*/
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(String[] myDataset, int[] myImgId) {
        mDataset = myDataset; mImgId = myImgId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
        holder.mImageView.setImageResource(mImgId[position]);

        final Context mContext = holder.mContext;
        final int pos = position;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("POS", pos);
                //Toast.makeText(mContext, "List pos"+pos, Toast.LENGTH_LONG).show();
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
