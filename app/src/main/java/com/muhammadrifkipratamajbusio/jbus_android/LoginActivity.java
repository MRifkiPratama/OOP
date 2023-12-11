package com.muhammadrifkipratamajbusio.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity {
    private BaseApiService mApiService;
    private Context mContext;
    private EditText email, password;
    private TextView registerNow = null;
    private Button loginButton = null;
    /**
     * The Logged acccount.
     */
    static Account loggedAcccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        // Load the components to the variables
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerNow = findViewById(R.id.register_now);
        loginButton = findViewById(R.id.reg_button);

        // other variable
        mContext = this;
        mApiService = UtilsApi.getApiService();

        // adding listener
        registerNow.setOnClickListener(v -> {
            moveActivity(this, RegisterActivity.class);
        });

        loginButton.setOnClickListener(v -> {
            handleLogin();
        });
    }

    private void moveActivity(Context ctx, Class<?> cls) {
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Handle login.
     */
    protected void handleLogin() {
        // handling empty field
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        if (emailS.isEmpty() || passwordS.isEmpty()) {
            viewToast(mContext, "Field cannot be empty");
            return;
        }

        // case if not empty then make a request
        mApiService.login(emailS, passwordS).enqueue(new Callback<BaseResponse<Account>>() {
            @Override
            public void onResponse(Call<BaseResponse<Account>> call, Response<BaseResponse<Account>> response) {
                // handle the potential 4xx & 5xx error
                if (!response.isSuccessful()) {
                    viewToast(mContext, "Application error " + response.code());
                    return;
                }

                // here always response code 200
                // ini nampung response tipenya samain ky yang di backend
                BaseResponse<Account> res = response.body();
                if (res.success) {
                    loggedAcccount = res.payload;
                    viewToast(mContext, "Welcome to JBus " + loggedAcccount.name);
                    email.setText("");
                    password.setText("");
                    startActivity(new Intent(mContext, MainActivity.class));
                } else {
                    viewToast(mContext, res.message);
                }
            }

            // in case client failed to make a request
            @Override
            public void onFailure(Call<BaseResponse<Account>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}