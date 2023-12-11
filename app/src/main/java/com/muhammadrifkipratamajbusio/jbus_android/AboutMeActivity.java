package com.muhammadrifkipratamajbusio.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammadrifkipratamajbusio.jbus_android.model.Account;
import com.muhammadrifkipratamajbusio.jbus_android.model.BaseResponse;
import com.muhammadrifkipratamajbusio.jbus_android.request.BaseApiService;
import com.muhammadrifkipratamajbusio.jbus_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type About me activity.
 */
public class AboutMeActivity extends AppCompatActivity {
    private BaseApiService mApiService;
    private Context mContext;
    /**
     * The Initial.
     */
    TextView initial = null;
    /**
     * The Username.
     */
    TextView username = null;
    /**
     * The Email.
     */
    TextView email = null;
    /**
     * The Balance.
     */
    TextView balance = null;
    /**
     * The Status renter.
     */
    TextView statusRenter = null;
    /**
     * The Register company.
     */
    TextView registerCompany = null;
    /**
     * The Amount.
     */
    EditText amount = null;
    /**
     * The Top up button.
     */
    Button topUpButton = null;
    /**
     * The Manage bus.
     */
    Button manageBus = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        initial = this.findViewById(R.id.init);
        username = this.findViewById(R.id.username);
        email = this.findViewById(R.id.email);
        balance = this.findViewById(R.id.balance);
        amount = this.findViewById(R.id.top_up_amount);
        statusRenter = this.findViewById(R.id.status_renter);
        registerCompany = this.findViewById(R.id.register_company);
        topUpButton = this.findViewById(R.id.top_up_button);
        manageBus = this.findViewById(R.id.button_manage_bus);

        mContext = this;
        mApiService = UtilsApi.getApiService();
        handleRefreshAccount();

        topUpButton.setOnClickListener(v->{
            handleTopUp();
        });

    }

    private void loadData(Account a) {
        initial.setText(""+a.name.toUpperCase().charAt(0));
        username.setText(a.name);
        email.setText(a.email);
        balance.setText("IDR "+a.balance);
        if (a.company == null) {
            statusRenter.setText("You're not registered as a renter");
            manageBus.setVisibility(View.GONE);
            registerCompany.setVisibility(View.VISIBLE);
        } else {
            statusRenter.setText("You're already registered as a renter");
            manageBus.setVisibility(View.VISIBLE);
            registerCompany.setVisibility(View.GONE);
        }
    }
    private void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx,cls);
        startActivity(intent);
    }

    /**
     * Handle top up.
     */
    protected void handleTopUp() {
        String amountS = amount.getText().toString();
        Double amountD = amountS.isEmpty() ? 0d : Double.parseDouble(amountS);
        mApiService.topUp(LoginActivity.loggedAcccount.id, amountD).enqueue(new Callback<BaseResponse<Double>>() {
            @Override
            public void onResponse(Call<BaseResponse<Double>> call, Response<BaseResponse<Double>> response) {
                BaseResponse<Double> res = response.body();
                if(res.success) {
                    balance.setText("IDR "+res.payload);
                }
                Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse<Double>> call, Throwable t) {

            }
        });
    }

    /**
     * Handle refresh account.
     */
    protected void handleRefreshAccount() {
        BaseApiService mApiService = UtilsApi.getApiService();
        mApiService.getAccountbyId(LoginActivity.loggedAcccount.id).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "App error", Toast.LENGTH_SHORT).show();
                    return;
                }

                Account responseAccount = response.body();
                loadData(responseAccount);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
            }
        });
    }
}