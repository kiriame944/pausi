package com.example.pausi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Configurar ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configuración del NavigationView
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    showFragment(new HomeFragment());
                } else if (id == R.id.nav_register) {
                    showFragment(new RegisterFragment());
                } else if (id == R.id.nav_login) {
                    showFragment(new LoginFragment());
                } else if (id == R.id.nav_select_pet) {
                    showFragment(new SelectPetFragment());
                } else if (id == R.id.nav_tips) {
                    showFragment(new TipsFragment());
                } else if (id == R.id.nav_users) {
                    showFragment(new UserManagementFragment());
                } else if (id == R.id.nav_logout) {
                    // Redirige al fragmento de inicio de sesión
                    showFragment(new LoginFragment());

                    // Mostrar mensaje de confirmación de cierre de sesión
                    Toast.makeText(MainActivity.this, "Sesión cerrada. Vuelve pronto.", Toast.LENGTH_SHORT).show();
                }


                drawerLayout.closeDrawers();
                return true;
            });


        }

        // Mostrar el fragmento inicial por defecto
        if (savedInstanceState == null) {
            showFragment(new HomeFragment());
            if (navigationView != null) {
                navigationView.setCheckedItem(R.id.nav_home);
            }
        }
    }

    // Método para cambiar entre fragmentos
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(navigationView);
        return super.onSupportNavigateUp();
    }
}
