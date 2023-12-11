package com.muhammadrifkipratamajbusio.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.muhammadrifkipratamajbusio.jbus_android.model.Price;

/**
 * The type Cancel payment.
 */
public class CancelPayment extends AppCompatActivity {
    /**
     * The Price.
     */
    TextView price;
    /**
     * The Username.
     */
    TextView Username;
    /**
     * The Confirm.
     */
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_payment);
        price = findViewById(R.id.price);
        Username = findViewById(R.id.username);
        confirm = findViewById(R.id.cancel_button);

        confirm.setOnClickListener(v -> {
            refund();
            moveActivity(this, AboutMeActivity.class);
        });
    }

    private void refund (){
        double Price = Double.parseDouble(String.valueOf(price));
        double refundAmount = Price * 0.8;
        LoginActivity.loggedAcccount.balance = LoginActivity.loggedAcccount.balance + (Price * 0.8);
    }

    private void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx,cls);
        startActivity(intent);
    }
}