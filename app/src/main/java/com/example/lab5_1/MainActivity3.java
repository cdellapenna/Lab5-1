package com.example.lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    public void clickSave(View view) {

        EditText editText = findViewById(R.id.edtInput);
        String userEntr = editText.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper db = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            db.saveNotes(username, title, userEntr, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            db.updateNote(title, date, userEntr, username);
        }

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", sharedPreferences.getString("username", ""));
        startActivity(intent);




    }

    int noteid = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText editText = findViewById(R.id.edtInput);
        Intent intent = getIntent();
        int idNote = intent.getIntExtra("noteid",-1);
        noteid = idNote;

        if (noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }


    }
}