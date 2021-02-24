package com.example.msg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class SneakerListAdapter extends ArrayAdapter<Sneaker> {

    Context context;
    int resource;
    List<Sneaker> sneakers;
    TextView brand, name;
    ImageView imageView;

    public SneakerListAdapter(@NonNull Context context, int resource, @NonNull List<Sneaker> sneakers) {
        super(context, resource, sneakers);
        this.context = context;
        this.resource = resource;
        this.sneakers = sneakers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null);
        brand = view.findViewById(R.id.sneakerBrandTextView);
        name = view.findViewById(R.id.sneakerNameTextView);
        imageView = view.findViewById(R.id.sneakerImageView);
        Sneaker sneaker = sneakers.get(position);
        brand.setText(sneaker.brand);
        name.setText(sneaker.name);
        new ImageDownload(imageView).execute(sneaker.image);

        return view;
    }
}
