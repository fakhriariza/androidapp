package com.lpikitb.lpikitbmobile.Activity.Chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.lpikitb.lpikitbmobile.Model.ChatListModel;
import com.lpikitb.lpikitbmobile.Network.Extras;
import com.lpikitb.lpikitbmobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {

    private Context context;
    private List<ChatListModel> chatListModelList;

    public ChatListAdapter(Context context, List<ChatListModel> chatListModelList) {
        this.context = context;
        this.chatListModelList = chatListModelList;
    }

    @NonNull
    @Override
    public ChatListAdapter.ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent,false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ChatListAdapter.ChatListViewHolder holder, int position) {

        ChatListModel chatListModel = chatListModelList.get(position);

        holder.tvNamaChat.setText(chatListModel.getNama_lengkap());
        holder.clChatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, ChatActivity2.class);
                intent.putExtra(Extras.USER_KEY, chatListModel.getUserid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatListModelList.size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {

        private CardView cvChat;
        private TextView tvNamaChat, tvStatus, tvTime, tvUnreadCount;
        private ConstraintLayout clChatList;


        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);

            cvChat = itemView.findViewById(R.id.cvChat);
            tvNamaChat = itemView.findViewById(R.id.tvNamaChat);
            tvTime = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvUnreadCount = itemView.findViewById(R.id.tvUnreadCount);
            clChatList = itemView.findViewById(R.id.clChatList);
        }
    }
}
