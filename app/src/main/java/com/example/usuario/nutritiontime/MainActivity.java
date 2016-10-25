package com.example.usuario.nutritiontime;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    public static final String DB_CREATED = "com.example.usuario.nutritiontime.DB_CREATED";

    private Realm realm;
    private ProgressBar bar;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean wasCreated = sp.getBoolean(DB_CREATED, false);

        realm = Realm.getDefaultInstance();

        ListView list = (ListView) findViewById(R.id.listMovies);
        adapter = new ListAdapter(getApplicationContext());
        list.setAdapter(adapter);

        if (wasCreated) {
            adapter.update();
        } else {
            createDB();
        }

        bar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void createDB() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Movie movie : Dump.movies) {
                    Movie m = realm.createObject(Movie.class);
                    m.setRating(movie.getRating());
                    m.setYear(movie.getYear());
                    m.setThumb(movie.getThumb());
                    m.setTitle(movie.getTitle());
                }
            }

        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                sp.edit().putBoolean(DB_CREATED, true).apply();
                adapter.update();
            }
        });
    }


}
