package com.boii.seb.winstonapp;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.boii.seb.winstonapp.cardSlip;

public class cardDatabase extends SQLiteOpenHelper {

    private static cardDatabase sInstance;


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "CardDB";
    private static final String table_CARDS = "cards";
    private static final String card_ID = "id";
    private static final String card_TYPE = "type";
    private static final String card_AUTH = "auth";
    private static final String card_AMOUNT = "amount";


    private static final String[] COLUMNS = { card_ID, card_TYPE, card_AUTH, card_AMOUNT };




    public cardDatabase(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CARD_TABLE = "CREATE TABLE cards ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "type TEXT, " + "auth INTEGER, " +
            "amount REAL )";
        db.execSQL(CREATE_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        db.execSQL("DROP TABLE IF EXISTS cards");
        this.onCreate(db);
    }

    public void createCardSlip(cardSlip cs) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(card_TYPE, cs.getType());
        values.put(card_AUTH, cs.getAuth());
        values.put(card_AMOUNT, cs.getAmount());


        // insert book
        db.insert(table_CARDS, null, values);

        // close database transaction
        db.close();
    }

    public cardSlip readSlip(int id) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get book query
        Cursor cursor = db.query(table_CARDS, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();

        cardSlip cs = new cardSlip();
        cs.setId(Integer.parseInt(cursor.getString(0)));
        cs.setType(cursor.getString(1));
        cs.setAuth(Integer.parseInt(cursor.getString(2)));
        cs.setAmount(Double.parseDouble(cursor.getString(3)));


        return cs;
    }

    public List getAllCards() {
        List slips  = new LinkedList();

        // select book query
        String query = "SELECT  * FROM " + table_CARDS;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        cardSlip cs = null;
        if (cursor.moveToFirst()) {
            do {
                cs = new cardSlip();
                cs.setId(Integer.parseInt(cursor.getString(0)));
                cs.setType(cursor.getString(1));
                cs.setAuth(Integer.parseInt(cursor.getString(2)));
                cs.setAmount(Double.parseDouble(cursor.getString(3)));

                // Add book to books
                slips.add(cs);
            } while (cursor.moveToNext());
        }
        return slips;
    }

    public int updateCardSlip(cardSlip cs) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("type", cs.getType()); // get type
        values.put("auth", cs.getAuth()); // get auth #
        values.put("amount", cs.getAmount()); // get amount


        // update
        int i = db.update(table_CARDS, values, card_ID + " = ?", new String[] { String.valueOf(cs.getId()) });

        db.close();
        return i;
    }

    // Deleting single book
    public void deleteCardSlip(cardSlip cs) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete book
        db.delete(table_CARDS, card_ID + " = ?", new String[] { String.valueOf(cs.getId()) });
        db.close();
    }
}