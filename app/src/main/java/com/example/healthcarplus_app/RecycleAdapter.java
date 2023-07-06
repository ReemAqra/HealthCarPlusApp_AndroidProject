package com.example.healthcarplus_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcarplus_app.admin.SearchActivity;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private int[] Image;
    private String[] Name;
    private String[] Price;

    public RecycleAdapter(int[] image, String[] Name ,String[] price   ) {
        this.Image=image;
        this.Name=Name;
        this.Price=price;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView productImage = (ImageView) cardView.findViewById(R.id.productImage);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), Image[position]);
        productImage.setImageDrawable(dr);
        TextView productName = (TextView)cardView.findViewById(R.id.productName);
        TextView productprice = (TextView)cardView.findViewById(R.id.productprice);

        productName.setText(Name[position]);
        productprice.setText(Price[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {

        return Name.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}
