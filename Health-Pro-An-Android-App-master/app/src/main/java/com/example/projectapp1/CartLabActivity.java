package com.example.projectapp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
    ArrayList list;
    HashMap <String,String> item;
    SimpleAdapter sa;
    ListView lst;
    TextView tvtotal;
    Button btnCheckout,btnBack;
    private String[][]packages={};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_lab);

        btnCheckout=findViewById(R.id.buttoncartCheckout);
        btnBack=findViewById(R.id.buttonCartBack);
        tvtotal=findViewById(R.id.textViewCartTotalCost);
        lst=findViewById(R.id.listViewCartProducts);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        database db=new database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"lab");
        Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_LONG).show();

        packages=new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        for(int i=0;i< dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String []strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost:"+strData[i]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strData[1]);
        }
       // tvtotal=findViewById(R.id.textViewCartTotalCost);
        tvtotal.setText("TotalCost:"+totalAmount);

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4] +"/-");
            list.add(item);}
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this,LabTestActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CartLabActivity.this,LabTestBookActivity.class);
                it.putExtra("price",tvtotal.getText());
                //it.putExtra("date",tvtotal.getText());
               // it.putExtra();
                startActivity(it);
            }
        });


    }
}