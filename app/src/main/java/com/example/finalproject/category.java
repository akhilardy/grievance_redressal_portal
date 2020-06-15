package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class category extends AppCompatActivity {
    ImageButton roads,schools,hospital,water,sanitary,tele,power,others;
    int pc;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.idtitle:
                Intent i=new Intent(category.this,profile.class);
                startActivity(i);
                //Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();
                return true;

            case R.id.idsub:
                Toast.makeText(getApplicationContext(), "submissions", Toast.LENGTH_LONG).show();
                return true;
            case R.id.idlogout:
                Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_LONG).show();
                return true;
            default:    return super.onOptionsItemSelected(item);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.choose_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        roads=(ImageButton)findViewById(R.id.imageButton14);
        schools=(ImageButton)findViewById(R.id.imageButton15);
        hospital=(ImageButton)findViewById(R.id.imageButton16);
        water=(ImageButton)findViewById(R.id.imageButton18);
        sanitary=(ImageButton)findViewById(R.id.imageButton19);
        tele=(ImageButton)findViewById(R.id.imageButton20);
        power=(ImageButton)findViewById(R.id.imageButton21);
        others=(ImageButton)findViewById(R.id.imageButton22);



        roads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","roads");

                startActivity(i);
            }
        });
        schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","schools");

                startActivity(i);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","hospital");

                startActivity(i);
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","water");

                startActivity(i);
            }
        });
        sanitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","sanitary");

                startActivity(i);
            }
        });
        tele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","tele_communications");

                startActivity(i);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","power");

                startActivity(i);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(category.this,categoryRoom.class);
                i.putExtra("category_name","others");

                startActivity(i);
            }
        });

    }
}
