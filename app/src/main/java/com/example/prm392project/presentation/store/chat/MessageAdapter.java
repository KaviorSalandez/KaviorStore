package com.example.prm392project.presentation.store.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.prm392project.R;
import com.example.prm392project.model.Chat;
import com.example.prm392project.presentation.store.product.ProductItemAdapter;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.senderViewHolder> {

    Context context;
    List<Chat> messageList;


    public MessageAdapter(Context context, List<Chat> messageList) {
        this.context = context;
        this.messageList = messageList;

    }


    @NonNull
    @Override
    public senderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat, parent, false);
//        return new senderViewHolder(view);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sender_layout, parent, false);
        return new MessageAdapter.senderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull senderViewHolder holder, int position) {
         Chat mes = messageList.get(position);
        Log.d("Texttest", "onBindViewHolder: " + holder.mesTxt);
        holder.mesTxt.setText(mes.getMsg());

    }

    @Override
    public int getItemCount() {
        if(messageList == null) return 0;
        return  messageList.size();
    }


    public static class senderViewHolder extends RecyclerView.ViewHolder {
        private TextView mesTxt;
        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            mesTxt = itemView.findViewById(R.id.msgSend);

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
