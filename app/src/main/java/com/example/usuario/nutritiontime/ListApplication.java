package com.example.usuario.nutritiontime;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Usuário on 15/10/2016.
 */

public class ListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }


}
