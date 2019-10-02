package com.example.reliancehmo;

import com.example.reliancehmo.models.AddProviderResponse;
import com.example.reliancehmo.models.GetProviderResponse;
import com.example.reliancehmo.models.Provider;
import com.example.reliancehmo.models.UploadImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("provider/{id}")
    Call<GetProviderResponse> getProvider(@Path("id") String id);

    @GET("providers")
    Call<GetProviderResponse> getProviders(@Query("q") String searchQuery);

    @Headers({"Accept: application/json"})
    @POST("providers")
    Call<AddProviderResponse> addProvider(@Body Provider provider);

    @Headers({"Accept: application/json"})
    @POST("uploads/")
    Call<UploadImageResponse> upload(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);

}
