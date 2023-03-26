package com.hanul.myapplication111;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    //UI
    ImageView image1, image2, image3;

    //constant
    final int PICTURE_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI
        image1 = (ImageView)findViewById(R.id.img1);
        image2 = (ImageView)findViewById(R.id.img2);
        image3 = (ImageView)findViewById(R.id.img3);
         Button btnImage = (Button)findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                //기존 이미지 지우기
                image1.setImageResource(0);
                image2.setImageResource(0);
                image3.setImageResource(0);


                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();


                // 이미지 전송

                ClipData clipData = data.getClipData();


                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {

                    for (int i = 0; i < 3; i++) {
                        if (i < clipData.getItemCount()) {
                            Uri urione = clipData.getItemAt(i).getUri();
                            switch (i) {
                                case 0:
                                    image1.setImageURI(urione);
                                    BitmapDrawable drawable = (BitmapDrawable) image1.getDrawable();
                                    Bitmap bitmap = drawable.getBitmap();
                                    BitmapConvertFile(bitmap,  "c:\\app");
                                    break;
                                case 1:
                                    image2.setImageURI(urione);
                                    break;
                                case 2:
                                    image3.setImageURI(urione);
                                    break;
                            }
                        }
                    }
                } else if (uri != null) {
                    image1.setImageURI(uri);
                }
            }
        }
    }
    private void BitmapConvertFile(Bitmap bitmap, String strFilePath)
    {
        // 파일 선언 -> 경로는 파라미터에서 받는다
        File file = new File(strFilePath);

        // OutputStream 선언 -> bitmap데이터를 OutputStream에 받아 File에 넣어주는 용도
        OutputStream out = null;
        try {
            // 파일 초기화
            file.createNewFile();

            // OutputStream에 출력될 Stream에 파일을 넣어준다
            out = new FileOutputStream(file);

            // bitmap 압축
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}