package com.robfloresmx.ribbit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by robfloresmx on 28/07/2015.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8hV4PPjOkqBYpgJaQHhnrfiBP4GgvH3BctevvgHB", "GQg61KqgcBGusAhVY0SmiiG8TFIhDbCQD9izdSLL");
    }

}
