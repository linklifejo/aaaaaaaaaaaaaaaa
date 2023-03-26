package com.hanul.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {
Uri currImageURI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent intent = new Intent();
       intent.setType("image/*");
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(intent.createChooser(intent,"Select Picture"),1);


    }
    @Override
    public  void onActivityResult(int requestCode, int resultCode,Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                currImageURI = data.getData();
            }
        }
    }
public String getRealPathFromURI(Uri contentUri){
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,
                proj,
                null,
                null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return  cursor.getString(column_index);
}
}