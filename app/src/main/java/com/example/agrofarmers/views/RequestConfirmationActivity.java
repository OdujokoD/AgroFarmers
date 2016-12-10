package com.example.agrofarmers.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agrofarmers.R;
import com.example.agrofarmers.views.RequestActivity;

public class RequestConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirmation);

        Intent intent = getIntent();
        String productAndWeight = intent.getStringExtra("productAndWeight");
        String transportType = intent.getStringExtra("transport");

        TextView product_weight_obj = (TextView)findViewById(R.id.goods_to_transport);
        TextView transport = (TextView)findViewById(R.id.transport_type);

        product_weight_obj.setText(productAndWeight);
        transport.setText(transportType);

        Button completeProcess = (Button)findViewById(R.id.process_complete);
        completeProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                startActivity(intent);
            }
        });


    }
}
