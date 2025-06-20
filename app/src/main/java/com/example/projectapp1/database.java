package com.example.projectapp1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Users table
        String qry1 = "CREATE TABLE IF NOT EXISTS users(username TEXT, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(qry1);

        // Cart table
        String qry2 = "CREATE TABLE IF NOT EXISTS cart(username TEXT, price FLOAT, otype TEXT)";
        sqLiteDatabase.execSQL(qry2);

        // Order table
        String qry3 = "CREATE TABLE IF NOT EXISTS orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INTEGER, otype TEXT, amount FLOAT, lab TEXT)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // You can add upgrade logic here if needed later
    }

    // Register user
    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    // Login user
    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[]{username, password};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);

        if (c.moveToFirst()) {
            result = 1;
        }

        c.close();
        db.close();
        return result;
    }

    // Add item to cart
    public void addCart(String username, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("price", price);
        cv.put("otype", otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    // Check if cart exists for a user
    public int checkCart(String username) {
        int result = 0;
        String[] str = new String[]{username};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=?", str);

        if (c.moveToFirst()) {
            result = 1;
        }

        c.close();
        db.close();
        return result;
    }

    // Remove cart items by username and order type
    public void removeCart(String username, String otype) {
        String[] str = new String[]{username, otype};

        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? AND otype=?", str);
        db.close();
    }

    // Get cart data for user and order type
    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        String[] str = new String[]{username, otype};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?", str);

        if (c.moveToFirst()) {
            do {
                String price = c.getString(1);
                arr.add("$" + price);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return arr;
    }

    // Add order to orderplace table
    public void addOrder(String username, String fullname, String address, String contact, int pincode, String otype, float price, String lab) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("otype", otype);
        cv.put("amount", price);
        cv.put("lab", lab);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    // Get order data for a user
    public ArrayList<String> getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        String[] str = new String[]{username};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=?", str);

        if (c.moveToFirst()) {
            do {
                arr.add(
                        c.getString(1) + "$" +  // fullname
                                c.getString(2) + "$" +  // address
                                c.getString(3) + "$" +  // contactno
                                c.getString(4) + "$" +  // pincode
                                c.getString(5) + "$" +  // otype
                                c.getString(6) + "$" +  // amount
                                c.getString(7) + "$" +  // lab
                                c.getString(0)          // username (or other)
                );
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return arr;
    }
}
