package com.example.geekbrainsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class CitysSpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final boolean LOG = true;
    private static final String TAG = "weatherOtherCityPage";

    private Spinner spinner;
    private EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        if (LOG) {
            Log.d(TAG, "Init in onCreate");
        }
        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.other_edit_text);
        spinner.setOnItemSelectedListener(this);
    }

    public void onClick(View view) {
        if (LOG) {
            Log.d(TAG, "Click ok");
        }
        intent = new Intent(CitysSpinnerActivity.this, SecondActivity.class);
        intent.putExtra("city", editText.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (LOG) {
            Log.v(TAG, "init. or click spinner item");
        }
        editText.setText(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}