package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Main2Activity extends AppCompatActivity {
//    @BindViews({R.id.recyc_btn,R.id.csv_btn})
//    Button recycbutton,csvbutton;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         ButterKnife.bind(this);

    }
   //@OnClick(R.id.recycview)
   @OnClick({R.id.recyc_btn,R.id.csv_btn,R.id.mian_btn,R.id.ttttt})
   public void Click(View view) {
      switch (view.getId()){
          case R.id.recyc_btn:
              startActivity(new Intent(Main2Activity.this, RecycleActivity.class));
              break;
          case R.id.csv_btn:
              startActivity(new Intent(Main2Activity.this, ExportCSVActivity.class));
              break;
          case R.id.mian_btn:
              startActivity(new Intent(Main2Activity.this, MainActivity.class));
              break;
          case R.id.ttttt:
              //startActivity(new Intent(Main2Activity.this, MainActivity.class));
              break;
      }
      // startActivity(new Intent(MainActivity.this, PermissionTestActivity.class));
   }
}
