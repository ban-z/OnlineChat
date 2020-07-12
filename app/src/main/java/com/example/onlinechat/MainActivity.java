package com.example.onlinechat;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.onlinechat.Utils.PermissionCheck;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
/*    private AppBarConfiguration appBarConfiguration;*/
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private  Toolbar toolbar;

    private ActionBarDrawerToggle toggle;
    private ImageView img_usericon_mainact;

    public static final int CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        /*
        * 请求权限
        * */
        new PermissionCheck(MainActivity.this).checkPermission();
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavigationUI.navigateUp(navController, appBarConfiguration);

        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)){
            drawerLayout.closeDrawer(navigationView);
        }
        super.onBackPressed();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.NavHostDrawerLayout);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        /*appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(drawerLayout)
                .build();*/

        /*NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/

        navigationView = findViewById(R.id.navigation_view);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavViewListener();

        /*
        * 可更改用户头像
        * */
        img_usericon_mainact = findViewById(R.id.img_usericon_mainact);
        /*img_usericon_mainact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("imag/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("MainActivity", "onRequestPermissionsResult: :::::::::::::::::::::::::>>>" + grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this, "没有权限打开相册", Toast.LENGTH_SHORT).show();
                }
                break;
            case 999:
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "开启震动权限", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "未开启震动权限", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                if (requestCode == RESULT_OK){
                    //判断手机系统版本好
                    if (Build.VERSION.SDK_INT >= 19){
                        //4.4及以上使用此方法处理图片
                        hanleImageOnKitKat(data);
                    }else {
                        //4.4以下
                        handleImageBeforeKitKat(data);
                    }
                }
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void hanleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)){
            //如果是document类型的Uri，则通过docunment id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的ID
                String selecion = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selecion);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }else if ("content".equalsIgnoreCase(uri.getScheme())){
                imagePath = getImagePath(uri, null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())){
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            img_usericon_mainact.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this, "更换头像失败", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri externalContentUri, String selecion) {
        String path  =null;
        Cursor cursor = getContentResolver().query(externalContentUri, null, selecion, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void NavViewListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_item_1:
                        /*navController.navigate(R.id.action_homeFragment_to_mineFragment);*/
                        navController.navigate(R.id.mineFragment);
                        drawerLayout.closeDrawer(navigationView);
                        /*Toast.makeText(MainActivity.this, "nav_item_1", Toast.LENGTH_SHORT).show();*/
                        break;
                    case R.id.nav_item_2:
                        /*navController.navigate(R.id.action_homeFragment_to_IPandPortFragment);*/
                        navController.navigate(R.id.IPandPortFragment);
                        drawerLayout.closeDrawer(navigationView);
                        /*Toast.makeText(MainActivity.this, "nav_item_2", Toast.LENGTH_SHORT).show();*/
                        break;
                        /*
                        * 留待扩充
                        * */
                    case R.id.nav_item_3:
                        //运行时权限的申请
                        if (ContextCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, }, 1);
                        }else {
                            openAlbum();
                        }

                        drawerLayout.closeDrawer(navigationView);
                        break;
                    /*case R.id.nav_item_4:
                        Toast.makeText(MainActivity.this, "nav_item_4", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.nav_item_5:
                        Toast.makeText(MainActivity.this, "nav_item_5", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(navigationView);
                        break;*/
                }

                return true;
            }
        });
    }
}
