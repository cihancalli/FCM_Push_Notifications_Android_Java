package com.zerdasoftware.fcmpushnotifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private EditText mTitle, mMessage;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TabA_Token = "DEVICE_TOKEN";


        mTitle = findViewById(R.id.eTxt_title);
        mMessage = findViewById(R.id.eTxt_message);
        send = findViewById(R.id.btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString().trim();
                String message = mMessage.getText().toString().trim();
                if (!title.equals("") && !message.equals("")) {
                    FCMSend.pushNotification(MainActivity.this,
                            TabA_Token,
                            title,
                            message);
                }
            }
        });
    }

    public void getToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        //Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("TOKEN "+ token);

                    }
                });
    }
}