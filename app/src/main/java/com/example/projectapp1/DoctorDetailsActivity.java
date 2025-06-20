package com.example.projectapp1;

import static android.widget.AdapterView.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][]doctor_details1={
            {"Doctor Name:Ajay Pawar", "Hospital Address:Jaipur", "Exp:15 year","Mobile no:7894561233", "Fee:200"},
            {"Doctor Name:Ashok Panday", "Hospital Address:America", "Exp:19 year","Mobile no:7894561233","Fee:300"},
            {"Doctor Name:Geeta Pawar", "Hospital Address:Jaipur", "Exp:12 year","Mobile no:7894522633","Fee:200"},
            {"Doctor Name:Abhay", "Hospital Address:Japan", "Exp:10 year","Mobile no:7894565893","Fee:500"}

    };

    private String[][]doctor_details2={
            {"Doctor Name:Ajay Pawar", "Hospital Address:Jaipur", "Exp:15 year","Mobile no:7894561233","Fee:200"},
            {"Doctor Name:Ashok Panday", "Hospital Address:America", "Exp:19 year","Mobile no:7894561233","Fee:700"},
            {"Doctor Name:Geeta Pawar", "Hospital Address:Jaipur", "Exp:12 year","Mobile no:7894561233","Fee:500"},
            {"Doctor Name:Abhay", "Hospital Address:Japan", "Exp:10 year","Mobile no:7894561233","Fee:200"}

    };
    private String[][]doctor_details3={
            {"Doctor Name:Ajay Pawar", "Hospital Address:Jaipur", "Exp:15 year","Mobile no:7894561233","Fee:100"},
            {"Doctor Name:Ashok Panday", "Hospital Address:America", "Exp:19 year","Mobile no:7894561233","Fee:200"},
            {"Doctor Name:Geeta Pawar", "Hospital Address:Jaipur", "Exp:12 year","Mobile no:7894561233","Fee:200"},
            {"Doctor Name:Abhay", "Hospital Address:Japan", "Exp:10 year","Mobile no:7894561233","Fee:500"}

    };
    private String[][]doctor_details4={
            {"Doctor Name:Ajay Pawar", "Hospital Address:Jaipur", "Exp:15 year","Mobile no:7894561233","Fee:200"},
            {"Doctor Name:Ashok Panday", "Hospital Address:America", "Exp:19 year","Mobile no:7894561233","Fee:1200"},
            {"Doctor Name:Geeta Pawar", "Hospital Address:Jaipur", "Exp:12 year","Mobile no:7894561233","Fee:800"},
            {"Doctor Name:Abhay", "Hospital Address:Japan", "Exp:10 year","Mobile no:7894561233","Fee:500"}

    };
    private String[][]doctor_details5= {
            {"Doctor Name:Ajay Pawar", "Hospital Address:Jaipur", "Exp:15 year", "Mobile no:7894561233", "Fee:200"},
            {"Doctor Name:Ashok Panday", "Hospital Address:America", "Exp:19 year", "Mobile no:7894561233", "Fee:200"},
            {"Doctor Name:Geeta Pawar", "Hospital Address:Jaipur", "Exp:12 year", "Mobile no:7894561233", "Fee:2000"},
            {"Doctor Name:Abhay", "Hospital Address:Japan", "Exp:10 year", "Mobile no:7894561233", "Fee:2050"}

    };
            TextView tv;
    Button btn;
    String [][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DoctorDetailsActivity.this,Finddoctor.class));
        }

    });
        list =new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5",doctor_details[i][4] +"/-");
            list.add(item);}
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> adapterView, View view,int i,long l){
                Intent it= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);


            }

        });

}}
