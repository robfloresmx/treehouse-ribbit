package com.robfloresmx.ribbit;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class EditFriendsActivity extends ActionBarActivity {

    public static final String TAG = EditFriendsActivity.class.getSimpleName();

    protected List<ParseUser> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friends);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setProgressBarIndeterminateVisibility(true);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.orderByAscending(ParseConstants.KEY_USERNAME);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                setProgressBarIndeterminateVisibility(false);
                if(e == null) {
                    //Success
                    mUsers = users;
                    String[] usernames = new String[mUsers.size()];
                    int i = 0;
                    for(ParseUser user : users ){
                        usernames[i] = user.getUsername();
                        i++;
                    }

                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
                    if(fragment == null) {
                        fragment = EditFriendsFragment.newInstance(usernames);
                        fm.beginTransaction().add(R.id.fragmentContainer, fragment)
                                .commit();
                    }

                } else {
                    Log.e(TAG, e.getMessage());
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditFriendsActivity.this);
                    builder.setMessage(e.getMessage())
                            .setTitle(R.string.error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
}
