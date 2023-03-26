package com.hanul.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
int REQUEST_CODE =10;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.profile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        button = (Button) findViewById(R.id.finish_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update Photo
                UpdatePhoto();
                infoInputCheck(new RegisterData());
            }
        });
    }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null) {
            selectedImage = data.getData();

            //여기서부터
            Uri photoUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photoUri);
                bitmap = rotateImage(bitmap, 90);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
            //여기까지는 이미지를 갤러리에서 클릭해서 그 사진을 이미지뷰에 보여주는 코드

            Cursor cursor = getContentResolver().query(Uri.parse(selectedImage.toString()), null, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            //커서 사용해서 경로 확인

        }else{
            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_LONG).show();
        }
    }
    private void UpdatePhoto() {
        if(mediaPath != null){
            File file = new File(mediaPath);

            //아래 두줄이 중요하다!!!
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("profile", file.getName(), requestBody);

            //token을 header에 넣는 두 줄의 코드
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String token = sp.getString("TOKEN", "");

            service.profile(fileToUpload, token).enqueue(new Callback<ProfileResponse>() {

                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    ProfileResponse result = response.body();
                }

                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    Toast.makeText(SetPuppy.this, "통신 실패", Toast.LENGTH_SHORT).show();
                    Log.d("에러",  t.getMessage());
                }
            });
        }}
}