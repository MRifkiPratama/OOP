package com.muhammadrifkipratamajbusio.jbus_android.request;

import com.muhammadrifkipratamajbusio.jbus_android.model.Account;
import com.muhammadrifkipratamajbusio.jbus_android.model.BaseResponse;
import com.muhammadrifkipratamajbusio.jbus_android.model.Bus;
import com.muhammadrifkipratamajbusio.jbus_android.model.BusType;
import com.muhammadrifkipratamajbusio.jbus_android.model.Facility;
import com.muhammadrifkipratamajbusio.jbus_android.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The interface Base api service.
 */
public interface BaseApiService {
    /**
     * Login call.
     *
     * @param email    the email
     * @param password the password
     * @return the call
     */
    @POST("account/login")
    Call<BaseResponse<Account>> login (
            @Query("email") String email,
            @Query("password") String password);

    /**
     * Register call.
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     * @return the call
     */
    @POST("account/register")
    Call<BaseResponse<Account>> register (
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password);

    /**
     * Gets accountby id.
     *
     * @param id the id
     * @return the accountby id
     */
    @GET("account/{id}")
    Call<Account> getAccountbyId(@Path("id") int id);

    /**
     * Register renter call.
     *
     * @param id          the id
     * @param companyName the company name
     * @param address     the address
     * @param phoneNumber the phone number
     * @return the call
     */
    @POST("account/{id}/registerRenter")
    Call<BaseResponse<Account>> registerRenter(
            @Path("id") int id,
            @Query("companyName") String companyName,
            @Query("address") String address,
            @Query("phoneNumber") String phoneNumber);

    /**
     * Number of buses call.
     *
     * @return the call
     */
    @GET("bus/total")
    Call<Integer> numberOfBuses();

    /**
     * Gets bus.
     *
     * @param page     the page
     * @param pageSize the page size
     * @return the bus
     */
    @GET("bus/page")
    Call<List<Bus>> getBus(@Query("page") int page, @Query("size") int pageSize);

    /**
     * Add bus call.
     *
     * @param accountId          the account id
     * @param name               the name
     * @param capacity           the capacity
     * @param facilities         the facilities
     * @param busType            the bus type
     * @param price              the price
     * @param stationDepartureId the station departure id
     * @param stationArrivalId   the station arrival id
     * @return the call
     */
    @POST("bus/create")
    Call<BaseResponse<Bus>> addBus(
            @Query("accountId") int accountId,
            @Query("name") String name,
            @Query("capacity") int capacity,
            @Query("facilities") List<Facility> facilities,
            @Query("busType") BusType busType,
            @Query("price") int price,
            @Query("stationDepartureId") int stationDepartureId,
            @Query("stationArrivalId") int stationArrivalId
    );

    /**
     * Gets my bus.
     *
     * @param accountId the account id
     * @return the my bus
     */
    @GET("bus/getMyBus")
    Call<List<Bus>> getMyBus(@Query("accountId") int accountId);

    /**
     * Gets busby id.
     *
     * @param busId the bus id
     * @return the busby id
     */
    @GET("bus/{id}")
    Call<Bus> getBusbyId(@Path("id") int busId);

    /**
     * Add schedule call.
     *
     * @param busId the bus id
     * @param time  the time
     * @return the call
     */
    @POST("bus/addSchedule")
    Call<BaseResponse<Bus>> addSchedule(@Query("busId") int busId,
                                        @Query("time") String time);

    /**
     * Top up call.
     *
     * @param id     the id
     * @param amount the amount
     * @return the call
     */
    @POST("account/{id}/topUp")
    Call<BaseResponse<Double>> topUp (@Path("id") int id, @Query("amount") double amount);

    /**
     * Gets all station.
     *
     * @return the all station
     */
    @GET("station/getAll")
    Call<List<Station>> getAllStation();
}
