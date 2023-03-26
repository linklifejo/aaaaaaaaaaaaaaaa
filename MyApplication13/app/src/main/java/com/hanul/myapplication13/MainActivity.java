package com.hanul.myapplication13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanul.myapplication13.COMMON.CommonMethod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwSelected;
    Button btnImageSend, btnImageSelection;
    File imgFile = null;
    String imgFilePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImageSend = findViewById(R.id.btnImageSend);
        btnImageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonMethod commonMethod = new CommonMethod();
                RequestBody fileBody = null;
                MultipartBody.Part filePart = null;
                if (imgFile != null) {
                    imgFilePath = "xx";
                    fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath));
                    filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                }
                // 데이터를 파라메터로 보낸다

                // commonMethod.setParams("param", "jobyx");
                // 서버로 보내는 부분
                commonMethod.sendFile("upload.f", filePart, new Callback<String>() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Toast.makeText(MainActivity.this, "onResponse: 보내기 실패", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        Toast.makeText(MainActivity.this, "onResponse: 보내기 성공", Toast.LENGTH_SHORT).show();
                    }


                });
            }
        });

        btnImageSelection = findViewById(R.id.btnImageSelection);
        btnImageSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Intent를 통해 이미지를 선택
                Intent intent = new Intent();
                // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        imgVwSelected = findViewById(R.id.imgVwSelected);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode != 1 || resultCode != RESULT_OK) {
            return;
        }
        // 비트맵으로 받는 방법
        try {
            InputStream in = getContentResolver().openInputStream(data.getData());
            Bitmap image = BitmapFactory.decodeStream(in);

            imgVwSelected.setImageBitmap(image);
         //
            in.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        // URI로 받는 방법
      //  imgVwSelected.setImageURI(data.getData());
    }
}