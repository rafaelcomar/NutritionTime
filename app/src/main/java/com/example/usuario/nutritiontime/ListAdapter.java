package com.example.usuario.nutritiontime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Ygor on 15/10/2016.
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private LayoutInflater inflater;
    private Context context;
    private Realm realm;

    public ListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public int getCount() {
        if (movies == null) {
            return 0;
        }
        return movies.size();
    }

    @Override
    public Movie getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView imgThumb = (ImageView) v.findViewById(R.id.imgThumb);
        TextView txtYear = (TextView) v.findViewById(R.id.txtYear);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtGenre = (TextView) v.findViewById(R.id.txtGenre);
        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

        Movie movie = getItem(i);

        txtYear.setText(String.valueOf(movie.getYear()));
        txtTitle.setText(movie.getTitle());
        txtGenre.setText(movie.getGenre());
        ratingBar.setRating(movie.getRating());

        Picasso.with(context).load(movie.getThumb())
                .placeholder(R.drawable.img_filme)
                .into(imgThumb);

        return v;
    }

    public void update() {
        movies = new ArrayList<>();

        RealmQuery<Movie> query = realm.where(Movie.class);
        RealmResults<Movie> result = query.findAll();

        movies.addAll(result);

        notifyDataSetChanged();
    }

}

