package com.example.tugasuts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tugasuts.DetailActivity;
import com.example.tugasuts.R;
import com.example.tugasuts.model.Makanan;

import java.util.ArrayList;

public class CardMakananAdapter extends RecyclerView.Adapter<CardMakananAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Makanan> listMakanan;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_makanan, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        categoryViewHolder.itemName.setText(getListMakanan().get(position).getTitle());
        categoryViewHolder.itemDesc.setText(getListMakanan().get(position).getDesc());
        Glide.with(context)
                .load(getListMakanan().get(position).getFoto())
                .apply(new RequestOptions())
                .into(categoryViewHolder.imgPhoto);

        categoryViewHolder.itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), DetailActivity.class);
                i.putExtra("title",getListMakanan().get(position).getTitle());
                i.putExtra("desc",getListMakanan().get(position).getDesc());
                i.putExtra("foto",getListMakanan().get(position).getFoto());
                i.putExtra("recipe", getListMakanan().get(position).getRecipe());
                i.putExtra("method", getListMakanan().get(position).getMethod());
                context.startActivity(i);
            }
        });

        categoryViewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getListMakanan().get(position).getTitle()+" Telah Menjadi Favorite", Toast.LENGTH_SHORT).show();
            }
        });

        categoryViewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getListMakanan().get(position).getTitle()+" Telah di Share ke semua social media", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public CardMakananAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return getListMakanan().size();
    }

    public ArrayList<Makanan> getListMakanan() {
        return listMakanan;
    }

    public void setListMakanan(ArrayList<Makanan> listMakanan) {
        this.listMakanan = listMakanan;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView itemcard;
        TextView itemName, itemDesc;
        ImageView imgPhoto;
        Button btnFavorite, btnShare;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemcard = itemView.findViewById(R.id.item_cardview);
            imgPhoto = itemView.findViewById(R.id.item_card_foto);
            itemName = itemView.findViewById(R.id.item_card_name);
            itemDesc= itemView.findViewById(R.id.item_card_desc);
            btnFavorite = itemView.findViewById(R.id.item_card_favorite);
            btnShare = itemView.findViewById(R.id.item_card_share);
        }
    }


}
