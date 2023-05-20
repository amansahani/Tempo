package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Display;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    public static final String ID = "id ";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String IS_ACTIVE = "isActive";
    public static final String CUSTOMER = "CustomerTable";


    public DB(@Nullable Context context) {
        super(context, CUSTOMER + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CUSTOMER + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                AGE + " TEXT, " +
                IS_ACTIVE + " BOOL" +
                ")";
        db.execSQL(createTable);
    }
    public boolean addOne(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, model.getName());
        cv.put(AGE,model.getAge());
        cv.put(IS_ACTIVE, model.isActive());

        long insert = db.insert(CUSTOMER,null, cv);
        return insert > 0;
    }

    public List<Model> getEveryone(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Model> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER;
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String age = cursor.getString(2);
                boolean isActive = Boolean.parseBoolean(cursor.getString(3).toString());
                Model newCustomer = new Model(id,name,Integer.parseInt(age), isActive);
                returnList.add(newCustomer);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
    public boolean delete(int i ){
       try{
           String deleteQuery = "DELETE FROM " + CUSTOMER+" WHERE id = '"+ i +"'";
           SQLiteDatabase db = this.getWritableDatabase();
           db.execSQL(deleteQuery);
       }catch(Exception e){
           return false;
       }
       return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
