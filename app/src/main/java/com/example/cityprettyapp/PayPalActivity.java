package com.example.cityprettyapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PayPalActivity extends AppCompatActivity {
    private Button payBtn;
    private Button addPedicureToCart;
    private Button addManicureToCart;
    private Button addManHaircutToCart;
    private Button addWomanHaircutToCart;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);
        payBtn = findViewById(R.id.PayBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        firebaseAuth = FirebaseAuth.getInstance();
        addPedicureToCart = findViewById(R.id.addPedicureToCart);
        addManicureToCart = findViewById(R.id.addManicureToCart);
        addManHaircutToCart = findViewById(R.id.addManHaircutToCart);
        addWomanHaircutToCart = findViewById(R.id.addWomanHaircutToCart);



        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_shopping_cart_black_24dp);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                myToolBar,
                R.string.Open,
                R.string.Close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                drawerLayout.closeDrawers();
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        addPedicureToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayPalActivity.this, AddPedicureToCartActivity.class));
                Toast.makeText(PayPalActivity.this, "Pedicure added to cart.", Toast.LENGTH_SHORT).show();
            }
        });

        addManicureToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayPalActivity.this, AddManicureToCartActivity.class));
                Toast.makeText(PayPalActivity.this, "Manicure added to cart.", Toast.LENGTH_SHORT).show();
            }
        });

        addManHaircutToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayPalActivity.this, AddManHaircutToCartActivity.class));
                Toast.makeText(PayPalActivity.this, "Men's haircut added to cart.", Toast.LENGTH_SHORT).show();
            }
        });

        addWomanHaircutToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayPalActivity.this, AddWomanHaircutToCartActivity.class));
                Toast.makeText(PayPalActivity.this, "Woman's haircut added to cart.", Toast.LENGTH_SHORT).show();
            }
        });


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayPalActivity.this, MakePaymentActivity.class));
            }
        });

        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.services_page) {
                    startActivity(new Intent(getApplicationContext(), PayPalActivity.class));
                }

                if(id == R.id.beauticians_page) {
                    startActivity(new Intent(getApplicationContext(), BeauticiansList.class));
                }

                if(id == R.id.settings_page) {
                    startActivity(new Intent(getApplicationContext(), ChangeSettings.class));
                }

                if(id == R.id.profile_page) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }

                if(id == R.id.appointments_page) {
                    startActivity(new Intent(getApplicationContext(), BeauticianSide.class));
                }

                if(id == R.id.about_city_pretty) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.citypretty.com"));
                    startActivity(intent);
                }

                if(id == R.id.logout_button) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
