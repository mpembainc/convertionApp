package com.example.conversionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String conversions[] = {"KM to Miles", "KM to CM", "KM to M"};
    String option;
    Button button;
    EditText km, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner1);
        button = (Button) findViewById(R.id.button1);
        km = (EditText) findViewById(R.id.kmText);
        other = (EditText) findViewById(R.id.otherText);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conversions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                option = item.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kmStringValue = km.getText().toString();
                String otherStringValue = other.getText().toString();

                if (kmStringValue.matches("") && otherStringValue.matches("")) {
                    Toast.makeText(MainActivity.this, "All values are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (otherStringValue.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter Other Value", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (kmStringValue.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter KM Value", Toast.LENGTH_SHORT).show();
                    return;
                }

                int kmValue = Integer.parseInt(kmStringValue);
                int otherValue = Integer.parseInt(otherStringValue);

                int result = kmValue * otherValue;
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("convertType", option);
                editor.putString("kmValue", kmStringValue);
                editor.putString("otherValue", otherStringValue);
                editor.putString("results", result+"");
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Result.class);
                startActivity(intent);
            }
        });
    }
}