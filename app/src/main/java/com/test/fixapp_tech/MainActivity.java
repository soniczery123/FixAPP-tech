package com.test.fixapp_tech;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.test.fixapp_tech.DB.UserDBHelper;
import com.test.fixapp_tech.model.MyJobListAdapter;
import com.test.fixapp_tech.model.MyJobsModel;
import com.test.fixapp_tech.model.order;
import com.test.fixapp_tech.model.orderlistAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnNews, btnMyjob, btnProfile,btnLogout;
    ViewFlipper viewFlipper;
    RelativeLayout relativeLayout;
    private MyJobListAdapter mAdapter_Myjob;
    private orderlistAdapter mAdapter_New;
    boolean check = true;
    private UserDBHelper mHelper;
    private SQLiteDatabase mDb;
    private ArrayList<MyJobsModel> myJobsModels_ItemList = new ArrayList<>();
    private ArrayList<order> news_ItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new UserDBHelper(this);
        mDb = mHelper.getReadableDatabase();
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        btnNews = (Button) findViewById(R.id.buttonNews);
        btnMyjob = (Button) findViewById(R.id.buttonMyjobs);
        btnProfile = (Button) findViewById(R.id.buttonProfile);
        btnLogout = (Button) findViewById(R.id.buttonLogout);
        btnNews.setBackgroundColor(Color.TRANSPARENT);
        btnMyjob.setBackgroundColor(Color.TRANSPARENT);
        btnProfile.setBackgroundColor(Color.TRANSPARENT);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        btnNews.setEnabled(false);
        news_ItemList.clear();
        news_ItemList.add(new order("AAA", "aaa", "2/5/2561",
                R.drawable.ic_launcher_background));
        news_ItemList.add(new order("BBB", "bbb", "3/5/2561",
                R.drawable.ic_launcher_background));

        mAdapter_New = new orderlistAdapter(
                MainActivity.this,
                R.layout.item,
                news_ItemList
        );

        ListView lv = (ListView) findViewById(R.id.list_item);
        lv.setAdapter(mAdapter_New);

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNews.setEnabled(false);
                btnMyjob.setEnabled(true);
                btnProfile.setEnabled(true);
                if(!check) {
                    check = true;
                    viewFlipper.showNext();
                }
                news_ItemList.clear();
                news_ItemList.add(new order("AAA", "aaa", "2/5/2561",
                        R.drawable.ic_launcher_background));
                news_ItemList.add(new order("BBB", "bbb", "3/5/2561",
                        R.drawable.ic_launcher_background));

                mAdapter_New = new orderlistAdapter(
                        MainActivity.this,
                        R.layout.item,
                        news_ItemList
                );

                ListView lv = (ListView) findViewById(R.id.list_item);
                lv.setAdapter(mAdapter_New);

            }
        });

        btnMyjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNews.setEnabled(true);
                btnMyjob.setEnabled(false);
                btnProfile.setEnabled(true);
                if(!check) {
                    check = true;
                    viewFlipper.showNext();
                }
                myJobsModels_ItemList.clear();
                myJobsModels_ItemList.add(new MyJobsModel("AAA", "aaa", "3/5/2561",
                        R.drawable.ic_launcher_background, R.drawable.ic_clear_black_24dp));
                myJobsModels_ItemList.add(new MyJobsModel("BBB", "bbb", "2/5/2561",
                        R.drawable.ic_launcher_background, R.drawable.ic_done_black_24dp));
                myJobsModels_ItemList.add(new MyJobsModel("CCC", "ccc", "3/5/2561",
                        R.drawable.ic_launcher_background, R.drawable.ic_clear_black_24dp));

                mAdapter_Myjob = new MyJobListAdapter(
                        MainActivity.this,
                        R.layout.item_list_my_jobs,
                        myJobsModels_ItemList
                );

                ListView lv = (ListView) findViewById(R.id.list_item);
                lv.setAdapter(mAdapter_Myjob);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNews.setEnabled(true);
                btnMyjob.setEnabled(true);
                btnProfile.setEnabled(false);
                if(check) {
                    viewFlipper.showNext();
                    check = false;
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to log out?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ContentValues cv = new ContentValues();
                        cv.put(mHelper.COL_EMAIL, "none");
                        mDb.update(mHelper.TABLE_NAME_LOGIN,cv,"id" + " = 1",null);
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) { // Method Inflate Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.noti, menu); // เริ่ม Inflate menu
        return super.onCreateOptionsMenu(menu); // ไปแสดงผลที่ Activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // Method ทำงานหลังกด Menu
        int id = item.getItemId();
        if (id == R.id.notificationButton) {
            Toast.makeText(MainActivity.this, "ALERT", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
            }
        });
        builder.show();

    }


}
