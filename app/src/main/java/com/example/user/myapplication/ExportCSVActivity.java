package com.example.user.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.myapplication.dao.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExportCSVActivity  extends Activity{

    private ExportCSVActivity mActivity;
    private static final String FILE_FOLDER =
            Environment.getExternalStorageDirectory() +
                    "/CeShi/";
    private    String filename = "ceshi"+".csv";
    private static final String FILE_CSV = "about_data.csv";
    private String[] strings={"zzzz","ads","dddd","ssdad","zer","loij",";kjh","dfgfd","aqwrt"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_exportcsv);
        ButterKnife.bind(this);
        mActivity=this;

        initialPermission();
        setlist();

    }
private ArrayList<User> list=new ArrayList<>();
private void setlist(){
    list.clear();
    String sex="";
    int age=0;
//    User user=new User("姓名","年龄","性别");
//    list.add(user);
    for(int i=0;100>i;i++){
        age= (int) (20+Math.random()*(35-20));
        if(i%2==0){
            sex="女";
        }else{
            sex="男";
        }
        User user=new User(i+"",age+"",sex);
        list.add(user);
    }
    pathfile=new File(FILE_FOLDER);


    if (!pathfile.exists()) {
        pathfile.mkdirs();
    }
//  String  root_Path = Environment.getExternalStorageDirectory() + "/TinyInvoice/";
//    boolean flag=new File(root_Path).exists();
//    pathfile=new File(FILE_FOLDER);
//
//    try {
//        if (!pathfile.exists()) {
//            pathfile.mkdirs();
//        }
//    }catch (Exception e){
//        Log.e("GU","Exception11111:"+e.toString());
//        e.printStackTrace();
//    }
}
   Handler mm=new Handler(){
       @Override
       public void handleMessage(Message msg) {
          // super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   hideProgressDialog();
                   Toast.makeText(mActivity, "文件导出成功！请到SD卡中查看", Toast.LENGTH_LONG).show();
                   break;
               case 1:
                   hideProgressDialog();
                  startActivity(new Intent(mActivity,PreViewActivity.class));
                   break;
           }
       }
   };

    @OnClick({R.id.btn_list2,R.id.btn_c})
    public void Click(View view) {
        switch (view.getId()){
            case R.id.btn_list2:
                Export();
                break;
            case R.id.btn_c:
                Log.e("GU","kdkfjfjgjg11111");
                CSV();
//                startActivity(new Intent(Main2Activity.this, ExportCSVActivity.class));
                break;
        }
        // startActivity(new Intent(MainActivity.this, PermissionTestActivity.class));
    }
    public void Export(){
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent= Utils.setIntentDataAndType(mActivity,intent,"application/vnd.ms-excel",file,false);
//        //intent.setDataAndType(fileUris.get(0), "application/pdf");
//
//        startActivityForResult(Intent.createChooser(intent, "Export"), 11);
        Intent intent=new Intent(mActivity,PreViewActivity.class);
        intent.putExtra("path",FILE_FOLDER+filename);
        startActivity(intent);

    }
    public void CSV(){
        Log.e("GU","kdkfjfjgjg11111");
        showProgressDialog("","Loading... please wait.....");
        Log.e("GU","kdkfjfjgjg");
        WriteData2CSVThread writeData2CSVThread=new WriteData2CSVThread();
        writeData2CSVThread.start();
        Log.e("GU","kdkfjfjgj222222");
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
    private void initialPermission() {
        if (!checkPermissions()) {
            requestPermissions();
        }
    }
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    public boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    public void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);


        if (shouldProvideRationale) {
            showSnackbar(this, R.string.allow_location_permission, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(mActivity,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                Toast.makeText(this, "Failed interaction was cancelled", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Success granted permission", Toast.LENGTH_SHORT).show();
            } else {
                showSnackbar(this, R.string.setting_permission, R.string.label_setting, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",
                                BuildConfig.APPLICATION_ID, null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public static void showSnackbar(Activity activity, final int mainTextStringId, final int actionStringId,
                                    View.OnClickListener listener) {
        Snackbar.make(activity.findViewById(android.R.id.content),
                activity.getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(activity.getString(actionStringId), listener).show();
    }
    File file;
    File pathfile;
    class WriteData2CSVThread extends Thread {


        @Override
        public void run() {
            super.run();

            StringBuffer buffer = new StringBuffer();
            buffer.append("姓名,年龄,性别\r\n");
            for(User u:list){
                buffer.append(u.getName()+","+u.getAge()+","+u.getSex()+"\r\n");
            }
            try {
//				String data =new String(buffer.toString().getBytes("utf-8"), "ansi") ;
                String data = buffer.toString();




                 file = new File(pathfile, filename);
                OutputStream out=new FileOutputStream(file);
                byte b[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
                out.write(b);

                out.write(data.getBytes());
                out.close();
               mm.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("GU","Exception:"+e.toString());
            }

        }
    }

}
