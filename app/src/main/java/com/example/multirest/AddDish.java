package com.example.multirest;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.multirest.ui.Dish;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class AddDish extends AppCompatActivity {

    //var
    EditText txtname,txtprice ,txtdescription;
    Button btnsave;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Dish d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        btnsave=(Button) findViewById(R.id.button6);
        txtname=(EditText)findViewById(R.id.editText7);
        txtprice=(EditText)findViewById(R.id.editText3);
        txtdescription=(EditText)findViewById(R.id.editText6);
        myRef=FirebaseDatabase.getInstance().getReference().child("DISH");

        //save button
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double p=Double.parseDouble(txtprice.getText().toString().trim());
                String n=txtname.getText().toString();
                String des=txtdescription.getText().toString();
                d=new Dish();
                d.setName(n);
                d.setPrice(p);
                d.setDesc(des);
                myRef.push().setValue(d);
                Toast.makeText(AddDish.this,"data inserted sucessfuly",Toast.LENGTH_LONG).show();
            }


        });

    }
}
