package com.lpikitb.lpikitbmobile.Activity.Chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.lpikitb.lpikitbmobile.Model.ChatListModel;
import com.lpikitb.lpikitbmobile.Model.FindFriendModel;
import com.lpikitb.lpikitbmobile.Network.Constants;
import com.lpikitb.lpikitbmobile.Network.Extras;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FindFriendAdaptertiga extends RecyclerView.Adapter<FindFriendAdaptertiga.ChatViewHolder> {
    private Context context;
    private List<ChatListModel> chatListModelList;
    private DatabaseReference databaseReferenceChats, databaseReferenceUsers;
    private FirebaseUser currentUser;

    public FindFriendAdaptertiga(Context context, List<ChatListModel> chatListModelList) {
        this.context = context;
        this.chatListModelList = chatListModelList;
    }

    @NonNull
    @NotNull
    @Override
    public FindFriendAdaptertiga.ChatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FindFriendAdaptertiga.ChatViewHolder holder, int position) {
        ChatListModel chatModel = chatListModelList.get(position);

        holder.tvNamaChat.setText(chatModel.getNama_lengkap());
        holder.tvStatus.setText(chatModel.getInstitusi());

        holder.clChatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, ChatActivity2.class);
                intent.putExtra(Extras.USER_KEY, chatModel.getUserid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatListModelList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        private CardView cvChat;
        private ConstraintLayout clChatList;
        private TextView tvNamaChat, tvStatus, tvTime, tvUnreadCount;


        public ChatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            cvChat = itemView.findViewById(R.id.cvChat);
            tvNamaChat = itemView.findViewById(R.id.tvNamaChat);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvUnreadCount = itemView.findViewById(R.id.tvUnreadCount);
            clChatList = itemView.findViewById(R.id.clChatList);
        }
    }
}

