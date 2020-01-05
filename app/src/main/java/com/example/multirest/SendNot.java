package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendNot extends AppCompatActivity {
    Button send;
    EditText msg1;
    EditText msg2;
    EditText msg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_not);
        send=(Button)findViewById(R.id.button4);
        msg1=(EditText)findViewById(R.id.editText7);
        msg2=(EditText)findViewById(R.id.editText8);
        msg3=(EditText)findViewById(R.id.editText9);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=msg1.getText().toString().trim();
                String s2=msg2.getText().toString().trim();
                String s3=msg3.getText().toString().trim();
                NotificationManager m=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder(getApplicationContext()).
                        setContentTitle(s2).setContentText(s3).setContentTitle(s1).setSmallIcon(R.drawable.multi).build();
                notify.flags |=Notification.FLAG_AUTO_CANCEL;
                m.notify(0,notify);

            }
        });
    }
}
