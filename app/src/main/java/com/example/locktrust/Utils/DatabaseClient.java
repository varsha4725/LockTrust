package com.example.locktrust.Utils;

import android.content.Context;



public class DatabaseClient {
    private Context mCtx;
    private static com.example.locktrust.Utils.DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
      //  appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "Acetute").build();
    }

    public static synchronized com.example.locktrust.Utils.DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new com.example.locktrust.Utils.DatabaseClient(mCtx);
        }
        return mInstance;
    }
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
