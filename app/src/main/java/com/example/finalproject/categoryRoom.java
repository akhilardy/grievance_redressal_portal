package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class categoryRoom extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    FloatingActionButton fab;
    EditText mesg;
    TextView tv;
    FirebaseAuth fauth;
    problem pm;
    String location,mysubid;
    long childrenCount=0;
    messageItem mitem;
    mysubObj mso;
    long maxid=0;
    String vinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_room);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        mesg=(EditText) findViewById(R.id.input);

        mRecyclerView=(RecyclerView)findViewById(R.id.extra);
        Intent i=getIntent();
        final String cat=i.getStringExtra("category_name");

        //data flooding

        fauth=FirebaseAuth.getInstance();

        /*
        FirebaseDatabase datab=FirebaseDatabase.getInstance();
        final DatabaseReference refer=datab.getReference().child("Categories").child(cat);
        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(dataSnapshot child:parent)
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        */
        //Toast.makeText(getApplicationContext(),String.valueOf(childrenCount),Toast.LENGTH_LONG).show();

        //data flooding
        final ArrayList<messageItem> totalList=new ArrayList<messageItem>();
        FirebaseDatabase datab=FirebaseDatabase.getInstance();
        final DatabaseReference refer=datab.getReference().child("Categories").child(cat);
        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<messageItem> totalList=new ArrayList<messageItem>();
                for(DataSnapshot data:dataSnapshot.getChildren()){

                    String loc=data.child("location").getValue().toString();
                    String prob=data.child("problem").getValue().toString();
                    String pcode=data.child("problemCode").getValue().toString();
                    String sta=data.child("status").getValue().toString();
                    String time=data.child("time").getValue().toString();
                    String un=data.child("username").getValue().toString();
                    messageItem mi=new messageItem(un,loc,prob,sta,pcode,time);
                    totalList.add(mi);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView=findViewById(R.id.extra);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(totalList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        //iterating through database to store the data in arraylist



        //final ArrayList<messageItem> totalList=new ArrayList<messageItem>();
        /*


        for(int j=1;j<=childrenCount;j++){
            String var=cat+j;
            FirebaseDatabase catDB=FirebaseDatabase.getInstance();
            DatabaseReference catRefer=catDB.getReference().child("Categories").child(cat).child(var);
            catRefer.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                problem pr=dataSnapshot.getValue(problem.class);
                    Iterator<DataSnapshot> items=dataSnapshot.getChildren().iterator();
                                mitem=new messageItem(pr.username,pr.location,pr.problem,pr.status,pr.problemCode,pr.time);
                                totalList.add(mitem);
                    Toast.makeText(getApplicationContext(), pr.username+" "+pr.location+" "+pr.problem+" "+pr.status+" "+pr.problemCode+" "+pr.time, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        mRecyclerView=findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(totalList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        */






        FirebaseDatabase data=FirebaseDatabase.getInstance();
        final DatabaseReference reff=data.getReference().child("Categories").child(cat);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currEmail=fauth.getCurrentUser().getEmail().trim();
                String[] splits=currEmail.split("@");
                final String userKey=splits[0];
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference ref=db.getReference().child("Users").child(userKey);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //to find the username of the current user to store it in problem
                        try{
                            if(dataSnapshot.getValue()!=null){
                                try{
                                    User usr=dataSnapshot.getValue(User.class);
                                    String dis=usr.district;
                                    String mand=usr.mandal;
                                    String nme=usr.name;
                                    String pin=(usr.pincode);
                                    String vill=usr.village;

                                    pm=new problem();
                                    pm.setLocation("Village:"+vill+"    "+"mandal:"+mand+"   "+
                                            "District:"+dis+"    "+"Pincode:"+pin);
                                    pm.setProblem(mesg.getText().toString());
                                    pm.setProblemCode(cat+String.valueOf(maxid+1));
                                    pm.setStatus("resolved");
                                    pm.setTime((DateFormat.format("dd-MM-yyyy (HH:mm:ss)",new Date().getTime())).toString());
                                    pm.setUsername(nme);
                                    reff.child(cat+String.valueOf(maxid+1)).setValue(pm);
                                    location="Village:"+vill+"    "+"mandal:"+mand+"   "+
                                            "District:"+dis+"    "+"Pincode:"+pin;
                                    mysubid=cat+String.valueOf(maxid+1);

                                    //to find the username of the current user to store it in problem

                                    //at the same time to store it in the my submissions of user
                                    FirebaseDatabase db1=FirebaseDatabase.getInstance();
                                    final DatabaseReference ref1=db1.getReference().child("My_Submissions");
                                    ref1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            try{
                                                if(dataSnapshot.getValue()!=null){

                                                    try{
                                                        mso=new mysubObj();
                                                        mso.setProblemCode(mysubid);
                                                        mso.setTime((DateFormat.format("dd-MM-yyyy (HH:mm:ss)",new Date().getTime())).toString());
                                                        mso.setLocation(location);
                                                        mso.setCategory(cat);
                                                        mso.setProblem(mesg.getText().toString().trim());
                                                        mso.setStatus("resolved");
                                                        ref1.child(userKey).child(mysubid).setValue(mso);
                                                    }
                                                    catch (Exception e){

                                                    }
                                                }
                                            }
                                            catch (Exception e){

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                                catch (Exception e){

                                }
                            }
                        }
                        catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

}
