package com.example.washing_machine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    DatabaseReference user;

    private EditText rollno;
    private EditText pass;
    private Button logp;
    private TextView signUp;
    private TextView contact;
    private FirebaseAuth mAuth;
    private String iitbroll;
    private String passw;
    private Button meow;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        mAuth = FirebaseAuth.getInstance();

       /* meow = findViewById(R.id.btn01);
        meow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse("https://gymkhana.iitb.ac.in/profiles/oauth/authorize/?client_id=ZQMrW9bM3pwVjaypNX1AV9oHjXt9Y842QVgNcT6F&response_type=code&scope=basic&redirect_uri=https://payalchoudhary2410.github.io/washing_machine/&state=some_state"));
                startActivity(intent);
                /*CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(MainActivity.this.getResources()
                                .getColor(R.color.colorPrimary))
                        .setShowTitle(true)
                        .build();
               */ // customTabsIntent.launchUrl(MainActivity.this, Uri.parse("https://gymkhana.iitb.ac.in/profiles/oauth/authorize/?client_id=ZQMrW9bM3pwVjaypNX1AV9oHjXt9Y842QVgNcT6F&response_type=code&scope=basic&redirect_uri=https://payalchoudhary2410.github.io/Washing_Machine/&state=some_state"));


    }










   

   @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent i= new Intent(MainActivity.this,App_Main_Activity.class);
            startActivity(i);
            finish();
        }
        
        logp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iitbroll=rollno.getText().toString();
                passw=pass.getText().toString();
                if(iitbroll.isEmpty())
                    rollno.setError("Field cannot be empty");
                else if(passw.isEmpty())
                    pass.setError("Field cannot be empty");
                else
                signIn();
               
                
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(MainActivity.this,Registration.class);
                startActivity(in);

            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ContactUs.class);
                startActivity(in);


            }
        });
        /*
            
               

        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri=getIntent().getData();
        if(uri!=null){
            Toast.makeText(this,"Successful",Toast.LENGTH_SHORT);
        }
    }

    public void setup(){
        rollno=(EditText)findViewById(R.id.roll);
        pass=(EditText)findViewById(R.id.password);
        logp=(Button)findViewById(R.id.login);
        signUp=(TextView)findViewById(R.id.signup);
        contact=(TextView)findViewById(R.id.contactUs);
    }

    private void signIn() {
        mAuth.signInWithEmailAndPassword(iitbroll,passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login Successful",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(MainActivity.this,App_Main_Activity.class);
                            startActivity(intent);
                        }
                        else{
                            String ptext=iitbroll+" Not registered";
                            Toast.makeText(MainActivity.this, ptext,
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        
        


    }

}
