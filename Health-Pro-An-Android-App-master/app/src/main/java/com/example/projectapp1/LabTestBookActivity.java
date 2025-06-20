package com.example.projectapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edContact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_book);

        edname=findViewById(R.id.editTextlabcartName);
        edaddress=findViewById(R.id.editTextlabcartAddress);
        edContact=findViewById(R.id.editTextlabcartContactNo);
        edpincode=findViewById(R.id.editTextlabcartPinCode);
        btnBooking=findViewById(R.id.buttonlabcartBook);

        Intent intent=getIntent();
        String[] price= intent.getStringArrayExtra("price").toString().split(java.util.regex.Pattern.quote(":"));

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                database db= new database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edContact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),"lab",Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");

                Toast.makeText(getApplicationContext(),"Your order placed successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, Home.class));
            }
        });

    }
}