package com.example.firebase_test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {

    private ArrayList<Book_item> aBook_list;
    private Recycler_Adapter_books.OnItemClickListener onItemClickListener;

    @NotNull
    @Override
    public Recycler_Adapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull Recycler_Adapter.ViewHolder holder, int position) {
        holder.onBind(aBook_list.get(position));
    }

    public void setFriendList(ArrayList<Book_item> list){
        this.aBook_list = list;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(Recycler_Adapter_books.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 아이템 클릭을 처리할 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View view, Book_info item);
    }
    @Override
    public int getItemCount() {
        return aBook_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView book_image;
        TextView book_name;
        TextView book_author;
        TextView book_publisher;
        TextView book_price;
        public ViewHolder(@NotNull View itemView) {
            super(itemView);

            book_image = (ImageView) itemView.findViewById(R.id.image_book);
            book_name = (TextView) itemView.findViewById(R.id.book_name);
            book_author = (TextView) itemView.findViewById(R.id.book_author);
            book_publisher = (TextView) itemView.findViewById(R.id.book_publisher);
            book_price =(TextView) itemView.findViewById(R.id.book_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Log.d("tag", "Clicked on item at position ----------------------------- " + position);
                    }
                }
            });
        }

        void onBind(Book_item item){
            book_image.setImageResource(item.getBook_image());
            book_name.setText(item.getBook_name());
            book_author.setText(item.getBook_author());
            book_publisher.setText(item.getBook_publisher());
            book_price.setText(item.getBook_price());
        }
    }
}