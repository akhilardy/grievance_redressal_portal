package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText name,email,mobNo,hNo,village,mandal,dist,pincode,setpassword,confirmPassword;
    Button signUp;
    FirebaseAuth fauth;
    RadioButton genF,genM;
    //String
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fauth= FirebaseAuth.getInstance();
        name=(EditText)findViewById(R.id.etUName);
        email=(EditText)findViewById(R.id.etEmailId);
        mobNo=(EditText)findViewById(R.id.etPhoneNo);
        hNo=(EditText)findViewById(R.id.etHno);
        village=(EditText)findViewById(R.id.etVillage);
        mandal=(EditText)findViewById(R.id.etMandal);
        dist=(EditText)findViewById(R.id.etDistrict);
        pincode=(EditText)findViewById(R.id.etPincode);
        genF=(RadioButton)findViewById(R.id.genderFemale);
        genM=(RadioButton)findViewById(R.id.genderMale);
        setpassword=(EditText)findViewById(R.id.etSetPassword);
        confirmPassword=(EditText)findViewById(R.id.etConfirmPassword);
        signUp=(Button)findViewById(R.id.btnSignUp);

        user=new User();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((name.getText().toString().trim()).equals("") || (email.getText().toString().trim()).equals("") ||
                        Integer.parseInt(mobNo.getText().toString())==32
                        ||(village.getText().toString().trim()).equals("")||(mandal.getText().toString().trim()).equals("")
                        ||(dist.getText().toString().trim()).equals("") ||(pincode.getText().toString().trim()).equals("")){
                    AlertDialog.Builder builder=new AlertDialog.Builder(SignUp.this);
                    builder.setTitle("Error occured");
                    builder.setMessage("No field should be empty");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert=builder.create();
                    alert.show();

                }

                //database storing

                if((setpassword.getText().toString().trim()).equals(confirmPassword.getText().toString().trim())){

                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference ref=db.getReference().child("Users");
                    String[] split=email.getText().toString().trim().split("@");
                    String childUsername=split[0];
                    user.setName(name.getText().toString().trim());
                    user.setEmail(email.getText().toString().trim());
                    user.setMobNo(Integer.parseInt(mobNo.getText().toString()));
                    user.setHno(hNo.getText().toString().trim());
                    user.setVillage(village.getText().toString().trim());
                    user.setMandal(mandal.getText().toString().trim());
                    user.setDistrict(dist.getText().toString().trim());
                    user.setPincode(pincode.getText().toString().trim());
                    if(genF.isChecked()){
                        user.setGender("Female");
                    }
                    else if(genM.isChecked()){
                        user.setGender("Male");
                    }
                    user.setPassword(setpassword.getText().toString());
                    ref.child(childUsername).setValue(user);

                }
                createUser(email.getText().toString().trim(),setpassword.getText().toString().trim());


            }
        });


    }
    public void createUser(String email,String password){
        fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "signup successfull", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(SignUp.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"sign up unsuccessfull",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
