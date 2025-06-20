package com.example.projectapp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyMedicianeDetails extends AppCompatActivity {
    TextView tvpackageName,tvTotalCost;
    TextView tvDetails;
    Button btnAddToCart,btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_mediciane_details);


        tvTotalCost=findViewById(R.id.textViewBMDetailsTotalCost);
        tvDetails=findViewById(R.id.textViewBMDetails);

        btnAddToCart=findViewById(R.id.buttonBMDetailsAddToCart);
        btnBack=findViewById(R.id.buttonBMDBack);

        Intent intent=getIntent();
        tvDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicianeDetails.this,BuyMediciane.class));

            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(), "healthcare", null, 1);

                if (db.checkCart(username) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, price,"lab");
                    Toast.makeText(getApplicationContext(), "Product Added to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicianeDetails.this, BuyMediciane.class));
                }
            }
        });
    }
}