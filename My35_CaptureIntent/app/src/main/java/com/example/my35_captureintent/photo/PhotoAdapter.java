package com.example.my35_captureintent.photo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my35_captureintent.MainActivity;
import com.example.my35_captureintent.R;

import java.util.ArrayList;


public class PhotoAdapter extends
        RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private static final String TAG = "main:SingerAdapter";

    // 메인에게 넘겨받는것 -> 반드시 : Context, ArrayList<DTO>
    Context context;
    ArrayList<PhotoDTO> dtos;

    LayoutInflater inflater;
    MainActivity activity;

    // 생성자로 메인에서 넘겨받은것들을 연결
    public PhotoAdapter(Context context, ArrayList<PhotoDTO> dtos , MainActivity a) {

        this.context = context;
        this.dtos = dtos;
        activity = a;
        // 화면 붙이는 객체를 생성
        inflater = LayoutInflater.from(context);

    }

    ////////////////////////////////////////////////////
    // 매소드는 여기에 만든다
    // dtos에 dto를 추가하는 매소드
    public void addDto(PhotoDTO dto){
        dtos.add(dto);
    }

    // dtos의 특정 위치에 있는 dto를 삭제하는 매소드
    public void delDto(int position){
        dtos.remove(position);
    }

    ////////////////////////////////////////////////////

   // 화면을 인플레이트 시킨다 (붙인다)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.photoview,
                parent, false);

        return new ViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        PhotoDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를
        // 사용하여 데이터를 셋팅시킨다
        holder.setDto(dto);

        // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoDTO dto1 = dtos.get(position);
                //// 여기 3
                Bundle b = new Bundle();
                b.putSerializable("dto",dto1);
                activity.fragmentControl(new FragSend(),b);

                Toast.makeText(context,"전송사진 : " + dto1.getPhoto() + "등록된 마리수는 : " + getItemCount()+"마리 입니다", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // dtos에 저장된 dto 갯수
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
    public class ViewHolder extends RecyclerView.ViewHolder{
        // singerview.xml 에서 사용된 모든 위젯을 정의한다
        TextView tvDog, tvSex,tvAddr,tvAge;
        ImageView ivImage;
        LinearLayout parentLayout;

        // singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);

            ivImage = itemView.findViewById(R.id.ivImage);

        }

        // singerview에 데이터를 연결시키는 매소드를 만든다
        public void setDto(PhotoDTO dto){
            ivImage.setImageURI(dto.getPhoto());
        }

    }

    private void showMessage(int position) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(context);
        builder.setTitle("안내");
        builder.setMessage("삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dtos.remove(position);
                delDto(position);
                // 지우거나 추가하면 반드시 화면을 갱신시켜야 한다
                notifyDataSetChanged();
            }
        });

        // 아니요 버튼
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
