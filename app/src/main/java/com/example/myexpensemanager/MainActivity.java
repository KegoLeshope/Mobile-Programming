package com.example.myexpensemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbh;
    TextView tv_date;
    EditText item, category, amount, itemNo, etDate;
    Button btnAddData;
    Button btnView;
    Button btnDelete;
    Button btnUpdate;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_date = (TextView) findViewById(R.id.tv_date);
        item = (EditText) findViewById(R.id.item);
        category = (EditText) findViewById(R.id.category);
        amount = (EditText) findViewById(R.id.amount);
        itemNo = (EditText) findViewById(R.id.itemNo);
        etDate = (EditText) findViewById(R.id.etDate);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnView = (Button) findViewById(R.id.btnView);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        dbh = new DatabaseHelper(this);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //AddData();
        //ViewData();
        DeleteData();
        UpdateData();

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListContents.class);
                startActivity(intent);
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Item = item.getText().toString();
                String Category = category.getText().toString();
                String Amount = amount.getText().toString();
                String Date = etDate.getText().toString();
                if (Item.length() !=0 && Category.length() !=0 && Amount.length() !=0 && Date.length() !=0) {
                    AddData(Item, Category, Amount,Date);
                    item.setText("");
                    category.setText("");
                    amount.setText("");
                    etDate.setText("");
                } else {
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                }
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                tv_date.setText(date);
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void AddData(String item, String category, String amount, String date) {
        boolean insertData = dbh.insertData(item, category, amount, date);

        if (insertData = true) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
    }





    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    private void DeleteData() {
        btnDelete.setOnClickListener((v)-> {
            Integer deleteRows = dbh.deleteData(itemNo.getText().toString());
                if (deleteRows > 0) {
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        );
    }

    public void UpdateData() {
        btnUpdate.setOnClickListener((v) -> {
            boolean IsUpdate = dbh.updateData(itemNo.getText().toString(),item.getText().toString(),category.getText().toString(),amount.getText().toString());
                if (IsUpdate == true){
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                 } else {
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_SHORT).show();
                }
             }
        );


    }
}