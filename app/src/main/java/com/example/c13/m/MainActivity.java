package com.example.c13.m;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    private LinearLayout mainLinearLayourLL;
    private ListView listView;
    private EditText nameET;
    private EditText depositET;
    private EditText mealET;
    private Button addBtn;
    MealInfo mealInfo;
    MealAdapter adapter;
    ArrayList<MealInfo> messMealArrayList;
    MealInfoManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        nameET = (EditText) findViewById(R.id.name);
        depositET = (EditText) findViewById(R.id.deposit);
        mealET = (EditText) findViewById(R.id.meal);
        addBtn = (Button) findViewById(R.id.add);
        mainLinearLayourLL=(LinearLayout)findViewById(R.id.linear_main_layour);
        mealInfo = new MealInfo();
        messMealArrayList = new ArrayList<MealInfo>();
        manager = new MealInfoManager(this);
        mealInfo.setContext(this);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        mainLinearLayourLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });

        try {
            showListView();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
        }
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }


    public void addToList(View view) {
        if (addBtn.getText().toString().toUpperCase().equals("ADD")) {
            String name = nameET.getText().toString();
            String deposit = depositET.getText().toString();
            String meal = mealET.getText().toString();

            if (name.length() > 0 && deposit.length() > 0 && meal.length() > 0) {

                mealInfo.setName(nameET.getText().toString());
                mealInfo.setDeposit(Float.valueOf(depositET.getText().toString()));
                mealInfo.setMeal(Float.valueOf(mealET.getText().toString()));

                mealInfo = new MealInfo(mealInfo.getName(), mealInfo.getDeposit(), mealInfo.getMeal());
                manager.addMealInfo(mealInfo);
                showListView();
                clearEditTExt();

            } else {
                Toast toast = Toast.makeText(MainActivity.this, "Please Insert Data", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else if (addBtn.getText().toString() == "DONE") {
            addBtn.setText("ADD");
            mealInfo.setName(nameET.getText().toString());
            mealInfo.setDeposit(Float.valueOf(depositET.getText().toString()));
            mealInfo.setMeal(Float.valueOf(mealET.getText().toString()));

            MealInfo newInfo = new MealInfo(mealInfo.getName(), mealInfo.getDeposit(), mealInfo.getMeal());
            manager.updateContact(mealInfo.getId(), newInfo);
            clearEditTExt();
            showListView();

        }

    }

    private void showListView() {
        messMealArrayList = manager.getAllMealInfo();
        adapter = new MealAdapter(MainActivity.this, messMealArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mealInfo.setId(i + 1);
//                view.setBackgroundColor(Color.BLUE);
               /* for(int index=0; index<((ViewGroup)view).getChildCount(); ++index) {
                    View nextChild = ((ViewGroup)view).getChildAt(index);


                    Toast.makeText(MainActivity.this,String.valueOf(nextChild), Toast.LENGTH_SHORT).show();
                }*/
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(MainActivity.this);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });
    }



    public void calculate(View view) {
        Intent intent = new Intent(MainActivity.this, DialogActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                new AlertDialog.Builder(this)
                        .setTitle("Delete All")
                        .setMessage("Are you sure you want to delete all data?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    manager.deleteAllContact();
                                    showListView();
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            case R.id.about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                Intent finishIntent = new Intent(MainActivity.this, MainActivity.class);
                finishIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishIntent.putExtra("EXIT", true);
                startActivity(finishIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearAll(View view) {
        try {
            manager.deleteAllContact();
            messMealArrayList = manager.getAllMealInfo();
            adapter = new MealAdapter(MainActivity.this, messMealArrayList);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
            listView.setAdapter(adapter);
        }
    }


    public void clearEditTExt() {
        nameET.setText("");
        depositET.setText("");
        mealET.setText("");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                new AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete data?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                manager.deleteMealInfo(mealInfo.getId());
                                showListView();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            case R.id.edit:
                MealInfo mealInfoById = manager.getMemberMealInfoById(mealInfo.getId());
                nameET.setText(mealInfoById.getName());
                depositET.setText(mealInfo.checkInteger(mealInfoById.getDeposit()));
                mealET.setText(mealInfo.checkInteger(mealInfoById.getMeal()));
                addBtn.setText("DONE");

                break;
        }
        return false;
    }
}
