package com.kharigo.exploria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kharigo.exploria.home.User;

public class ca extends AppCompatActivity {
    EditText editTextTextPersonName3,editTextTextPersonName4,editTextTextPersonName5;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);
        Button button3 = findViewById(R.id.button3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
         editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);


        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().writeNewUser(editTextTextPersonName4.getText().toString(),editTextTextPersonName3.getText().toString(),editTextTextPersonName5.getText().toString()); finish();

            }
        });
    }
}