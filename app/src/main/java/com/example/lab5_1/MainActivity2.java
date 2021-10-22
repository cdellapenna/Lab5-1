package com.example.lab5_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {

    TextView textView2;
    Menu optionsMenu;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString("username", "");
        textView2.setText("Welcome " + str + "!");


        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper db = new DBHelper(sqLiteDatabase);
        notes = db.readNotes(str);

        ArrayList<String> displayNotes = new ArrayList<>();
        for(Note note : notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,displayNotes);
        ListView listView = (ListView)  findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.logout) {
            Intent intent = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(MainActivity.usernameKey2).apply();
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(this, MainActivity3.class);
            //SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
            //sharedPreferences.edit().remove(MainActivity.usernameKey2).apply();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}