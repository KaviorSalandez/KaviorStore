package com.example.prm392project.presentation.store.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.model.Chat;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    String SenderUID;
    TextView reciverNName;
    public  static String senderImg;
    public  static String reciverIImg;
    CardView sendbtn;
    EditText textmsg;

    RecyclerView messageAdapter;

    ArrayList<Chat> messageArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendbtn = findViewById(R.id.sendbtnn);
        textmsg = findViewById(R.id.textmsg);
        messageAdapter = findViewById(R.id.msgadpter);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = textmsg.getText().toString();
                if(message.isEmpty()){
                    Toast.makeText(ChatActivity.this, "Enter message first!", Toast.LENGTH_SHORT).show();
                }
                textmsg.setText("");
                Chat currentchat = new Chat(message);
                messageArrayList.add(currentchat);

            }
        });
    }
}
