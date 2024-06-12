package com.example.whatistheicon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Item item = items.get(position);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        ImageView imageView = view.findViewById(R.id.imageView);

        titleTextView.setText(item.getTitle());
        Picasso.get().load(item.getImageUrl()).into(imageView);

        return view;
    }
}
