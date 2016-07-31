package edu.sfsu.cs.orange.ocr.DataBase;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by MorcosS on 7/30/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_Reading = "CREATE TABLE "
            + "Reading_Item" + "(" + "Person_ID" + " INTEGER PRIMARY KEY," + "Reading_No"
            + " STRING)";

    public DBHelper(Context context) {
        super(context, "Order_Items", null, 1);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Reading);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOrder (int Person_ID, String Reading_NO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Person_ID",Person_ID);
        values.put("Reading_No", Reading_NO);
        long movie_row = db.insert("Reading_Items", null, values);
        db.close(); // Closing database connection
        if (movie_row==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getOrder() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * "  + " FROM Reading_Items ";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c == null || ! c.moveToFirst()) return null;
        return c;
    }

    public void deleteOrder(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("Rading_Items","Person_ID = "+id,null);
    }

}
