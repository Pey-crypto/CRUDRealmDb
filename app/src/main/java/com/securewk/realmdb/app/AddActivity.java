package com.securewk.realmdb.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.securewk.realmdb.R;
import com.securewk.realmdb.entity.Books;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class AddActivity extends AppCompatActivity {


    private EditText book_name,author_name,book_price,book_description;
    private Realm myRealm;
    private RealmAsyncTask realmTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        book_name =findViewById(R.id.book_name_edit_text);
        author_name =findViewById(R.id.author_name_edit_text);
        book_description =findViewById(R.id.book_description_edit_text);
        book_price =findViewById(R.id.book_price_edit_text);
        myRealm = Realm.getDefaultInstance();
    }

    private void insertRecords() {
        final String id = UUID.randomUUID().toString();
        final String author = author_name.getText().toString();
        final String name = book_name.getText().toString();
        final String price = book_price.getText().toString();
        final String description = book_description.getText().toString();


        if (name.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter Book name...", Toast.LENGTH_LONG).show();
            return;
        }
        if (author.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter Book name...", Toast.LENGTH_LONG).show();
            return;
        }
        if (price.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter Book name...", Toast.LENGTH_LONG).show();
            return;
        }
        if (description.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter Book name...", Toast.LENGTH_LONG).show();
            return;
        }
        realmTask = myRealm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Books books = realm.createObject(Books.class,id);
                        books.setBookName(name);
                        books.setAuthorName(author);
                        books.setBookPrice(Double.parseDouble(price));
                        books.setBookDescription(description);
                    }
                },

                new Realm.Transaction.OnSuccess(){
                    @Override
                    public void onSuccess(){
                        Toast.makeText(AddActivity.this,"Success",Toast.LENGTH_LONG).show();

                        book_name.setText("");
                        author_name.setText("");
                        book_price.setText("");
                        book_description.setText("");


                    }
                },
                new Realm.Transaction.OnError(){
                    @Override
                    public void onError(Throwable error){
                        Toast.makeText(AddActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }

                );
    }
    public void addBooks(View view){
        insertRecords();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(realmTask != null && realmTask.isCancelled()){
            realmTask.cancel();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        myRealm.close();
    }
}