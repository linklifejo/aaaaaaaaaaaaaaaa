package com.hanul.myapplication8;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hanul.myapplication8.COMMON.CommonMethod;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploadUtils {
    public static void send2Server(File file){
        // 보낼 파일 MultipartBody.Part 생성
        String imgFilePath= "";
        CommonMethod commonMethod = new CommonMethod();
        //    commonMethod.setParams("param", dto);
        // 보낼 파일 MultipartBody.Part 생성
        // List<MultipartBody.Part> imgs = new ArrayList<>();
        RequestBody fileBody =null;
        MultipartBody.Part filePart =null;
        if(file != null) {

            fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath));
            filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
        }
        // 데이터를 파라메터로 보낸다
        commonMethod.setParams("param", "jobyx");
        // 서버로 보내는 부분
        commonMethod.sendFile("upload.f", filePart, new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
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