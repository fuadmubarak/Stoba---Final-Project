package com.google.finalstobaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    private Button mat, aut, too, med, sta, bev, foo, ele, fas, hom, bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        bck = findViewById(R.id.caback);
        mat = findViewById(R.id.material);
        aut = findViewById(R.id.automotive);
        too = findViewById(R.id.tools);
        med = findViewById(R.id.medicine);
        sta = findViewById(R.id.stationary);
        bev = findViewById(R.id.beverage);
        foo = findViewById(R.id.food);
        ele = findViewById(R.id.electronic);
        fas = findViewById(R.id.fashion);
        hom = findViewById(R.id.home);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        bev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, BeverageActivity.class);
                startActivity(intent);
            }
        });

        foo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, ElectActivity.class);
                startActivity(intent);
            }
        });

        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, HomActivity.class);
                startActivity(intent);
            }
        });

        fas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, FashActivity.class);
                startActivity(intent);
            }
        });

        //

        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, MatActivity.class);
                startActivity(intent);
            }
        });

        aut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });

        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, StatActivity.class);
                startActivity(intent);
            }
        });

        too.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, ToolsActivity.class);
                startActivity(intent);
            }
        });

        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, MedActivity.class);
                startActivity(intent);
            }
        });
    }
}