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

public class Recycler_Adapter_books extends RecyclerView.Adapter<Recycler_Adapter_books.ViewHolder> {
    public static int bookposition;
    private ArrayList<Book_info> booksList;
    private OnItemClickListener onItemClickListener;
    public static int pIndex = -1;
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        holder.onBind(booksList.get(position));
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 아이템 클릭을 처리할 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View view, Book_info item);
    }
    public void setBooksList(ArrayList<Book_info> list){
        this.booksList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (booksList != null) ? booksList.size() : 0;
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
                    bookposition = getAdapterPosition();
                    if (bookposition != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(itemView.getContext(), Home_book_info.class);
                        pIndex = booksList.get(bookposition).getIndex();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

        void onBind(Book_info item){
            // Glide를 사용하여 이미지를 로드하고 ImageView에 표시
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl()) // getImageUrl() 메서드를 통해 이미지의 URL을 가져옴
                    .into(book_image);

            book_name.setText(item.getSell_name_ed());
            book_author.setText(item.getSell_author_ed());
            book_publisher.setText(item.getSell_publisher_ed());
            book_price.setText(item.getSell_price_ed());
        }
    }
}
