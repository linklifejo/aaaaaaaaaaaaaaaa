package com.hanul.myapplication2;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hanul.myapplication2.COMMON.CommonMethod;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MultiImageAdapter extends RecyclerView.Adapter<MultiImageAdapter.ViewHolder>{
    ImageView imageView;
    public int reqPicCode = 1004;
    private ArrayList<Uri> mData = null ;
    private Context mContext = null ;

    // 생성자에서 데이터 리스트 객체, Context를 전달받음.
    MultiImageAdapter(ArrayList<Uri> list, Context context) {
        mData = list ;
        mContext = context;

    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조.
            image = itemView.findViewById(R.id.image);
        }
    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    // LayoutInflater - XML에 정의된 Resource(자원) 들을 View의 형태로 반환.
    @Override
    public MultiImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.multi_image_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        MultiImageAdapter.ViewHolder vh = new MultiImageAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MultiImageAdapter.ViewHolder holder, int position) {
        Uri image_uri = mData.get(position) ;
        Glide.with(mContext)
                .load(image_uri)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 보낼 파일 MultipartBody.Part 생성

                CommonMethod commonMethod = new CommonMethod();
                //    commonMethod.setParams("param", dto);
                // 보낼 파일 MultipartBody.Part 생성
                // List<MultipartBody.Part> imgs = new ArrayList<>();
                RequestBody fileBody =null;
                MultipartBody.Part filePart =null;
                if(mData.size() > 0) {

                    fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File( mData.get(0).getPath()));
                    filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                }
                // 데이터를 파라메터로 보낸다
              //  commonMethod.setParams("param", "jobyx");
                // 서버로 보내는 부분
                commonMethod.sendFile("upload.f", filePart, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(mContext,"onResponse: 보내기 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mContext,"onResponse: 보내기 실패", Toast.LENGTH_SHORT).show();

                    }
                });
    //            Toast.makeText(mContext," " + image_uri, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
