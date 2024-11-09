package com.aman.roomdbinandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText etxtTitle, etxtAmount;
Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etxtTitle = findViewById(R.id.etxtTitle);
        etxtAmount = findViewById(R.id.etxtAmount);
        btnAdd = findViewById(R.id.button);


        DatabaseHelper databaseHelper = DatabaseHelper.getdb(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etxtTitle.getText().toString();
                String amount = etxtAmount.getText().toString();

                databaseHelper.expenseDao().addTx(
                        new Expense(title, amount)
                );

               ArrayList<Expense> arrExpense = (ArrayList<Expense>) databaseHelper.expenseDao().getAllExpense();

           for(int i =0;i<arrExpense.size();i++){
               Log.d("Data", "Title: "+arrExpense.get(i).getTitle() + "Amount:"+ arrExpense.get(i).getAmount());
           }
            }
        });

    }
}