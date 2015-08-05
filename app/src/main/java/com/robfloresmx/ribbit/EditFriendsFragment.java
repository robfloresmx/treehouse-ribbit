package com.robfloresmx.ribbit;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

/**
 * Created by robertoflores on 8/4/15.
 */
public class EditFriendsFragment extends ListFragment {

    private static final String ARGS_FRIENDS_ARRAY = "ARGS_FRIENDS_ARRAY";

    String[] mFriends;

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);

        mFriends = getArguments().getStringArray(ARGS_FRIENDS_ARRAY);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_checked, mFriends);

        setListAdapter(adapter);

    }

    public static EditFriendsFragment newInstance(String[] friends) {
        Bundle args = new Bundle();
        args.putStringArray(ARGS_FRIENDS_ARRAY, friends);
        EditFriendsFragment fragment = new EditFriendsFragment();
        fragment.setArguments(args);

        return fragment;

    }

}
