package com.example.my28_recyclerview3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3,
                container, false);
          activity =(MainActivity)getActivity();
          Bundle b = activity.bundle;
          PhotoDTO d = (PhotoDTO) b.getSerializable("dto");

            ImageView i = view.findViewById(R.id.img);
            i.setImageURI(d.getPhoto());

        return view;
    }
}
