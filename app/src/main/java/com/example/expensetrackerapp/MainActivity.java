package com.example.expensetrackerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etAmount, etCategory;
    Button btnAddExpense;
    ListView listViewExpenses;
    ArrayList<String> expenseList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ExpenseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etCategory = findViewById(R.id.etCategory);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        listViewExpenses = findViewById(R.id.listViewExpenses);

        db = ExpenseDatabase.getInstance(this);
        loadExpenses();

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = etCategory.getText().toString();
                double amount = Double.parseDouble(etAmount.getText().toString());
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                db.expenseDao().insertExpense(new Expense(category, amount, date));
                loadExpenses();
            }
        });
    }

    private void loadExpenses() {
        List<Expense> expenses = db.expenseDao().getAllExpenses();
        expenseList.clear();
        for (Expense exp : expenses) {
            expenseList.add(exp.category + " - $" + exp.amount + " 📅 " + exp.date);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        listViewExpenses.setAdapter(adapter);
    }
}
