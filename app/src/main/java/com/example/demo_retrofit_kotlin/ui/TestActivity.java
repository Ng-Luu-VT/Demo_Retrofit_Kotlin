package com.example.demo_retrofit_kotlin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.demo_retrofit_kotlin.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()
}