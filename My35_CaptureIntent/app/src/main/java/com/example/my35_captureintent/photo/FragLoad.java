package com.example.my35_captureintent.photo;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my35_captureintent.MainActivity;
import com.example.my35_captureintent.R;

import java.util.ArrayList;

public class FragLoad extends Fragment {
    private static final String TAG = "main:MainActivity";
    private static final int PICTURE_REQUEST_CODE = 120;

    /* 1. DB에 있는 테이블을 기본으로 하여 DTO를 만든다
     *   가정 : 1. DB 에 singerDTO라는 테이블이 있다
     *         2. singerDTO라는 테이블에 name, mobile, age,
     *             resId(이미지경로)의 칼럼이 있다
     * 2. 1에서 만든 DTO에서 내가 보여주고 싶은 데이터를 골라 xml파일을 만든다
     * 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
     * 4. 선언한 클래스와 xml파일을 이용하여 화면을 adapter에서 생성한다
     * 5. 만든 adapter를 RecyclerView에 붙여준다
     * */

    MainActivity activity;
    RecyclerView recyclerView;
    PhotoAdapter adapter;
    ArrayList<PhotoDTO> dtos;
    Button btnAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.frag_load,
                container, false);


        // 반드시 생성해서 어댑터에 넘겨야 함
        dtos = new ArrayList<>();
       activity = (MainActivity)getActivity();
        btnAdd = view.findViewById(R.id.btnAdd);
        recyclerView = view.findViewById(R.id.recyclerView);
        // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(
                activity.getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 어댑터 객체 생성
        adapter = new
                PhotoAdapter(activity.getApplicationContext(), dtos,activity);

        // 어댑터에 있는 ArrayList<SingerDTO> dtos에 dto 추가
     //   adapter.addDto(new PhotoDTO( R.drawable.dog6));

        // 만든 어댑터를 리싸이클러뷰에 붙인다
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtos = null;

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();
                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri uri1 = clipData.getItemAt(i).getUri();
                        adapter.addDto(new PhotoDTO(uri1));

                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


}
