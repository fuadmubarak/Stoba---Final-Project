package com.google.finalstobaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {

    private TextView accinf;
    private Button btnlogout, bck;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accinf = findViewById(R.id.accinfo);
        btnlogout = findViewById(R.id.logout);
        bck = findViewById(R.id.accback);
        firebaseAuth = FirebaseAuth.getInstance();

        accinf.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(AccountActivity.this)
                    .setTitle("STOBA FX")
                    .setMessage("you want to quit?")
                    .setCancelable(false)
                    .setPositiveButton("yes",(dialog, id) -> {
                        firebaseAuth.signOut();
                        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("no", null)
                    .show();
            }
        });
    }
}