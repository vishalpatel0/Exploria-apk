package com.kharigo.exploria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView sad;
    EditText ET_mobile,ET_pass;
    public DatabaseReference mDatabase;
    String json="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final user user=new user(getApplicationContext());
        new uses_permission().permition(this);
        if (user.getM() != "") { //login
            final String name = user.getName();
            Intent i = new Intent(this, home.class);
            startActivity(i);
            finish();
        }



        imageView = findViewById(R.id.imageView);
        ET_mobile = findViewById(R.id.editTextPhone);
        ET_pass = findViewById(R.id.TV_pass);
        Button BTN_login = findViewById(R.id.BTN_login);
        Glide.with(this)
                .load(R.drawable.a)
                .into(imageView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
     //   myRef.setValue(" Login exploria");
//        writeNewUser("8796965393","sada","31232");
//
//        Intent i = new
//        Intent(getApplicationContext(), home.class);
//        startActivity(i);
//        finish();





ET_mobile.setText("123");

ET_pass.setText("123");



        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m =  ET_mobile.getText().toString();
                String p =  ET_pass.getText().toString();
                    String userId = String.valueOf(ET_mobile);

                if (m.equals("123") || m.equals("111") ){
                    Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                    Log.i("tost","tost");
                    user user = new user(MainActivity.this);
                    user.set_log(m,p,"vishal","hello vishal");
                    Intent i = new Intent(getApplicationContext(), home.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }






//                mDatabase = FirebaseDatabase.getInstance("https://exploria-4d91f-default-rtdb.firebaseio.com/").getReference();
//                mDatabase.child("us").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                            if (!task.isSuccessful()) {
//                                Log.e("xx", "Error getting data", task.getException());
//                            }
//                            else {
//                                Log.d("_______ value ", ""+json);
//
//                                json = String.valueOf(task.getResult().getValue());
//                                Log.d("________ value ", ""+json);
//
//                                try {
//                                    JSONObject obj = new JSONObject(json);
//                                    Log.d("My App", obj.toString());
//                                    Log.d("name value ", obj.getString("name"));
//                                    Log.d("email value ", obj.getString("email"));
//
//
//
//
//                                } catch (Throwable t) {
//                                    Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
//                                }
//
//
//                            }
//                        }
//                    });




            }
        });




        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ca.class);
                startActivity(i);
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }

    public void writeNewUser(String userId, String name, String email) {
//        User user = new User(name, email);

        mDatabase = FirebaseDatabase.getInstance("https://exploria-4d91f-default-rtdb.firebaseio.com/").getReference();
//
//        mDatabase.child("us").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.e("xx", "Done getting data", task.getException());
//
//            }
//        });

        mDatabase.child("us").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("xxxx", "Error getting data", task.getException());
                }
                else {

                    Log.e("x____________x", "  "+ String.valueOf(task.getResult().getValue()));


                }}});
    }
}