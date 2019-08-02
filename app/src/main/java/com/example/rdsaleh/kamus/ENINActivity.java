package com.example.rdsaleh.kamus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.rdsaleh.kamus.Adapter.AdapterENIN;
import com.example.rdsaleh.kamus.Database.KamusHelper;
import com.example.rdsaleh.kamus.Model.KamusModel;

import java.util.ArrayList;

public class ENINActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private NavigationView nv;

    private RecyclerView recyclerView;
    private AdapterENIN adapterENIN;
    private KamusHelper kamusHelper;

    private EditText et_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.en_to_in);
        setContentView(R.layout.activity_enin);

        mDrawerLayout = findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv_enin);
        nv.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rv_enin);
        et_word      = findViewById(R.id.et_word);

        et_word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    showAllData();
                }else{
                    String word = et_word.getText().toString();
                    getDataByWord(word);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        showAllData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)){ return true; }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        menuItem.setChecked(true);
        menuItem.setCheckable(true);

        if(id == R.id.navigation_intoen){
            Intent i = new Intent(ENINActivity.this,INENActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        if(id == R.id.navigation_entoin){
            Intent i = new Intent(ENINActivity.this,ENINActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        return true;
    }

    public void showAllData(){
        kamusHelper = new KamusHelper(this);
        adapterENIN = new AdapterENIN(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapterENIN);

        kamusHelper.open();
        ArrayList<KamusModel> kamusModels = kamusHelper.getAllDataEN();
        kamusHelper.close();

        adapterENIN.addItem(kamusModels);
    }

    public void getDataByWord(String word){
        kamusHelper = new KamusHelper(this);
        adapterENIN = new AdapterENIN(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapterENIN);

        kamusHelper.open();
        ArrayList<KamusModel> kamusModels = kamusHelper.getDataENByWord(word);
        kamusHelper.close();

        adapterENIN.addItem(kamusModels);
    }
}
