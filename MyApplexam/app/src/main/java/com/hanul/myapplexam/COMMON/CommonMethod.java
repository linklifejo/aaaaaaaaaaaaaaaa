package com.hanul.myapplexam.COMMON;

import android.widget.Toast;

import com.google.gson.Gson;
import com.hanul.myapplexam.ASyncTask.ApiClient;
import com.hanul.myapplexam.ASyncTask.ApiInterface;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonMethod {

    public static String ipConfig = "http://192.168.0.3:8989/app/";


    // 해당하는 getData라는 메소드를 여러 액티비티와 프래그먼트에서 재활용을 통해
    // Spring에 연결 후 데이터를 가지고 오는 처리를 해야함.
    // 어떤식으로 바뀌면 재활용이 편할까???
    private HashMap<String , Object> params = new HashMap<>();
    MultipartBody.Part file;

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }

    // 파라미터 데이터 넣는 메소드
    public void setParams(String key , Object value){
        params.put(key,value);
    }

    // 데이터 보내는 메소드 : "anJoin",완료했을때 골백 실행
    public void getData(String url , Callback<String> callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest =  apiInterface.getData(url , params);
        apiTest.enqueue(callback);
    }

        // 데이터와 파일을 동시에 보낼때: "anJoin", 사진파일, 완료후 콜백
    public void sendFile(String url , MultipartBody.Part part, Callback<String> callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest =  apiInterface.connFilesPost(url, stringToRequest(), part);
        apiTest.enqueue(callback);
    }

    //Multipart로 데이터 동시에 보내서 요청
    public RequestBody stringToRequest(){
        RequestBody data = null;
        if(!params.isEmpty()){
            data = RequestBody.create(
                    MediaType.parse("multipart/form-data"), new Gson().toJson(
                            params.get("param")
                    )
            );
        }
        return data;
    }



}
