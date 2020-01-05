package com.example.multirest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class ClientOptions extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button signOut;
    private Button mButton;
    private Button OpenMenu;
    private Button callW;
    private Button myOrders;
    FirebaseAuth mAuth;
    private static String text;
    private Button camerab;
    String pathToFile;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);
        Spinner spinner =findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        signOut= (Button) findViewById(R.id.sign_out);
        mButton=(Button) findViewById(R.id.showMenu);
        camerab = (Button) findViewById(R.id.camera);
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[] {Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        imageView = findViewById(R.id.image);
        camerab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicAction();

            }
        });
        callW=(Button) findViewById(R.id.button2);
        myOrders=(Button)findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();
        OpenMenu = (Button) findViewById(R.id.showMenu);
        OpenMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mAuth.signOut();//get signed out
                goToStart();
            }

        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyOrders();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void takePicAction() {
        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepic.resolveActivity(getPackageManager()) != null){
            File photofile= null;
            photofile = createPhotoFile();
            if(photofile != null){
                pathToFile = photofile.getAbsolutePath();
                Uri photoURI= FileProvider.getUriForFile(ClientOptions.this, "com.example.multirest.fileprovider", photofile);
                takepic.putExtra(MediaStore.EXTRA_OUTPUT , photoURI);
                startActivityForResult(takepic , 1);

            }


        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss" ).format(new Date());
        File storgeDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name ,".jpg",storgeDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private void goToStart() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);



    }

    public static String getTable(){
        return text;
    }

    public void openMenu(){
        Intent intent=new Intent(this,menu.class);
        startActivity(intent);    }


    public void openMyOrders(){
        Intent intent=new Intent(this,myOrders.class);
        startActivity(intent);    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
