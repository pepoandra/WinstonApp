package com.boii.seb.winstonapp;


        import java.util.ArrayList;
        import java.util.List;

        import android.app.ListActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

public class cardLists extends ListActivity implements OnItemClickListener {
    cardDatabase db = new cardDatabase(this);
    List list;
    ArrayAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_list);


        // get all cardSlips
        list = db.getAllCards();
        List listTitle = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).toString());
        }

        myAdapter = new ArrayAdapter(this, R.layout.activity_card_list, R.id.rowTextView, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
        // start cardSlipActivity with extras the cardSlip id
        Intent intent = new Intent(this, cardActivity.class);

        intent.putExtra("cs", (cardSlip) list.get(arg2));


       // intent.putExtra("cardSlip", list.get(arg2).toString());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get all cardSlips again, because something changed
        list = db.getAllCards();

        List listTitle = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).toString());
        }

        myAdapter = new ArrayAdapter(this, R.layout.activity_card_list, R.id.rowTextView, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
}