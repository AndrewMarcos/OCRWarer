package edu.sfsu.cs.orange.ocr.Services;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;

import edu.sfsu.cs.orange.ocr.DataBase.DBHelper;

/**
 * Created by MorcosS on 7/31/16.
 */
public class sendService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public sendService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getOrder();
    }
}
