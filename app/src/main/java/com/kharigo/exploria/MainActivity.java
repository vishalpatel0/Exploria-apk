package com.kharigo.exploria;

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
import com.kharigo.exploria.home.home;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView sad;
    EditText ET_mobile,ET_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   new uses_permission().permition(getApplicationContext());

        final user user=new user(getApplicationContext());
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


// test use
//        Intent i = new Intent(getApplicationContext(), home.class);
//        startActivity(i);
//        finish();





        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m =  ET_mobile.getText().toString();
                String p =  ET_pass.getText().toString();

                if (m.equals("123") || m.equals("111") ){
                    Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                    Log.i("tost","tost");

                    user user = new user(MainActivity.this);
                    user.set_log(m,p,"vishal","hello vishal");

               Intent i = new Intent(getApplicationContext(), home.class);
               startActivity(i);
               finish();

                }


            }
        });



    }
}