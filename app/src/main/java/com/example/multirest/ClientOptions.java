package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ClientOptions extends AppCompatActivity {

    private Button signOut;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);

        signOut= (Button) findViewById(R.id.sign_out);
        mAuth = FirebaseAuth.getInstance();


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mAuth.signOut();//get signed out
                goToStart();
            }

        });



    }

    private void goToStart() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);



    }


}
