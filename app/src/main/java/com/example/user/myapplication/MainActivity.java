package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eddd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
         eddd=findViewById(R.id.eddd);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String zz=  eddd.getText().toString();
            String cc=zz.substring(0);
                String cc1=zz.substring(1,zz.length());
                String cc2=zz.substring(0,1);
                Toast.makeText(MainActivity.this,cc,Toast.LENGTH_LONG).show();
                //Log.v("TAG",+"zzzz");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l=236%8;
                Log.v("TAG",l+"dddd");
            }
        });
    }
}
