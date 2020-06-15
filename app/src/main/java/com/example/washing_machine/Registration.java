package com.example.washing_machine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {

    private EditText name;
    private EditText ldap;
    private EditText wing;
    private EditText roomno;
    private EditText phone;
    private EditText password;
    private Button register;
    private TextView login;

    DatabaseReference reference;
    private static final String TAG= "Reg:";

    String Name,Ldap,Wing,Room,Phone,Password;
    FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupid();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = name.getText().toString();
                Ldap = ldap.getText().toString();
                Phone = phone.getText().toString();
                Wing = wing.getText().toString();
                Room = roomno.getText().toString();
                Password = password.getText().toString();

                if (Name.isEmpty() || Ldap.isEmpty() || Phone.isEmpty() || Wing.isEmpty() || Room.isEmpty() || Password.isEmpty())
                    Toast.makeText(Registration.this, "Please fill in all the details", Toast.LENGTH_SHORT);
                else {

                    System.out.println(Ldap);
                    System.out.println(Password);
                    mauth=FirebaseAuth.getInstance();

                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    reference=database.getReference("Users");

                    mauth.createUserWithEmailAndPassword(Ldap,Password)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull final Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mauth.getCurrentUser();
                                        DatabaseReference users = reference.child(Phone);
                                        ValueEventListener eventListener = new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (!dataSnapshot.exists()) {
                                                    Users users = new Users(Name, Ldap, Wing, Room, Phone);
                                                    reference.child(Phone).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                            Intent interestIntent = new Intent(Registration.this, App_Main_Activity.class);
                                                            interestIntent.putExtra("wing", Wing);
                                                            startActivity(interestIntent);


                                                        }


                                                    });
                                                } else {
                                                    Toast.makeText(Registration.this, "Ldap Id already Registered", Toast.LENGTH_SHORT).show();
                                                }
                                            }


                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        };
                                        users.addListenerForSingleValueEvent(eventListener);


                                    }
                                    else{
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(Registration.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,MainActivity.class);
                startActivity(intent);
            }
        });








    }

    private void signIn() {


    }






    private void setupid() {

        name=findViewById(R.id.name);
        ldap=findViewById(R.id.ldapid);
        wing=findViewById(R.id.wing);
        roomno=findViewById(R.id.roomno);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.pass);
        register=findViewById(R.id.register);
        login=findViewById(R.id.exist);



    }
}
