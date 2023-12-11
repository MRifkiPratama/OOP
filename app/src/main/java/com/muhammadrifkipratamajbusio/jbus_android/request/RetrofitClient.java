package com.muhammadrifkipratamajbusio.jbus_android.request;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Retrofit client.
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    /**
     * Get client retrofit.
     *
     * @param baseUrl the base url
     * @return the retrofit
     */
    public static Retrofit getClient(String baseUrl){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            //ganti value header di bawah ini dengan nama kalian
                            .addHeader("Client-Name", "changeme")
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();
    }
}