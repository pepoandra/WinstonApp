package com.boii.seb.winstonapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //  cardDatabase db = cardDatabase.getInstace(this);
    cardDatabase db = new cardDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drop this database if already exists
        db.onUpgrade(db.getWritableDatabase(), 1, 2);



        db.createCardSlip(new cardSlip("visa", 34, 34.55));
        db.createCardSlip(new cardSlip("Mast", 3, 21.75));
        db.createCardSlip(new cardSlip("derv", 69, 8.2499));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This App was awarded the Matthew Caines Seal of Approval for its outstanding performance.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void clearPreferences (){

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Starting_Money", 0);
        editor.putInt("Total", 0);
        editor.putInt("Drinks", 0);

        editor.commit();

    }


    public void clearAll(View view){

        // drop this database if already exists
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        clearPreferences();
    }

    public void addStartingMoney(View view){

        EditText editText = (EditText) findViewById(R.id.editText2);
        int startingMoney = Integer.parseInt( editText.getText().toString() );

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Starting_Money", startingMoney);
        editor.commit();
    }


    public int getStartingMoney(){
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        return sp.getInt("Starting_Money", -1);
    }



    public void addTotal(View view){

        EditText editText = (EditText) findViewById(R.id.editText3);
        int total = Integer.parseInt( editText.getText().toString() );

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Total", total);
        editor.commit();
    }

    public int getTotal(){
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        return sp.getInt("Total", -1);
    }



    public void addDrinks(View view){

        EditText editText = (EditText) findViewById(R.id.editText4);
        int drinks = Integer.parseInt( editText.getText().toString() );

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Drinks", drinks);
        editor.commit();
    }



    public int getDrinks(){
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        return sp.getInt("Drinks", -1);
    }




    public void viewSlips(View view){

        Intent intent = new Intent(this, cardLists.class);
      //  String message = "puto";
       // intent.putExtra(EXTRA_MESSAGE, message);
        this.startActivity(intent);


    }


    public void addCard( View view){

       EditText editText1 = (EditText) findViewById(R.id.editText5);
        EditText editText2 = (EditText) findViewById(R.id.editText6);
        EditText editText3 = (EditText) findViewById(R.id.editText7);

        String type = editText1.getText().toString();
        int auth = Integer.parseInt(editText2.getText().toString());
        double amount = Double.parseDouble(editText3.getText().toString());

        db.createCardSlip(new cardSlip(type, auth, amount));

    }


    public double getTotalCard(){

        List list = db.getAllCards();
        cardSlip cs;
        double total = 0;

        for (int i = 0; i < list.size(); i++) {
            cs = (cardSlip) list.get(i);
            total = total + cs.getAmount();
        }
        return total;
    }



    public void cashOut(View view) {

        cashOut co = new cashOut(getStartingMoney(), getTotal(),getDrinks(), getTotalCard() );
        Intent intent = new Intent(this, result.class);


        intent.putExtra("cashOut", co);
        startActivity(intent);


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
