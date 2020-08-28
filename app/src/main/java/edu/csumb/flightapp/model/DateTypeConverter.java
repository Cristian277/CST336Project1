package edu.csumb.flightapp.model;

import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }

}
