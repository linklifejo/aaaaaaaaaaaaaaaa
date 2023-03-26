package com.example.my35_captureintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.example.my35_captureintent.photo.FragLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDangerousPermissions();
        //fragmentControl(new FragTake());
        fragmentControl(new FragLoad());


    }


    public void fragmentControl(Fragment f) {

        getSupportFragmentManager().beginTransaction().replace(R.id.contain,f).commit();
    }
    public void fragmentControl(Fragment f, Bundle b) {
        bundle = b;
        getSupportFragmentManager().beginTransaction().replace(R.id.contain,f).commit();
    }

    // 위험권한
    private void checkDangerousPermissions() {
        String[] permissions = {
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public  void BitmapConvertFile(Bitmap bitmap, String strFilePath)
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