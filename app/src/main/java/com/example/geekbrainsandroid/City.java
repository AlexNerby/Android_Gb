package com.example.geekbrainsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class City extends AppCompatActivity implements View.OnClickListener {

    private static final boolean LOG = true;
    private static final String TAG = "weatherCityPage";
    TextView text1, text3, text4, text5, text6, text2;
    EditText textField;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        init();
    }

    private void init() {
        if (LOG) {
            Log.d(TAG, "init in method init");
        }
        textField = findViewById(R.id.text_field);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text7);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);

        text1.setText(getResources().getString(R.string.city));
        text2.setText(getResources().getString(R.string.city2));
        text3.setText(getResources().getString(R.string.city3));
        text4.setText(getResources().getString(R.string.city4));
        text5.setText(getResources().getString(R.string.city5));
        text6.setText(getResources().getString(R.string.city6));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (LOG) {
                    Log.d(TAG, "intent.putExtra / startActivity");
                }
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("city", textField.getText().toString());
                startActivity(intent);
                break;
            case R.id.text1:
                textField.setText(R.string.city);
                break;
            case R.id.text7:
                textField.setText(R.string.city2);
                break;
            case R.id.text3:
                textField.setText(R.string.city3);
                break;
            case R.id.text4:
                textField.setText(R.string.city4);
                break;
            case R.id.text5:
                textField.setText(R.string.city5);
                break;
            case R.id.text6:
                textField.setText(R.string.city6);
                break;
            case R.id.buttonOther:
                startActivity(new Intent(this, Citys2.class));
        }
    }
}