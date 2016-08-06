package com.example.rahul.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final EditText ed1;
        final EditText ed2;
        final EditText ed3;
        final EditText ed4;
        Button b1;
        ed1=(EditText)findViewById(R.id.name);
        ed2=(EditText)findViewById(R.id.place);
        ed3=(EditText)findViewById(R.id.uname);
        ed4=(EditText)findViewById(R.id.pwd);
        b1=(Button)findViewById(R.id.register);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        final Realm realm = Realm.getInstance(realmConfig);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed1.length()!=0&&ed2.length()!=0&&ed3.length()!=0&&ed4.length()!=0) {
                    realm.beginTransaction();
                    users user = realm.createObject(users.class);
                    //RealmQuery<users> query = realm.where(users.class);
                    //realm.createObjectFromJson(users.class, "{ Name: Place: Username: Password }");
                    user.setName(ed1.getText().toString());
                    user.setPlace(ed2.getText().toString());
                    user.setUsername(ed3.getText().toString());
                    user.setPassword(ed4.getText().toString());
                    realm.commitTransaction();
                    Intent intent=new Intent(signup.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Registered, Thank you!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    };

}
