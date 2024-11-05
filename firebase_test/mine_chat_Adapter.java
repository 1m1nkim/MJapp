package com.example.firebase_test;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class mine_chat_Adapter extends RecyclerView.Adapter<mine_chat_Adapter.ViewHolder> {
    public static int chatdataposition;
    private ArrayList<My_chat_info> lastChat;
    private OnItemClickListener onItemClickListener;
    public static int pIndex = -1;
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_chat_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        holder.onBind(lastChat.get(position));
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 아이템 클릭을 처리할 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View view, My_chat_info item);
    }
    public void setChatsList(ArrayList<My_chat_info> list){
        this.lastChat = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (lastChat != null) ? lastChat.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView chat_last;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);

            chat_last = itemView.findViewById(R.id.chat_last);
            chat_last.setText(Book_home.books.get(chatdataposition).getSell_name_ed().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chatdataposition = getAdapterPosition();

                    if (chatdataposition != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(itemView.getContext(), Chatting_room_in.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        itemView.getContext().startActivity(intent);

                    }
                }
            });
        }

        void onBind(My_chat_info item){

        }
    }
}
