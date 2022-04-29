package com.google.finalstobaapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterEdit extends RecyclerView.Adapter<AdapterEdit.ViewHolder> {

    Context context;
    ArrayList<Item> list;

    public AdapterEdit(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup child, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recadd, child, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = list.get(position);
        holder.name.setText(item.getName());
        holder.cat.setText(item.getCat());
        holder.desc.setText(item.getDesc());
        holder.amount.setText(item.getAmount());
        holder.barcode.setText(item.getBarcode());

        holder. finaluser = holder.user.getEmail();
        holder. resultemail = holder.finaluser.replace(".", "");

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.barcode.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.cat_item))
                        .setExpanded(true,1500)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText name = v.findViewById(R.id.nameedit);
                EditText desc = v.findViewById(R.id.descedit);
                EditText amount = v.findViewById(R.id.amoedit);
                TextView barcode = v.findViewById(R.id.baredit);
                TextView cat = v.findViewById(R.id.cedit);
                Button update = v.findViewById(R.id.btnupdate);

                name.setText(item.getName());
                desc.setText(item.getDesc());
                amount.setText(item.getAmount());
                barcode.setText(item.getBarcode());
                cat.setText(item.getCat());
                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("desc",desc.getText().toString());
                        map.put("amount",amount.getText().toString());
                        map.put("barcode",barcode.getText().toString());
                        map.put("cat",cat.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child(holder.resultemail).child("Item").child(String.valueOf(holder.barcode.getText())).setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "data updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "data fail", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });

            }
        });

        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("are you sure?");
                builder.setMessage("deleted cant redo");

                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child(holder.resultemail).child("Item").child(String.valueOf(holder.barcode.getText())).removeValue();
                        Toast.makeText(holder.name.getContext(), "deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public String finaluser, resultemail;
        TextView name, cat, desc, amount, barcode;
        Button btndel, edit;
        FirebaseAuth mAuth;
        FirebaseUser user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nam);
            cat = itemView.findViewById(R.id.cate);
            amount = itemView.findViewById(R.id.amo);
            desc = itemView.findViewById(R.id.des);
            barcode = itemView.findViewById(R.id.code);

            edit = itemView.findViewById(R.id.btnedit);
            btndel = itemView.findViewById(R.id.btndelete);

            mAuth = FirebaseAuth.getInstance();
            user = mAuth.getCurrentUser();

        }
    }
}
