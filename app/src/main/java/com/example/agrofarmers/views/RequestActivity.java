package com.example.agrofarmers.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.agrofarmers.R;

import java.util.ArrayList;

import com.example.agrofarmers.controllers.DBHelper;
import com.example.agrofarmers.controllers.RecyclerAdapter;
import com.example.agrofarmers.model.Request;

public class RequestActivity extends AppCompatActivity implements RecyclerAdapter.ClickListener  {

    private String DIALOG_DETAILS= "details";
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Request> requests = new ArrayList<>();
    private Request requestObject;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                NewRequestDialog dialog = new NewRequestDialog();
                dialog.show(fm, DIALOG_DETAILS);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        updateRequestList();

        recyclerView = (RecyclerView) findViewById(R.id.request_list);
        adapter = new RecyclerAdapter(getApplicationContext(), requests);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void updateRequestList()
    {
        dbHelper = new DBHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = dbHelper.showRequest();
        cursor.moveToFirst();

        int cursorSize = cursor.getCount();


        if(cursorSize != 0)
        {
            while(!cursor.isAfterLast())
            {
                int request_id = cursor.getInt(0);
                String product = cursor.getString(1);
                int weight = cursor.getInt(2);
                String transport_type = cursor.getString(3);
                String status = cursor.getString(4);

                requestObject = new Request(request_id, product, weight, transport_type, status);
                requests.add(requestObject);

                cursor.moveToNext();
            }

            dbHelper.close();
        }
    }

    @Override
    public void itemClicked(View view, int position) {
        Request request = requests.get(position);
        String productWeight = String.valueOf(request.getWeight() + "Kg of " + request.getProduct());
        String transportType = String.valueOf(request.getTransport_type());

        Intent intent = new Intent(getApplicationContext(), RequestConfirmationActivity.class);
        intent.putExtra("productAndWeight", productWeight);
        intent.putExtra("transport", transportType);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "It's working", Toast.LENGTH_SHORT).show();
    }
}