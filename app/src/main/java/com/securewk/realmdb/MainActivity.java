package com.securewk.realmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.securewk.realmdb.app.AddActivity;
import com.securewk.realmdb.entity.Books;
import com.securewk.realmdb.ui.BookAdapter;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Realm realm;
    private BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm= Realm.getDefaultInstance();
        getAllBooks();

    }
    protected void  onResume(){
        super.onResume();
        if(bookAdapter!=null){
            bookAdapter.notifyDataSetChanged();
        }
    }

    private void getAllBooks(){
        RealmResults<Books> results = realm.where(Books.class).findAll();
        recyclerView  = findViewById(R.id.my_book_recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        bookAdapter = new BookAdapter(MainActivity.this,realm,results);
        recyclerView.setAdapter(bookAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.setting_menu:
                Toast.makeText(getApplicationContext(),"Setting Menu",Toast.LENGTH_SHORT).show();
                break;

            case R.id.insert_menu:
                startActivity(new Intent(MainActivity.this, AddActivity.class));
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}