package com.example.rahul.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    String username;
    String password;
    Button b1;
    Button b2;
    EditText ed1;
    EditText ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        ed1=(EditText)findViewById(R.id.username);
        ed2=(EditText)findViewById(R.id.password);



        b1=(Button)findViewById(R.id.signin);
        b2=(Button)findViewById(R.id.signup);


        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        final Realm realm = Realm.getInstance(realmConfig);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username= ed1.getText().toString();
                password=ed2.getText().toString();

                Log.d("user is : ",username+" "+password);

                realm.beginTransaction();
                RealmQuery<users> query = realm.where(users.class);
                RealmResults<users> u = query.findAll();
                realm.commitTransaction();
                for (users user : u){
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        Log.d("user",user.getUsername()+" "+user.getPassword());
                        String name =user.getName();
                        String place=user.getPlace();
                        Intent i = new Intent(MainActivity.this, welcome.class);
                        i.putExtra("uname",name);
                        i.putExtra("uplace",place);
                        startActivity(i);
                        finish();
                    }else
                        Log.d("user not match",user.getUsername()+" "+user.getPassword());


                }

            }


        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });


    }


}
