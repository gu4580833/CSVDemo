package com.example.user.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.dao.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PreViewActivity extends Activity {
    private PreViewActivity mActivity;
    private ListViewForScrollView listViewForScrollView;

     private String filepath="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        mActivity=this;
        filepath=getIntent().getStringExtra("path");
        listViewForScrollView=findViewById(R.id.listcsv);
        TextView btn=findViewById(R.id.bb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(0,new User("姓名","年龄","性别"));
                csvAdapter.notifyDataSetChanged();
            }
        });

    }
CSVAdapter csvAdapter;
    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog("","Loading... please wait.....");
        qure();
    }
private void setAdapter(){
    if(csvAdapter==null){

        csvAdapter=new CSVAdapter(mActivity,list);
        listViewForScrollView.setAdapter(csvAdapter);
    }else{
        csvAdapter.notifyDataSetChanged();
    }
}
    ProgressDialog progressDialog;

    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(mActivity, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    /*
     * 隐藏提示加载
     */
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    Handler mm=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // super.handleMessage(msg);
            switch (msg.what){
                case 0:

                    setAdapter();
                    hideProgressDialog();
                    //Toast.makeText(mActivity, "文件导出成功！请到SD卡中查看", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    hideProgressDialog();
                    Toast.makeText(mActivity, "文件导出成功！请到SD卡中查看", Toast.LENGTH_LONG).show();

                    break;
            }
        }
    };
    private void qure(){
        ReadCSVThread readCSVThread=new ReadCSVThread();
        readCSVThread.start();

    }
    private ArrayList<User> list=new ArrayList<>();
    class ReadCSVThread extends Thread {
        @Override
        public void run() {
            super.run();
            //File pathfile= new File();



                File inFile = new File(filepath);
                if(inFile.exists()){
                final StringBuilder cSb = new StringBuilder();
                String inString;
                try {

                    list.clear();
                    BufferedReader reader =
                            new BufferedReader(new FileReader(inFile));
                    //reader.readLine();
                    //inString = reader.readLine();
//                String[] lines = inString.split(",");
//
//                    User user=new User(lines[0],lines[1],lines[2]);
//
//                list.add(user);
                    while ((inString = reader.readLine()) != null) {
                        String[]   lines = inString.split(",");
                        User user1=new User(lines[0],lines[1],lines[2]);
                        list.add(user1);
                        //cSb.append(inString).append("\n");

                    }
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mm.sendEmptyMessage(0);
            }else{
                mm.sendEmptyMessage(1);
            }
        }
    }
}
