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
import com.example.multirest.ui.Dish;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class AddDish extends AppCompatActivity {
EditText txtname,txtprice;
Button btnsave;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        btnsave=(Button) findViewById(R.id.button6);
        txtname=(EditText)findViewById(R.id.editText);
        txtprice=(EditText)findViewById(R.id.editText2);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double p=Double.parseDouble(txtprice.getText().toString());
                Dish d=new Dish(txtname.getText().toString(),p);
                myRef.setValue(d);
            }


    });

}
}
