package com.lpikitb.lpikitbmobile.Activity.Chat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lpikitb.lpikitbmobile.Model.ChatListModel;
import com.lpikitb.lpikitbmobile.Model.User;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private RecyclerView rvChatList;
    private ChatListAdapter chatListAdapter;
    private List<ChatListModel> chatListModelList;

    private DatabaseReference databaseReferenceChats, databaseReferenceUsers;
    private ChildEventListener childEventListener;
    private Query query;

    public ChatFragment(){

    }

    LinearLayoutManager linearLayoutManager;
    FirebaseUser currentUser;
    TextView tvLoginNotice, tvStatus;
    Button btnLogin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvChatList = view.findViewById(R.id.rvListDaftarChat);
        chatListModelList =  new ArrayList<>();
        chatListAdapter = new ChatListAdapter(getActivity(), chatListModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvChatList.setAdapter(chatListAdapter);
        tvStatus = view.findViewById(R.id.tvStatus);

        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReferenceChats = FirebaseDatabase.getInstance().getReference().child(NodeNames.CHATS).child(currentUser.getUid());

        query = databaseReferenceChats.orderByChild(NodeNames.TIME_STAMP);

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable String s) {
                   updatelist(snapshot, true, snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                updatelist(snapshot, false, snapshot.getKey());

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addChildEventListener(childEventListener);

    }

    private void updatelist(DataSnapshot snapshot, boolean isNew, String userid){
        String unreadcount, lastmessagge, lastmessagetime;
        lastmessagge = "";
        lastmessagetime = "";
        unreadcount = "";

        databaseReferenceUsers.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String nama_lengkap= snapshot.child(NodeNames.NAMALENGKAP).getValue()!=null?
                    snapshot.child(NodeNames.NAMALENGKAP).getValue().toString():"";
                String institusi = snapshot.child(NodeNames.INSTITUSI).getValue().toString();

               ChatListModel chatListModel = new ChatListModel(userid, nama_lengkap, unreadcount, lastmessagetime, lastmessagge, institusi);

               chatListModelList.add(chatListModel);
               chatListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(getActivity(),  getActivity().getString(R.string.failed_to_fetch_chat_list, error.getMessage())
                        , Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void OnDestroy(){
        super.onDestroy();
        query.removeEventListener(childEventListener);
    }
}
