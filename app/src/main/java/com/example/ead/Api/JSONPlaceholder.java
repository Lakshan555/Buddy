package com.example.ead.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JSONPlaceholder {
    @GET("test")
    Call<List<Post>> getPost();



}
