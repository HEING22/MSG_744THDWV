package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SneakerList extends AppCompatActivity {

    DatabaseReference reference;
    List<Sneaker> sneakers = new ArrayList<>();
    String brandID;
    ListView sneakerListView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_list);
        brandID = getIntent().getStringExtra("ID");
        sneakerListView = findViewById(R.id.sneakerListView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        reference = FirebaseDatabase.getInstance().getReference().child("sneakers");
        reference.child(brandID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot shot : snapshot.getChildren()){
                    Sneaker sneaker = shot.getValue(Sneaker.class);
                    sneakers.add(sneaker);
                }
                SneakerListAdapter adapter = new SneakerListAdapter(getApplicationContext(), R.layout.sneaker_list_item, sneakers);
                sneakerListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sneakerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SneakerDetail.class);
                intent.putExtra("Sneaker", sneakers.get(position));
                startActivity(intent);
            }
        });
    }
}