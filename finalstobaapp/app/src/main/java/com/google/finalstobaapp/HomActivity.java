package com.google.finalstobaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomActivity extends AppCompatActivity {

    RecyclerView rech;
    DatabaseReference reff;
    Adapter adapter;
    ArrayList<Item> list;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom);
        rech = findViewById(R.id.recth);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String finaluser = user.getEmail();
        String resultemail = finaluser.replace(".", "");

        reff = FirebaseDatabase.getInstance().getReference(resultemail).child("Item");
        Query query = reff.orderByChild("cat").equalTo("home supply");
        rech.setHasFixedSize(true);
        rech.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Adapter(this,list);
        rech.setAdapter(adapter);

        query.addValueEventListener(new ValueEventListener() {
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
}