package com.example.prm392project.presentation.store.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.model.Chat;
import com.example.prm392project.presentation.store.PagerFragment;
import com.example.prm392project.presentation.store.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    CardView sendbtn;
    EditText textmsg;

    Button backbtn;
    RecyclerView mesRecycleView;

    List<Chat> messageArrayList ;

    MessageAdapter mesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sendbtn = findViewById(R.id.sendbtnn);
        textmsg = findViewById(R.id.textmsg);
        mesRecycleView = findViewById(R.id.msgadpter);
        backbtn = findViewById(R.id.btn_backHome);


        messageArrayList = ChatShareRfr.getItems(getApplicationContext());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);

        mesRecycleView.setLayoutManager(linearLayoutManager);
        mesAdapter = new MessageAdapter( this, messageArrayList);
        mesRecycleView.setAdapter(mesAdapter);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = textmsg.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Enter message first!", Toast.LENGTH_SHORT).show();
                }
                textmsg.setText("");
                Chat currentchat = new Chat(message);
//                messageArrayList.add(currentchat);

//
//                mesAdapter.notifyDataSetChanged();

                messageArrayList.add(currentchat);
                ChatShareRfr.saveItems(getApplicationContext(), messageArrayList);
//                mesAdapter.notifyDataSetChanged();
                Toast.makeText(ChatActivity.this, messageArrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
