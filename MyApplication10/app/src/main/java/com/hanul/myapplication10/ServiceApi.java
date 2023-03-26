package com.hanul.myapplication10;

public class ServiceApi {
    @Multipart
    @POST("/user/profile") //프로필 사진 업데이트
    Call<ProfileResponse> profile (@Part MultipartBody.Part profile, @Header("token") String token);
}
