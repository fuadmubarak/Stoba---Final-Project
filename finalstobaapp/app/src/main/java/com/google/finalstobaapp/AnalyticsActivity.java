package com.google.finalstobaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalyticsActivity extends AppCompatActivity {

    TextView foodamount, bevamount, fasamount, homeamount, autoamount, medamount, staamount, eleamount, mateamount, tooamount;
    AnyChartView cv;
    private FirebaseAuth mAuth;
    private DatabaseReference reff, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        foodamount = findViewById(R.id.foodamount);
        bevamount = findViewById(R.id.bevamount);
        fasamount = findViewById(R.id.fashamount);
        homeamount = findViewById(R.id.homeamount);
        autoamount = findViewById(R.id.autoamount);
        medamount = findViewById(R.id.medamount);
        staamount = findViewById(R.id.staamount);
        eleamount = findViewById(R.id.elecamount);
        mateamount = findViewById(R.id.matamount);
        tooamount = findViewById(R.id.toolamount);

        cv = findViewById(R.id.anychart);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String finaluser = user.getEmail();
        String resultemail = finaluser.replace(".", "");
        reff = FirebaseDatabase.getInstance().getReference(resultemail).child("Budget");
        ref = FirebaseDatabase.getInstance().getReference(resultemail).child("Item");

        //item amount
        Query food = ref.orderByChild("cat").equalTo("food");
        Query bev = ref.orderByChild("cat").equalTo("baverage");
        Query fas = ref.orderByChild("cat").equalTo("fashion");
        Query home = ref.orderByChild("cat").equalTo("home supply");
        Query auto = ref.orderByChild("cat").equalTo("automotive");
        Query med = ref.orderByChild("cat").equalTo("medicine");
        Query stat = ref.orderByChild("cat").equalTo("stationery");
        Query elec = ref.orderByChild("cat").equalTo("electronic");
        Query mate = ref.orderByChild("cat").equalTo("material");
        Query tools = ref.orderByChild("cat").equalTo("tools");


        food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    foodamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        bev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    bevamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        fas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    fasamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        home.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    homeamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        auto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    autoamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        med.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    medamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        stat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    staamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        elec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    eleamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    mateamount.setText(String.valueOf(sum));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        tools.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum=0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("amount");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    tooamount.setText(String.valueOf(sum));


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        Grapgh();

        }

    private void Grapgh() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){


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

                    Pie pie = AnyChart.pie();
                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("Fashion", FashionTotal));
                    data.add(new ValueDataEntry("House Supply", HouseTotal));
                    data.add(new ValueDataEntry("Food", FooTotal));
                    data.add(new ValueDataEntry("beverage", BevTotal));
                    data.add(new ValueDataEntry("electronic", EleTotal));
                    data.add(new ValueDataEntry("medicine", MedTotal));
                    data.add(new ValueDataEntry("automotive", AutoTotal));
                    data.add(new ValueDataEntry("stationary", StaTotal));
                    data.add(new ValueDataEntry("tools", TooTotal));
                    data.add(new ValueDataEntry("material", MatTotal));

                    pie.data(data);

                    pie.title("Budget Analytics");

                    pie.labels().position("outside");

                    pie.legend().title().enabled(true);
                    pie.legend().title()
                            .text("Budget On")
                            .padding(0d, 0d, 10d, 0d);

                    pie.legend()
                            .position("center-bottom")
                            .itemsLayout(LegendLayout.HORIZONTAL)
                            .align(Align.CENTER);

                    cv.setChart(pie);
                }
                else {
                    Toast.makeText(AnalyticsActivity.this,"Child does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AnalyticsActivity.this,"Child does not exist", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
