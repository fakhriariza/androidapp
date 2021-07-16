package com.lpikitb.lpikitbmobile.Activity.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.lpikitb.lpikitbmobile.Model.FindFriendModel;
import com.lpikitb.lpikitbmobile.Network.Constants;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FindFriendAdapter extends RecyclerView.Adapter<FindFriendAdapter.FindFriendViewHolder> {
    private Context context;
    private List<FindFriendModel> findFriendModelList;
    private DatabaseReference databaseReferenceChats, databaseReferenceUsers;
    private FirebaseUser currentUser;

    public FindFriendAdapter(Context context, List<FindFriendModel> findFriendModelList) {
        this.context = context;
        this.findFriendModelList = findFriendModelList;
    }

    @NonNull
    @NotNull
    @Override
    public FindFriendAdapter.FindFriendViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kontak_item, parent, false);
        return new FindFriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FindFriendAdapter.FindFriendViewHolder holder, int position) {
        FindFriendModel friendModel = findFriendModelList.get(position);

        holder.tvNamaChat.setText(friendModel.getNama_lengkap());

        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);
        databaseReferenceChats = FirebaseDatabase.getInstance().getReference().child(NodeNames.CHATS);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        holder.buttonchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userid = friendModel.getUserid();
                databaseReferenceChats.child(currentUser.getUid()).child(userid)
                        .child(NodeNames.TIME_STAMP).setValue(ServerValue.TIMESTAMP).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            databaseReferenceChats.child(userid).child(currentUser.getUid())
                                    .child(NodeNames.TIME_STAMP).setValue(ServerValue.TIMESTAMP).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        databaseReferenceUsers.child(currentUser.getUid()).child(userid)
                                                .child(NodeNames.NAMALENGKAP).setValue(Constants.REQUEST_STATUS_ACCEPTED).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    databaseReferenceUsers.child(userid).child(currentUser.getUid())
                                                            .child(NodeNames.REQUEST_TYPE).setValue(Constants.REQUEST_STATUS_ACCEPTED).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                            if (task.isSuccessful()){
                                                                holder.buttonchat.setVisibility(View.GONE);
                                                                holder.tvNamaChat.setVisibility(View.GONE);
                                                                holder.ivGambarChat.setVisibility(View.GONE);
                                                                holder.cvChat.setVisibility(View.GONE);

                                                            }
                                                            else {
                                                                Toast.makeText(context, context.getString(R.string.failed_to_accept_request, task.getException()), Toast.LENGTH_SHORT).show();
                                                            }

                                                            }
                                                    });
                                                }
                                                else {
                                                    Toast.makeText(context, context.getString(R.string.failed_to_accept_request, task.getException()), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(context, context.getString(R.string.failed_to_accept_request, task.getException()), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                        else {
                            Toast.makeText(context, context.getString(R.string.failed_to_accept_request, task.getException()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return findFriendModelList.size();
    }

    public class FindFriendViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaChat;
        private Button buttonchat;
        private ImageView ivGambarChat;
        private CardView cvChat;

        public FindFriendViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNamaChat = itemView.findViewById(R.id.tvNamaChat);
            buttonchat = itemView.findViewById(R.id.buttonchat);
            ivGambarChat = itemView.findViewById(R.id.ivGambarChat);
            cvChat = itemView.findViewById(R.id.cvChat);
        }
    }
}

