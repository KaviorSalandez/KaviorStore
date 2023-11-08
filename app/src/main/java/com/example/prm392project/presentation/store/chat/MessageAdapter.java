package com.example.prm392project.presentation.store.chat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.prm392project.R;
import com.example.prm392project.common.api.ApiService;
import com.example.prm392project.model.Chat;
import com.example.prm392project.model.User;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MessageAdapter extends RecyclerView.Adapter {

    Context context;
    List<Chat> messageList;
    List<Chat> messageList2;

    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    User user;


//    public MessageAdapter(Context context, List<Chat> messageList) {
//        this.context = context;
//        this.messageList = messageList;
//
//
//    }

    public MessageAdapter(Context context, List<Chat> messageList) {
        this.context = context;
        this.messageList = messageList;
    }


//    @NonNull
//    @Override
//    public senderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat, parent, false);
////        return new senderViewHolder(view);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.sender_layout, parent, false);
//        return new MessageAdapter.senderViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull senderViewHolder holder, int position) {
//         Chat mes = messageList.get(position);
//        Log.d("Texttest", "onBindViewHolder: " + holder.mesTxt);
//        holder.mesTxt.setText(mes.getMsg());
//
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
            return new senderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

//        Cha messages = messagesAdpterArrayList.get(position);

        Chat mes = messageList.get(position);

//        Log.d("Texttest", "onBindViewHolder: " + holder.mesTxt);
//        holder.mesTxt.setText(mes.getMsg());
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                new AlertDialog.Builder(context).setTitle("Delete")
//                        .setMessage("Are you sure you want to delete this message?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        }).show();
//
//                return false;
//            }
//        });

        // done
        if (holder.getClass() == senderViewHolder.class) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.mesTxt.setText(mes.getMsg());

                if (UserShareRfr.getUserChat(context).getId() == 1) {
                    Picasso.get().load(R.drawable.logostore).into(viewHolder.circleImageView);
                }
                else {
                    Picasso.get().load(R.drawable.user).into(viewHolder.circleImageView);
                }

        } else {
            reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(mes.getMsg());

                if (UserShareRfr.getUserChat(context).getId() == 1) {
                    Picasso.get().load(R.drawable.user).into(viewHolder.circleImageView);
                }
                else {
                    Picasso.get().load(R.drawable.logostore).into(viewHolder.circleImageView);
                }
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        Chat mes = messageList.get(position);
        user = UserShareRfr.getUserChat(context);
        if (user.getId() == mes.getUser_id()) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }


    public static class senderViewHolder extends RecyclerView.ViewHolder {
        private TextView mesTxt;
        private CircleImageView circleImageView;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            mesTxt = itemView.findViewById(R.id.msgSend);
            circleImageView = itemView.findViewById(R.id.profilerggg);

        }
    }

    public class reciverViewHolder extends RecyclerView.ViewHolder {
        private TextView msgtxt;
        private CircleImageView circleImageView;

        public reciverViewHolder(@NonNull View itemView) {

            super(itemView);
            msgtxt = itemView.findViewById(R.id.recivertextset);
            circleImageView = itemView.findViewById(R.id.pro);
        }
    }


}