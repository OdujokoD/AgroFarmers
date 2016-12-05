package com.example.agrofarmers;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import controllers.DBHelper;

public class NewRequestDialog extends DialogFragment
{

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_new_request_dialog, null);
        final AutoCompleteTextView productTextView = (AutoCompleteTextView) view.findViewById(R.id.product);
        final AutoCompleteTextView weightTextView = (AutoCompleteTextView) view.findViewById(R.id.weight);
        final Spinner transportSpinner = (Spinner)view.findViewById(R.id.transport);
        Button btn_ok = (Button)view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button)view.findViewById(R.id.btn_cancel);

        builder.setView(view);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = productTextView.getText().toString();
                int weight = Integer.parseInt(weightTextView.getText().toString());
                String transport_type = transportSpinner.getSelectedItem().toString();
                String status = "Pending";

                dbHelper = new DBHelper(getActivity());
                sqLiteDatabase = dbHelper.getWritableDatabase();

                dbHelper.addRequest(sqLiteDatabase, product, weight, transport_type, status);

                Toast.makeText(getActivity(), "Saved!!!", Toast.LENGTH_LONG).show();

                dbHelper.close();

                dismiss();

                /*Intent intent = new Intent(getActivity(), RequestActivity.class);
                intent.putExtra("product", product);*/
            }
        });

        return builder.create();
    }
}
