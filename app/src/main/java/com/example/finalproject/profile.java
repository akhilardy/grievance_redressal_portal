package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    TextView name,emailId,phoneNo,gender,address1,address2,address3,address4,pincode;
    FirebaseAuth fauth;
    FloatingActionButton edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.nameVal);

        emailId=(TextView)findViewById(R.id.emailIdVal);
        phoneNo=(TextView)findViewById(R.id.phoneNoVal);
        gender=(TextView)findViewById(R.id.genderVal);
        address1=(TextView)findViewById(R.id.addVal1);
        address2=(TextView)findViewById(R.id.addVal2);
        address3=(TextView)findViewById(R.id.addVal3);
        address4=(TextView)findViewById(R.id.addVal4);
        pincode=(TextView)findViewById(R.id.pincodeVal);
        fauth=FirebaseAuth.getInstance();
        //Toast.makeText(getApplicationContext(), fauth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
        String currEmail=fauth.getCurrentUser().getEmail().trim();
        String[] splits=currEmail.split("@");
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference().child("Users").child(splits[0]);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    if(dataSnapshot.getValue()!=null){
                        try{
                            User usr=dataSnapshot.getValue(User.class);
                            Log.d("tag",usr.gender+"  "+usr.hno+"  "+usr.village+"  "+
                                    usr.mandal+"  "+usr.district+"  "+usr.pincode);
                            address4.setText(usr.district);
                            emailId.setText(usr.email);
                            gender.setText(usr.gender);
                            address1.setText(usr.hno);
                            address3.setText(usr.mandal);
                            phoneNo.setText(String.valueOf(usr.mobNo));
                            name.setText(usr.name);
                            pincode.setText(usr.pincode);
                            address2.setText(usr.village);
                        }
                        catch(Exception e){

                        }
                    }
                }
                catch(Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "nu naak pankiraavu", Toast.LENGTH_LONG).show();
            }
        });

    }
}
