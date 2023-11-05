package com.example.prm392project.presentation.store.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.prm392project.R;
import com.example.prm392project.model.Chat;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.senderViewHolder> {

    Context context;
    List<Chat> messageList;


    public MessageAdapter(Context context, List<Chat> messageList) {
        this.context = context;
        this.messageList = messageList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public senderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_chat, parent, false);
        return new senderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull senderViewHolder holder, int position) {
        final Chat mes = messageList.get(position);
        holder.msgtxt.setText(mes.getMsg());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class senderViewHolder extends RecyclerView.ViewHolder {
        TextView msgtxt;
        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            msgtxt = itemView.findViewById(R.id.msgsendertyp);

        }
    }

//    public class reciverViewHolder extends RecyclerView.ViewHolder {
//        TextView msgtxt;
//
//        public reciverViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//            msgtxt = itemView.findViewById(R.id.recivertextset);
//        }
//    }


}
