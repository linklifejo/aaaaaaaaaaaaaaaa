package com.example.my28_recyclerview3;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private static final String TAG = "main:MainActivity";

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
    DogAdapter adapter;
    ArrayList<DogDTO> dtos;
    Button btnAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.fragment2,
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
                DogAdapter(activity.getApplicationContext(), dtos,activity);

        // 어댑터에 있는 ArrayList<SingerDTO> dtos에 dto 추가
        adapter.addDto(new DogDTO("도사견",
                "암", "서울",1, R.drawable.dog6));
        adapter.addDto(new DogDTO("도사견",
                "수", "광주",2, R.drawable.dog2));
        adapter.addDto(new DogDTO("도사견",
                "암", "전남",1, R.drawable.dog3));
        adapter.addDto(new DogDTO("도사견",
                "수", "서울",1, R.drawable.dog4));
        adapter.addDto(new DogDTO("도사견",
                "암", "서울",2, R.drawable.dog5));

        // 만든 어댑터를 리싸이클러뷰에 붙인다
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addDto(new DogDTO("도사견",
                        "암", "서울",1, R.drawable.dog2));
                // 추가후 리스트 갱신
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }


}
