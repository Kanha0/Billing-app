package com.example.BillingApp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DataTable.class, CustomerDetails.class}, version = 2 , exportSchema = false)
public abstract class RoomDatabaseData extends RoomDatabase {

    public abstract DaoData daoData();
    private static volatile RoomDatabaseData INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static final ExecutorService databaseCustomerWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriterExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                DaoData dao = INSTANCE.daoData();
//                dao.deleteAll();
//
//                DataTable word = new DataTable(0,);
//                dao.insertData(word);
//
//            });
//        }
//    };
    //add this .addCallback(sRoomDatabaseCallback) call back inside if before .build();



    static RoomDatabaseData getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabaseData.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabaseData.class, "data_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
