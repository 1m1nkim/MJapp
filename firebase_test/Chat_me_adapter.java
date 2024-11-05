package com.example.firebase_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Chat_me_adapter extends RecyclerView.Adapter<Chat_me_adapter.ViewHolder> {
    private ArrayList<Chat_me> chats;

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_me_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        holder.onBind(chats.get(position));
    }

    public void setChatsList(ArrayList<Chat_me> list) {
        this.chats = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (chats != null) ? chats.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView chats;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            chats = itemView.findViewById(R.id.chat_last); // 수정 필요한 부분

        }

        void onBind(Chat_me item) {
            chats.setText(item.getChat_me());
        }
    }
}
