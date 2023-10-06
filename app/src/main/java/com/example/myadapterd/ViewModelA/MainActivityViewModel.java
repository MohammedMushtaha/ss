package com.example.myadapterd.ViewModelA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myadapterd.R;

public class MainActivityViewModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_model);

        ////Object View Model
        ViewModelMain homeViewModel =
                new ViewModelProvider(this).get(ViewModelMain.class);
        ////

        final TextView textView =findViewById(R.id.textV);
//      homeViewModel.getText().observe(this, textView::setText);

        homeViewModel.getText().observe(this, nested -> {
             textView.setText(nested);
        });

    }
}