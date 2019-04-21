package com.example.tugasuts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tugasuts.DetailActivity;
import com.example.tugasuts.R;
import com.example.tugasuts.model.Makanan;

import java.util.ArrayList;

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Makanan> listMakanan;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_makanan, viewGroup, false);
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

        categoryViewHolder.itemList.setOnClickListener(new View.OnClickListener() {
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
    }

    public ListMakananAdapter(Context context) {
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
        TextView itemName, itemDesc;
        ImageView imgPhoto;
        ConstraintLayout itemList;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_makanan_nama);
            itemDesc = itemView.findViewById(R.id.item_makanan_desc);
            imgPhoto = itemView.findViewById(R.id.item_makanan_foto);
            itemList = itemView.findViewById(R.id.item_list_layout);
        }
    }


}
