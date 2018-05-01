package io.textory.sample.contact;

import android.net.Uri;

import com.google.gson.GsonBuilder;

public class Application extends android.app.Application {

    private void on(GsonBuilder builder){
        builder.registerTypeAdapter(Uri.class, new rebeccapurple.uri.Serializer());
    }

    @Override
    public void onCreate(){
        super.onCreate();

        functional.json.init(this::on);                     /** initialize gson lib */

        functional.log.console(false);                      /** remove console out */
        functional.log.depth(5);                            /** set method depth */
        functional.log.date(false);                         /** remove date out */
        functional.log.stacktrace(false);                   /** remove stacktrace  */
        functional.log.add(functional.android.log.get());   /** set android log method */

//        functional.contact.init("io.textory", "local");
//        functional.contact.init("io.textory", "hello@textory.com");
    }
}
