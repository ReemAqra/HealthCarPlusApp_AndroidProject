package com.example.healthcarplus_app;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.healthcarplus_app.admin.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private List<product> ProductList;



    public RecycleAdapter(Context context, List<product> productList ) {
        this.context = context;
        this.ProductList = productList;
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


        Glide.with(context).load(ProductList.get(position).getpImage()).into(holder.productImage);
        holder.productName.setText(ProductList.get(position).getpName());
        holder.productPrice.setText(ProductList.get(position).getpPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DitailActivity.class);
                intent.putExtra("pImage", ProductList.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("pDescription", ProductList.get(holder.getAdapterPosition()).getpDescription());
                intent.putExtra("pName", ProductList.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("pPrice",ProductList.get(holder.getAdapterPosition()).getpPrice());
                intent.putExtra("pNumber", ProductList.get(holder.getAdapterPosition()).getpNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return ProductList.size();
    }
    public void searchDataList(ArrayList<product> searchList){
        ProductList = searchList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView productName ,productPrice;
        private ImageView productImage;

        public ViewHolder(CardView itemView){
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.productImage);
            cardView = itemView.findViewById(R.id.cardView);
        }

    }
}
