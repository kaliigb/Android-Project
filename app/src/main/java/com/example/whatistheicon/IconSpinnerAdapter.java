package com.example.whatistheicon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class IconSpinnerAdapter extends BaseAdapter {
    Context context;
    private List<IconSign> mData;
    private
    LayoutInflater inflter;

    public IconSpinnerAdapter(Context applicationContext, List<IconSign> mData) {
        this.context = applicationContext;
        this.mData = mData;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_item, null);
        ImageView icon = view.findViewById(R.id.iconImageView);
        TextView description = view.findViewById(R.id.nameTextView);
        Picasso.get().load(mData.get(i).getIcon()).into(icon);
        description.setText(mData.get(i).getDescription());
        return view;
    }
}