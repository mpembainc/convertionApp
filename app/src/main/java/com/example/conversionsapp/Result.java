package com.example.conversionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView kmTextView, otherTextView, typeTextView, resultTextView;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        kmTextView = (TextView) findViewById(R.id.km);
        otherTextView = (TextView) findViewById(R.id.other);
        typeTextView = (TextView) findViewById(R.id.type);
        resultTextView = (TextView) findViewById(R.id.result);
        logoutButton = (Button) findViewById(R.id.logout);

        final SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String type = preferences.getString("convertType", "");
        String km = preferences.getString("kmValue", "");
        String other = preferences.getString("otherValue", "");
        String result = preferences.getString("results", "");

        kmTextView.setText("KM value is: " + km);
        otherTextView.setText("Other Value is: " + other);
        typeTextView.setText("Converted from " + type);
        resultTextView.setText("The result is: " + result);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences1 = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}