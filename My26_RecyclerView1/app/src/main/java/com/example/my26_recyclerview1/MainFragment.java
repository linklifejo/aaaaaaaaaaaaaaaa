package com.example.my26_recyclerview1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {

    // 내가 속한 액티비티를 알게 해준다 : 개발자가
    MainActivity activity;

    // 프래그먼트 화면 붙이기 : 반드시 onCreateView를 오버라이드 시켜야 한다
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.mainfragment,
                                container, false);

        activity = (MainActivity) getActivity();

        view.findViewById(R.id.btnMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  activity.onChangeFrag(new SubFragment());
            }
        });
        Bundle b = activity.bundle;
        SingerDTO s = (SingerDTO) b.getSerializable("dto");
        TextView t =view.findViewById(R.id.tvMain);
        t.setText(s.name);
        ImageView i = view.findViewById(R.id.img);
        i.setImageResource(s.resId);
        return view;
    }
}
