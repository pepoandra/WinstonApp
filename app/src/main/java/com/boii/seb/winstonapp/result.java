package com.boii.seb.winstonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8;

    private final double BUSBOY_TIP_OUT = 0.026;
    private final double HOUSE_TIP_OUT = 0.026;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        cashOut co = (cashOut) getIntent().getSerializableExtra("cashOut");


        t1 = (TextView) findViewById(R.id.textView5);
        t2 = (TextView) findViewById(R.id.textView6);
        t3 = (TextView) findViewById(R.id.textView7);
        t4 = (TextView) findViewById(R.id.textView8);
        t5 = (TextView) findViewById(R.id.textView9);
        t6 = (TextView) findViewById(R.id.textView10);
        t7 = (TextView) findViewById(R.id.textView11);
        t8 = (TextView) findViewById(R.id.textView12);

        String str1, str2, str3, str4, str5, str6, str7, str8;

        str1 = "Starting Money: $" + co.getStartingMoney();
        str2 = "Total: $" + co.getTotal();
        str3 = "Alcohol: $" + co.getAlcohol();
        str4 = "Food: $" + (co.getTotal() - co.getAlcohol());
        str5 = "Total Cards: $" + co.getCardTotal();
        str6 = "Total Cash: $" + (co.getTotal() + co.getStartingMoney() - co.getAlcohol());
        str7 = "Busboy Tip Out: $" + (co.getTotal() * BUSBOY_TIP_OUT);
        str8 = "House Tip Out: $" + (co.getTotal() * HOUSE_TIP_OUT);


        t1.setText(str1);
        t2.setText(str2);
        t3.setText(str3);
        t4.setText(str4);
        t5.setText(str5);
        t6.setText(str6);
        t7.setText(str7);
        t8.setText(str8);

    }
}
