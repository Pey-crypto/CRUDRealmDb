package com.securewk.realmdb.database;
import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class RealmConfig extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);


        RealmConfiguration configuration = new RealmConfiguration.Builder().name("books.realm").build();
        Realm.setDefaultConfiguration(configuration);
    }


}
