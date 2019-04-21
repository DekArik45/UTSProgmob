package com.example.tugasuts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Makanan implements Parcelable {
    private String publisher, title, foto, desc, recipe, method;

    public Makanan() {
    }

    public Makanan(String publisher, String tiltle, String foto, String desc, String recipe, String method) {
        this.publisher = publisher;
        this.title = tiltle;
        this.foto = foto;
        this.desc = desc;
        this.recipe = recipe;
        this.method = method;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tiltle) {
        this.title = tiltle;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.publisher);
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.foto);
        dest.writeString(this.method);
        dest.writeString(this.recipe);
    }

    protected Makanan(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.foto = in.readString();
        this.publisher = in.readString();
        this.recipe = in.readString();
        this.method = in.readString();

    }
    public static final Parcelable.Creator<Makanan> CREATOR = new Parcelable.Creator<Makanan>() {
        @Override
        public Makanan createFromParcel(Parcel source) {
            return new Makanan(source);
        }
        @Override
        public Makanan[] newArray(int size) {
            return new Makanan[size];
        }
    };
}

