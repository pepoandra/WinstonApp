package com.boii.seb.winstonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class cardActivity extends Activity {
    TextView cardType;
    TextView cardAuth;
    TextView cardAmount;
    cardSlip selectedCard;
    cardDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);

        cardType = (TextView) findViewById(R.id.title);
        cardAuth = (TextView) findViewById(R.id.author);
        cardAmount  = (TextView) findViewById(R.id.amount);;


        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        cardSlip cs = (cardSlip) getIntent().getSerializableExtra("cs");

        int id = intent.getIntExtra("card", -1);

        // open the database of the application context
        db = new cardDatabase(getApplicationContext());

        // read the book with "id" from the database
        selectedCard = cs;

        initializeViews();
    }

    public void initializeViews() {
        cardType.setText(selectedCard.getType());
        cardAuth.setText(Integer.toString(selectedCard.getAuth()));
        cardAmount.setText(Double.toString(selectedCard.getAmount()));

    }

    public void update(View v) {
        Toast.makeText(getApplicationContext(), "This card is updated.", Toast.LENGTH_SHORT).show();

        String t, au, am;

        t = ((EditText) findViewById(R.id.typeEdit)).getText().toString();
        au = (((EditText) findViewById(R.id.authEdit)).getText()).toString();
        am = (((EditText) findViewById(R.id.amountEdit)).getText()).toString();



        if(! TextUtils.isEmpty(t)){
            selectedCard.setType(t);
        }

        if(! TextUtils.isEmpty(au)){
            selectedCard.setAuth(Integer.parseInt(au));
        }

        if(! TextUtils.isEmpty(am)){
            selectedCard.setAmount(Double.parseDouble(am));
        }

        // update book with changes
        db.updateCardSlip(selectedCard);
        finish();
    }

    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This card is deleted.", Toast.LENGTH_SHORT).show();

        // delete selected book
        db.deleteCardSlip(selectedCard);
        finish();
    }
}