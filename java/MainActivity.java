package com.example.flames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText e1,g1;
    String boyname,girlname;
    char relationIs;
    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    boolean ischecked = false;




    @SuppressLint({"SetTextI18n", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        e1=findViewById(R.id.e1);
        g1=findViewById(R.id.e2);
        Button b1= findViewById(R.id.button);
        TextView t1= findViewById(R.id.t1);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        navigationView=findViewById(R.id.nav_view);


        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(item -> {
            if(item.getItemId()==R.id.set){
                Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId()==R.id.acc){
                Uri uri= Uri.parse("https://instagram.com/_decent_fel_low?utm_source=qr&igshid=MzNINGNkZWQ4Mg%3D%3D");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            } else if (item.getItemId()==R.id.logout) {
                Uri uri=Uri.parse("https://www.google.com/");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
            else if(item.getItemId()==R.id.share) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("Text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Check out this cool Application");
                intent.putExtra(Intent.EXTRA_TEXT,"Hi Vro !");
                startActivity(Intent.createChooser(intent,"share via"));

            }
            return false;
        });






        b1.setOnClickListener(v -> {
            ischecked = CheckAllFields(e1, g1);
            if (ischecked){
            boyname = e1.getText().toString().toLowerCase();
            girlname = g1.getText().toString().toLowerCase();

            StringBuilder sb1 = new StringBuilder(boyname);// converting to string builder
            StringBuilder sb2 = new StringBuilder(girlname);

            int m = sb1.length();
            int n = sb2.length();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (sb1.charAt(i) == sb2.charAt(j)) {
                        sb1.replace(i, i + 1, "0"); // replacing matching characters into "0"
                        sb2.replace(j, j + 1, "0");
                    }
                }
            }
            int x1 = 0;
            int y1 = 0;
            String s1;
            String s2;
            s1 = sb1.toString();
            s2 = sb2.toString();
            for (int i = 0; i < s1.length(); i++) { //length of string to remove 0 and find the length
                if (s1.charAt(i) != '0') {
                    x1 += 1;

                }
            }


            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) != '0') {

                    y1 += 1;

                }
            }


            int x = x1 + y1; // total length of remaining characters in both the strings
            String flames = "flames";
            StringBuilder sb3 = new StringBuilder(flames);


            while (sb3.length() != 1) {
                int y = x % sb3.length();
                String temp;

                if (y != 0) {
                    temp = sb3.substring(y) + sb3.substring(0, y - 1); // taking substring (counting purpose)

                } else {
                    temp = sb3.substring(0, sb3.length() - 1); // taking substring (counting purpose)

                }
                sb3 = new StringBuilder(temp);
                relationIs = sb3.charAt(0);


            }

            switch (relationIs) {
                case 'f':
                    t1.setText("Friends ");
                    break;
                case 'l':
                    t1.setText("Love ");
                    break;
                case 'a':
                    t1.setText("Affection");
                    break;
                case 'm':
                    t1.setText("Marriage");
                    break;
                case 'e':
                    t1.setText("Enemies");
                    break;
                case 's':
                    t1.setText("Sibling");
                    break;
            }
        }



        });
    }
    private boolean CheckAllFields(TextView ed1, TextView ed2) {
        if(ed1.length()==0){
            ed1.setError("this field is required");
            return false;
        }
        if(ed2.length()==0){
            ed2.setError("this field is required");
            return false;
        }

        return true;
    }
}