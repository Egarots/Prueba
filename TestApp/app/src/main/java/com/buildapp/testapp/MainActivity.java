package com.buildapp.testapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.buildapp.testapp.Data.Song;
import com.buildapp.testapp.Helpers.Controller;
import com.buildapp.testapp.ui.Adapters.SongAdapter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    Controller controller= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            }

    public void onClick(View v)
    {
        boolean searching = true;
        EditText  searchText = (EditText) findViewById(R.id.editTextTextSearch);
        String text= searchText.getText().toString();
        String newText= text.replace(" ","+");
        controller= new Controller(this,this);
        controller.start(newText);

    }


}