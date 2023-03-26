package com.hanul.myapplication9;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class DataService {
    private String BASE_URL = "http://192.168.0.3:8989/app/"; // TODO REST API 퍼블릭 IP로 변경

    Retrofit retrofitClient;

    {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(new OkHttpClient.Builder().build());
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofitClient = builder.build();
    }

    SelectAPI select = retrofitClient.create(SelectAPI.class);
    InsertAPI insert = retrofitClient.create(InsertAPI.class);
    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
}

interface SelectAPI{
    @GET("select/{id}")
    Call<Member> selectOne(@Path("id") long id);

    @GET("select")
    Call<List<Member>> selectAll();
}
interface InsertAPI{
    @POST("insert")
    Call<Member> insertOne(@Body Map<String, String> map);
}

interface UpdateAPI{
    @POST("update/{id}")
    Call<Member> updateOne(@Path("id") long id, @Body Map<String, String> map);
}

interface DeleteAPI{
    @POST("delete/{id}")
    Call<ResponseBody> deleteOne(@Path("id") long id);
}
