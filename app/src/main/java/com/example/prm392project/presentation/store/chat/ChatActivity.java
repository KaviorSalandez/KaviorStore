package com.example.prm392project.presentation.store.chat;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.prm392project.common.api.ApiService;
import com.example.prm392project.model.Chat;
import com.example.prm392project.model.User;
import com.example.prm392project.presentation.store.PagerFragment;
import com.example.prm392project.presentation.store.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    CardView sendbtn;
    EditText textmsg;

    Button backbtn;
    RecyclerView mesRecycleView;

    List<Chat> messageArrayList ;
    User user;

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
        user = getUserLoggedIn();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        mesRecycleView.setLayoutManager(linearLayoutManager);
        mesAdapter = new MessageAdapter( this, messageArrayList);
        mesRecycleView.setAdapter(mesAdapter);
        mesAdapter.notifyDataSetChanged();
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = textmsg.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Enter message first!", Toast.LENGTH_SHORT).show();
                }
                else{
                    textmsg.setText("");
                    Chat currentchat;

                        currentchat = new Chat(user.getId(), message);

                        messageArrayList.add(currentchat);
                    ChatShareRfr.saveItems(getApplicationContext(), messageArrayList);
//                    ChatShareRfr2.saveItems(getApplicationContext(), messageArrayList2);
                    mesAdapter.notifyDataSetChanged();
                }


            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private User getUserLoggedIn(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("USER_TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        ApiService.apiService.getLoggedInUserProfile("Bearer " + token)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            user = response.body();
                            UserShareRfr.saveItems(getApplicationContext(),response.body());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "aaa", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
        return user;
    }
}
