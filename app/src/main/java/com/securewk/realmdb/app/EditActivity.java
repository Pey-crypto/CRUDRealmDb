package com.securewk.realmdb.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.securewk.realmdb.MainActivity;
import com.securewk.realmdb.R;
import com.securewk.realmdb.entity.Books;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditActivity extends AppCompatActivity {

    private EditText name,author,price,description;
    private Realm realm;
    private Bundle bundle;
    private int position;
    private Books books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        bundle = getIntent().getExtras();
        if(bundle != null)
            position = bundle.getInt("position");

        name = findViewById(R.id.edit_book_name_edit_text);
        author = findViewById(R.id.edit_author_name_edit_text);
        price = findViewById(R.id.edit_book_price_edit_text);
        description = findViewById(R.id.edit_book_description_edit_text);

        realm = Realm.getDefaultInstance();
        RealmResults<Books> results = realm.where(Books.class).findAll();
        books = results.get(position);
        setupView(books);
    }

    private void setupView(Books books) {
        name.setText(books.getBookName());
        author.setText(books.getAuthorName());
        price.setText(""+books.getBookPrice());
        description.setText(books.getBookDescription());


    }

    private void updateBooks(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                books.setBookPrice(Double.parseDouble(price.getText().toString().trim()));
                books.setBookDescription(description.getText().toString().trim());
                books.setBookName(name.getText().toString().trim());
                books.setAuthorName(author.getText().toString().trim());
                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });

    }

    public void editBooks(View view){
        updateBooks();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}