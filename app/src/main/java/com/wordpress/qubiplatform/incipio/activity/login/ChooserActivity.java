package com.wordpress.qubiplatform.incipio.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wordpress.qubiplatform.incipio.R;

/**
 * Simple list-based Activity to redirect to one of the other Activities. This Activity does not
 * contain any useful code related to Firebase Authentication. You may want to start with
 * one of the following Files:
 *     {@link GoogleSignInActivity}
 *     {@link FacebookLoginActivity}
 *     {@link EmailPasswordActivity}
 */

public class ChooserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final Class[] CLASSES = new Class[]{
            GoogleSignInActivity.class,
            FacebookLoginActivity.class,
            EmailPasswordActivity.class,
    };

    private static final int[] DESCRIPTION_IDS = new int[] {
            R.string.desc_google_sign_in,
            R.string.desc_facebook_login,
            R.string.desc_emailpassword,
    };

    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_chooser);

        ListView listView = findViewById(R.id.list_view);
        MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);

        adapter.setDescriptionIds(DESCRIPTION_IDS);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int b = 1;
        int c = 0;
        Class clicked = CLASSES[position];
        startActivity(new Intent(this,clicked));
    }

    public static class MyArrayAdapter extends ArrayAdapter<Class> {

        private Context mContext;
        private Class[] mClasses;
        private int[] mDescriptionIds;

        public MyArrayAdapter(Context context, int resource, Class[] objects) {

            super(context, resource, objects);
            mContext = context;
            mClasses = objects;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(convertView == null) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2,null);
            }

            ((TextView) view.findViewById(android.R.id.text1)).setText(mClasses[position].getSimpleName());
            ((TextView) view.findViewById(android.R.id.text2)).setText(mDescriptionIds[position]);

            return view;
        }

        public void setDescriptionIds(int[] descriptionIds) {

            mDescriptionIds = descriptionIds;

        }
    }
}
