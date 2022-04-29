package com.google.finalstobaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.SecureRandom;

public class AddActivity extends AppCompatActivity {

    public static TextView itemamou, coname;
    private Button inc, dec, res, save, bck, codeb, reco;
    private EditText itemdes,itemnam;
    private Spinner itemcat;
    int counter = 0;
    DatabaseReference reff;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        reco = findViewById(R.id.reco);
        dec = findViewById(R.id.btndecr);
        itemamou = findViewById(R.id.itemamount);
        inc = findViewById(R.id.btnincr);
        itemdes = findViewById(R.id.itemdesc);
        res = findViewById(R.id.btnreset);
        itemnam = findViewById(R.id.itemname);
        itemdes = findViewById(R.id.itemdesc);
        itemcat = findViewById(R.id.catspin);
        save = findViewById(R.id.datasave);
        bck = findViewById(R.id.addback);
        codeb = findViewById(R.id.code);
        coname = findViewById(R.id.codename);
        mAuth = FirebaseAuth.getInstance();

        reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, ItemRecog.class);
                startActivity(intent);
            }
        });

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        //nambahin amount
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                itemamou.setText(Integer.toString(counter));
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                itemamou.setText(Integer.toString(counter));
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                itemamou.setText(Integer.toString(counter));
            }
        });
        codeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarcodeActivity.class));
            }
        });




        //inputdata
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference();
                //get all values
                String name = itemnam.getText().toString();
                String cat = itemcat.getSelectedItem().toString();
                String amount = itemamou.getText().toString();
                String desc = itemdes.getText().toString();
                String barcode = coname.getText().toString();

                if(barcode  .isEmpty()) {
                    Toast.makeText(AddActivity.this, "please scan the barcode", Toast.LENGTH_SHORT).show();
                }
                else{
                    Item item = new Item(name, cat, desc, amount, barcode);

                    item.setName(itemnam.getText().toString());
                    item.setCat(itemcat.getSelectedItem().toString());
                    item.setAmount(itemamou.getText().toString());
                    item.setDesc(itemdes.getText().toString());
                    item.setBarcode(coname.getText().toString());

                    FirebaseUser user = mAuth.getCurrentUser();
                    String finaluser = user.getEmail();
                    String resultemail = finaluser.replace(".", "");

                    reff.child(resultemail).child("Item").child(barcode).setValue(item);
                    Toast.makeText(AddActivity.this,"data inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}