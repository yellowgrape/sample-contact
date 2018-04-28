package io.textory.sample.contact;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // functional.contact.data.all(this,functional.log::e);
        functional.contact.all(this, ContactsContract.Contacts.CONTENT_URI, null, null, functional.log::e);
    }
}
