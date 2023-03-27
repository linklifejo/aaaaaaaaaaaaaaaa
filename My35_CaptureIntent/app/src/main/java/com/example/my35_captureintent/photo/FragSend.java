package com.example.my35_captureintent.photo;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my35_captureintent.MainActivity;
import com.example.my35_captureintent.R;

public class FragSend extends Fragment {

MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_send,
                container, false);
       view.findViewById(R.id.btnRet).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               activity.fragmentControl(new FragTake());
           }
       });
          activity =(MainActivity)getActivity();
          Bundle b = activity.bundle;
          PhotoDTO d = (PhotoDTO) b.getSerializable("dto");

            ImageView i = view.findViewById(R.id.img);
           i.setImageURI(d.getPhoto());

        return view;
    }
}
