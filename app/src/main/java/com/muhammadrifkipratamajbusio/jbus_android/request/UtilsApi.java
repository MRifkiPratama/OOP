package com.muhammadrifkipratamajbusio.jbus_android.request;

/**
 * The type Utils api.
 */
public class UtilsApi {
    /**
     * The constant BASE_URL_API.
     */
    public static final String BASE_URL_API = "http://10.0.2.2:5000/";

    /**
     * Gets api service.
     *
     * @return the api service
     */
    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
