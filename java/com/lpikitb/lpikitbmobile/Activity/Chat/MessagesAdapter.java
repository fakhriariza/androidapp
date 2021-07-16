package com.lpikitb.lpikitbmobile.Activity.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.lpikitb.lpikitbmobile.Model.MessageModel;
import com.lpikitb.lpikitbmobile.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private Context context;
    private List<MessageModel> messagesList;
    private FirebaseAuth firebaseAuth;


    public MessagesAdapter(Context context, List<MessageModel> messagesList) {
        this.context = context;
        this.messagesList = messagesList;
    }

    @NonNull
    @NotNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageViewHolder holder, int position) {
        MessageModel message = messagesList.get(position);
        firebaseAuth = FirebaseAuth.getInstance();
        String currentUserId= firebaseAuth.getCurrentUser().getUid();

        String fromUserId = message.getMessageFrom();

        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dateTime = sfd.format(new Date(message.getMessageTime()));
        String [] splitString = dateTime.split("");
        String messageTime = splitString [1];

        if(fromUserId.equals(currentUserId)){
            holder.llsent.setVisibility(View.VISIBLE);
            holder.llreceived.setVisibility(View.GONE);
            holder.tvSentMessage.setText(message.getMessage());
            holder.tvSentMessageTime.setText(messageTime);
        }
        else {
            holder.llsent.setVisibility(View.GONE);
            holder.llreceived.setVisibility(View.VISIBLE);
            holder.tvReceivedMessage.setText(message.getMessage());
            holder.tvReceivedMessageTime.setText(messageTime);
        }

    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llsent, llreceived;
        private TextView tvSentMessage, tvSentMessageTime, tvReceivedMessage, tvReceivedMessageTime;
        private ConstraintLayout clMessage;

        public MessageViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            llsent = itemView.findViewById(R.id.llSent);
            llreceived = itemView.findViewById(R.id.llReceived);
            tvSentMessage = itemView.findViewById(R.id.tvSentMessage);
            tvSentMessageTime = itemView.findViewById(R.id.tvSentMessageTime);
            tvReceivedMessage = itemView.findViewById(R.id.tvReceivedMessage);
            tvReceivedMessageTime = itemView.findViewById(R.id.tvSentMessageTime);
            clMessage = itemView.findViewById(R.id.clMessage);
        }
    }
}
