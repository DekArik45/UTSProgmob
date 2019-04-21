package com.example.tugasuts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tugasuts.DetailActivity;
import com.example.tugasuts.R;
import com.example.tugasuts.model.Makanan;

import java.util.ArrayList;

public class GridMakananAdapater extends RecyclerView.Adapter<GridMakananAdapater.GridViewHolder> {

    private Context context;
    private ArrayList<Makanan> listMakanan;

    public GridMakananAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_makanan, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, final int position) {
        Glide.with(context)
                .load(getListMakanan().get(position).getFoto())
                .apply(new RequestOptions())
                .into(gridViewHolder.imgPhoto);

        gridViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
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

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.item_grid_foto);
        }
    }
}
