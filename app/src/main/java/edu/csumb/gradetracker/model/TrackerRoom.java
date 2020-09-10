package edu.csumb.gradetracker.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities={User.class,Course.class,Assignment.class,Grade.class}, version=1)
@TypeConverters({DateTypeConverter.class})

public abstract class TrackerRoom extends RoomDatabase {
    // singleton
    private static TrackerRoom instance;

    public abstract TrackerDao dao();

    public static TrackerRoom getTrackerRoom(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TrackerRoom.class,
                    "TrackerDB") // database name
                    .allowMainThreadQueries()  // temporary for now
                    .build();
        }
        return instance;
    }
}
