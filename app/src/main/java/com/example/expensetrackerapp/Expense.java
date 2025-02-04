package com.example.expensetrackerapp;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "expenses")
    public class Expense {
        @PrimaryKey(autoGenerate = true)
        public int id;

        public String category;
        public double amount;
        public String date;

        public Expense(String category, double amount, String date) {
            this.category = category;
            this.amount = amount;
            this.date = date;
        }
    }


