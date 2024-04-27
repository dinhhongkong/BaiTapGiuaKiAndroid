package com.example.bai8;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    EditText textView1;
    EditText textView2;
    EditText textView3;
    List<Phone> phones = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.thong_bao);
        ListViewAdpater adapter = new ListViewAdpater(this, R.layout.item_san_pham, phones);

        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);
// Set the adapter for the ListView


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        button1.setOnClickListener(v -> {
            myRef.setValue(textView1.getText().toString()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        });
        textView2 = findViewById(R.id.product_name);
        textView3 = findViewById(R.id.product_price);

        button2.setOnClickListener(v -> {
            Map<String, Object> contentValues = new HashMap<>();
            contentValues.put("name", textView2.getText().toString());
            contentValues.put("price", textView3.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("products")
                    .push().setValue(contentValues).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference().child("products").get().addOnSuccessListener(dataSnapshot -> {
                                phones.clear();
                                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                                    Phone phone = productSnapshot.getValue(Phone.class);
                                    phones.add(phone);
                                }
                                adapter.notifyDataSetChanged();
                            }).addOnFailureListener(e -> {
                                Log.w(TAG, "Failed to read value.", e);
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            // Read from the database
        });
        // Get a reference to the products in the database
        FirebaseDatabase.getInstance().getReference().child("products").get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                Phone phone = productSnapshot.getValue(Phone.class);
                phones.add(phone);
            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> {
            Log.w(TAG, "Failed to read value.", e);
        });
    }
}