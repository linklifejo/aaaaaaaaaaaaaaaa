package com.hanul.myapplexam;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanul.myapplexam.COMMON.CommonMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
Uri uri;
ImageView imageView;

    File imgFile = null;
    String imgFilePath = null;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imgView);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");

                startActivityResult.launch(intent);
            }
        });

    }
ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    uri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        imageView.setImageBitmap(bitmap);
                        File path = new File(".");

                        Toast.makeText(MainActivity.this," " + path.getAbsolutePath(), Toast.LENGTH_LONG).show();
                      //  imageView.getResources().getAssets().
                        //imgFilePath=  uri.getPath();
                      //  BitmapConvertFile(bitmap,uri.getPath().toString());
                       // aa();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    private void aa(){
        // 보낼 파일 MultipartBody.Part 생성

        CommonMethod commonMethod = new CommonMethod();
        //    commonMethod.setParams("param", dto);
        // 보낼 파일 MultipartBody.Part 생성
        // List<MultipartBody.Part> imgs = new ArrayList<>();
        RequestBody fileBody =null;
        MultipartBody.Part filePart =null;
        if(imgFile != null) {

            fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath));
            filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
        }
        // 데이터를 파라메터로 보낸다
        commonMethod.setParams("param", "jobyx");
        // 서버로 보내는 부분
        commonMethod.sendFile("upload.f", filePart, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this,"onResponse: 보내기 성공", Toast.LENGTH_SHORT).show();


                //   finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onResponse: 보내기 실패", Toast.LENGTH_SHORT).show();

            }
        });
    }
   }
