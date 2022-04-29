package com.google.finalstobaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private Button add, delete, edit, barcode, analytic, acc, bev, foo, ele, fas, hom, update;
    ImageView bdgt;
    TextView allcat,wac,rup;
    RecyclerView recyclerView;
    DatabaseReference reff, ref;
    Adapter adapter;
    ArrayList<Item> list;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bdgt = findViewById(R.id.badgt);
        rup = findViewById(R.id.rp);
        wac = findViewById(R.id.wacc);
        add = findViewById(R.id.btnadd);
        delete = findViewById(R.id.btndel);
        edit = findViewById(R.id.btnedit);
        barcode = findViewById(R.id.btnbarcode);
        analytic = findViewById(R.id.btnanalytic);
        acc = findViewById(R.id.account);
        bev = findViewById(R.id.beverage);
        foo = findViewById(R.id.food);
        ele = findViewById(R.id.electronic);
        fas = findViewById(R.id.fashion);
        hom = findViewById(R.id.home);
        allcat = findViewById(R.id.allcat);
        update = findViewById(R.id.btnupdate);
        mAuth = FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        analytic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AnalyticsActivity.class);
                startActivity(intent);
            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        bev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BeverageActivity.class);
                startActivity(intent);
            }
        });

        foo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ElectActivity.class);
                startActivity(intent);
            }
        });

        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HomActivity.class);
                startActivity(intent);
            }
        });

        fas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FashActivity.class);
                startActivity(intent);
            }
        });

        allcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });


        FirebaseUser user = mAuth.getCurrentUser();
        String finaluser = user.getEmail();
        String resultemail = finaluser.replace(".", "");

        wac.setText("Welcome   " + mAuth.getCurrentUser().getEmail());

        ref = FirebaseDatabase.getInstance().getReference(resultemail).child("Budget");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                int FashionTotal = Integer.parseInt(snapshot.child("fashion").getValue().toString());
                int FooTotal = Integer.parseInt(snapshot.child("food").getValue().toString());
                int HouseTotal = Integer.parseInt(snapshot.child("home supply").getValue().toString());
                int BevTotal = Integer.parseInt(snapshot.child("baverage").getValue().toString());
                int EleTotal = Integer.parseInt(snapshot.child("electronic").getValue().toString());
                int MedTotal = Integer.parseInt(snapshot.child("medicine").getValue().toString());
                int AutoTotal = Integer.parseInt(snapshot.child("automotive").getValue().toString());
                int StaTotal = Integer.parseInt(snapshot.child("stationery").getValue().toString());
                int TooTotal = Integer.parseInt(snapshot.child("tools").getValue().toString());
                int MatTotal = Integer.parseInt(snapshot.child("material").getValue().toString());

                    sum += FashionTotal+FooTotal+HouseTotal+BevTotal+EleTotal+MedTotal+AutoTotal+StaTotal+TooTotal+MatTotal;
                    rup.setText(String.valueOf(sum));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "no budget found", Toast.LENGTH_SHORT).show();
            }
        });

        //recycleview
        recyclerView = findViewById(R.id.rect);

        reff = FirebaseDatabase.getInstance().getReference(resultemail).child("Item");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Item item = dataSnapshot.getValue(Item.class);
                    list.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (reff != null);{
//            reff.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()){
//                        for (DataSnapshot ds : snapshot.getChildren()) {
//
//                            Item item = ds.getValue(Item.class);
//                            list.add(item);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                }

//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
}