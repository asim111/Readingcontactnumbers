package com.example.readingcontactnumbers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> phonelist;
    ListView listView;
    SearchView searchView;
    listSearch listsearch;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phonelist = new ArrayList<Contact>();
        listView = findViewById(R.id.contact_list);
        searchView = findViewById(R.id.contact_search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission
                (Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getcontacts();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getcontacts();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getcontacts() {

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            phonelist.add(new Contact(name, phone));
            Collections.sort(phonelist, new Comparator<Contact>() {
                @Override
                public int compare(Contact lhr, Contact rhr) {
                    return lhr.getName().compareTo(rhr.getName());
                }
            });

        }
        cursor.close();
        listsearch = new listSearch(this, phonelist);
        listView.setAdapter(listsearch);
        searchSettings();
    }

    private void searchSettings() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                listsearch.filter(text);
                return false;
            }
        });
    }
}
