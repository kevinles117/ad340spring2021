package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.helloworld.models.MatchesModel;
import com.example.helloworld.viewmodels.MatchesViewModel;
import com.example.helloworld.viewmodels.SettingsViewModel;
import com.google.android.material.navigation.NavigationView;

public class SignUpActivity extends AppCompatActivity implements MatchesFragment.OnListFragmentInteractionListener{
    String name;
    String occupation;
    int age;
    String desc;
    private FragmentManager manager;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private MatchesViewModel matchesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b.containsKey("Name") && b.containsKey("Description") && b.containsKey("Occupation") && b.containsKey("Age")) {
            name = b.getString("Name");
            occupation = b.getString("Occupation");
            age = b.getInt("Age");
            desc = b.getString("Description");
        }

        drawer = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.topAppBar);
        navigationView = findViewById(R.id.navigationView);

        navigationView.bringToFront();
        toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_profile);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        ProfileFragment fragment = new ProfileFragment();
        Profile profile = new Profile(name, age, occupation, desc);
        fragment.setProfile(profile);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.signUpLinearLayout, fragment, "profile");
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = new ProfileFragment();
        Profile profile = new Profile(name, age, occupation, desc);
        ((ProfileFragment) fragment).setProfile(profile);
        manager = getSupportFragmentManager();
        switch(menuItem.getItemId()) {
            case R.id.nav_profile:
                break;
            case R.id.nav_matches:
                fragment = new MatchesFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }
        manager.beginTransaction()
                .replace(R.id.signUpLinearLayout, fragment)
                .commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(MatchesModel match) {
        match.liked = true;
        this.matchesViewModel.updateMatches(match);
    }

    public static class Profile {
        String name;
        int age;
        String occupation;
        String desc;

        Profile(String name, int age, String occupation, String desc) {
            this.name = name;
            this.age = age;
            this.occupation = occupation;
            this.desc = desc;
        }
    }
}