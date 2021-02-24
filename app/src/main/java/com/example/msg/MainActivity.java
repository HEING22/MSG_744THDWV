package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView ListViewOfBrands;
    private DatabaseReference reference;
    List<String> brandNameList = new ArrayList<>();
    List<Brand> brandList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewOfBrands = findViewById(R.id.ListViewOfBrands);
        reference = FirebaseDatabase.getInstance().getReference().child("brands");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot shot : snapshot.getChildren()){
                    Brand brand = shot.getValue(Brand.class);
                    brandNameList.add(brand.name);
                    brandList.add(brand);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, brandNameList);

                ListViewOfBrands.setAdapter(arrayAdapter);

                ListViewOfBrands.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), SneakerList.class);
                                intent.putExtra("ID", brandList.get(position).id);
                                startActivity(intent);
                            }
                        }
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}