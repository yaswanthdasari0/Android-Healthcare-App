package com.example.projectapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMediciane extends AppCompatActivity {

    private String[][]packages={
            {"Acetaminophen (Tylenol):","","","","50"},
            {"Ibuprofen (Advil, Motrin):","","","","500"},
            {"Antihistamines (Benadryl, Claritin):","","","","4567"},
            {"Antacids (Tums, Maalox):","","","","509"},
            {"Multivitamins:","","","","670"}


    };
    private String[]package_details={
            "Building and keeping the bones and teeth strong\n"+
                    "reducing Fatigue and stress and muscualar pain\n"+
                    "Boosting the immune system and increasing resistance aganist infection",
            "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) that helps reduce pain, inflammation, and fever\n"+" It is often used to relieve headaches, muscle aches, menstrual cramps, and minor injuries.\n",
            "Antihistamines are commonly used to relieve allergy symptoms such as sneezing, itching, watery eyes, and runny nose.\n"+"They work by blocking the effects of histamine, a substance produced by the body during an allergic reaction.\n",
            "Antacids are medications used to neutralize stomach acid and provide relief from heartburn, indigestion, and acid reflux.\n"+" They work by reducing the acidity in the stomach.\n",
            "Multivitamins are dietary supplements that contain a combination of vitamins and minerals.\n"+" They are commonly used to supplement the diet and ensure adequate intake of essential nutrients.\n"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_mediciane);

        lst=(ListView)findViewById(R.id.listViewBM);
        btnBack=(Button)findViewById(R.id.buttonBMBack);
        btnGoToCart=(Button)findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMediciane.this,CartBuyMedicianeActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMediciane.this,Home.class));

        }
    });
        list=new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(BuyMediciane.this,BuyMedicianeDetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });
}}