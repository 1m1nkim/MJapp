package com.example.firebase_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Notice_Adapter extends RecyclerView.Adapter<Notice_Adapter.ViewHolder> {
    private ArrayList<Notice_item> items = null;
    public static int position;
    public static int nIndex = -1;

    //======[Click 이벤트 구현을 위해 추가된 코드]==============
    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    //OnItemClickListener 참조 변수 선언
    public static OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public static void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    //==========================================================

    //----뷰홀더 클래스--------------------------------------------
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);

            // 뷰 객체에 대한 참조
            textView = itemView.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    //-------------------------------------------------------------

    // 생성자
    Notice_Adapter(ArrayList<Notice_item> list){
        items = list;
    }

    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public Notice_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_notice, parent, false);
        Notice_Adapter.ViewHolder vh = new Notice_Adapter.ViewHolder(view);

        //
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String data = "";
                position = vh.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    data = vh.getTextView().getText().toString();
                }

                itemClickListener.onItemClicked(position);
            }
        });
        //asd
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시
    @Override
    public void onBindViewHolder(Notice_Adapter.ViewHolder holder, int position) {
        String text = items.get(position).title;

        holder.textView.setText(text);
    }

    // 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Notice_item> list){
        items = list;
        notifyDataSetChanged();

    }
}