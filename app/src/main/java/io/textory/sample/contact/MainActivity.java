package io.textory.sample.contact;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import rebeccapurple.android.contact.Observer;
import rebeccapurple.android.contact.Raw;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        functional.contact.init(this, "io.textory", "local",new Observer());

        // insert(this, "hello", "000-0000-0000", "hello@textory.com", "dljfdlkajfd");
        functional.contact.update(this, 2194L, "hello7", "010-0000-0000", "hello2@textory.com", "dljfdlkajfd");
        // delete(this, 2195L);

        // functional.contact.data.all(this,functional.log::e);

        functional.contact.all(this, ContactsContract.Contacts.CONTENT_URI, null, null, functional.log::e);



        // functional.contact.insert(name, phone, email, address);
        // functional.contact.update(id, name, phone, email, address);

        /**
         *
         * functional.contact.insert(account,)
         * functional.contact.update(...)
         *
         *
         */
//
//        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
//
//        builder.withValue()
    }
}
