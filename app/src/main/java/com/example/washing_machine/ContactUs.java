package com.example.washing_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
    private ImageView whatsappLogo, gmailLogo,phoneLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        whatsappLogo=(ImageView)findViewById(R.id.whatsapp);
        whatsappLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent whatsappIntent= new Intent (Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+917280911163"));
                        if (whatsappIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(whatsappIntent);

                        }
                        else{
                            Toast.makeText(ContactUs.this, "No App support this function", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        gmailLogo=(ImageView)findViewById(R.id.gmail);
        gmailLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"choudharypayal2410@gmail.com"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Issue");
                        if (emailIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(emailIntent);
                        }
                        else {
                            Toast.makeText(ContactUs.this, "No App support this function", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        phoneLogo=(ImageView)findViewById(R.id.phone);
        phoneLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri number = Uri.parse("tel:7280911163");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        if (callIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(callIntent);
                        }
                        else{
                            Toast.makeText(ContactUs.this, "No App support this function", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );
    }
}
