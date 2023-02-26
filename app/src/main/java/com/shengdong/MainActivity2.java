package com.shengdong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar tb = (Toolbar)findViewById(R.id.toolbar);
        tb.setTitle("Home");
        tb.setLogo(R.drawable.ic_baseline_home);
        setSupportActionBar(tb);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int item_id = item.getItemId();

        if(item_id==R.id.profile){
            Toast.makeText(this, "This is profile item", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.contactos){
            Toast.makeText(this, "This is contactos item", Toast.LENGTH_SHORT).show();
        }else if(item_id==R.id.setting){
            Toast.makeText(this, "This is setting item", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.exit){
            mAuth.signOut();
            startActivity(new Intent(MainActivity2.this, LoginActivity.class));
            finish();
        }else if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // FirebaseRecyclerOptions<>
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    public void irHome(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        }

}