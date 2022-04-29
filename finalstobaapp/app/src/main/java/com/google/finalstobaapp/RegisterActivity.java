package com.google.finalstobaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText Email, password;
    private Button register, back;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btnregister);
        back = findViewById(R.id.btnback);
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailstring = Email.getText().toString();
                String passstring = password.getText().toString();
                String budget = "0";

                if(emailstring.isEmpty() || passstring.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "enter email or password", Toast.LENGTH_SHORT).show();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(emailstring,passstring).addOnCompleteListener((task) -> {

                        FirebaseDatabase.getInstance().getReference();
                        FirebaseUser userinfirebase = mAuth.getCurrentUser();
                        String UserID = userinfirebase.getEmail();
                        String resultemail = UserID.replace(".","");

                        FirebaseDatabase.getInstance().getReference("User").child(resultemail);

                            if (task.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("food").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("baverage").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("electronic").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("fashion").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("home supply").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("material").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("medicine").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("stationery").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("tools").setValue(budget);
                                FirebaseDatabase.getInstance().getReference().child(resultemail).child("Budget").child("automotive").setValue(budget);
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        }
    }
