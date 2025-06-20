package com.example.projectapp1;

import static androidx.compose.ui.semantics.SemanticsPropertiesKt.setText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvpackageName,tvTotalCost;
    TextView tvDetails;
    Button btnAddToCart,btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_details);


        tvTotalCost=findViewById(R.id.textViewTotalCost);
        tvDetails=findViewById(R.id.textViewPackageDetails);

        btnBack=findViewById(R.id.buttonLTBack);
        btnAddToCart=findViewById(R.id.buttonLTAddToCart);

        Intent intent=getIntent();
        tvDetails.setText(intent.getStringExtra("text2"));
       tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
           }
       });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                //String product = tvpackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(), "healthcare", null, 1);

                if (db.checkCart(username) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, price,"lab");
                    Toast.makeText(getApplicationContext(), "Product Added to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            };



    });
}}