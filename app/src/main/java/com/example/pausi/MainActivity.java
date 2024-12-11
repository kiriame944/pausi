package com.example.pausi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura el DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Configura el botón de menú
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configura la navegación del menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                showFragment(new HomeFragment());
            } else if (id == R.id.nav_register) {
                showFragment(new RegisterFragment());
            } else if (id == R.id.nav_login) {
                showFragment(new LoginFragment());
            } else if (id == R.id.nav_users) {
                showFragment(new UserManagementFragment());
            } else if (id == R.id.nav_select_pet) {
                showFragment(new SelectPetFragment());
            } else if (id == R.id.nav_tips) {
                showFragment(new TipsFragment());
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        // Muestra el fragmento inicial al iniciar la app
        if (savedInstanceState == null) {
            showFragment(new HomeFragment());
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
