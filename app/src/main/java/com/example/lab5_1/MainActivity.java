package com.example.lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView2;
    static String usernameKey2;

    public void clickFunction(View view) {
        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName);
        String str = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("username", str).apply();

        goToActivity2(str);
    }

    public void goToActivity2(String s) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        usernameKey2 = usernameKey;

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("message", sharedPreferences.getString("username", ""));
            startActivity(intent);

        } else {
            setContentView(R.layout.activity_main);
        }
    }
}