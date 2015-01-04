package com.lewis.loginwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

	private static final int VERSION=1;
	public DBUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public  DBUtil(Context context,String name){
		this(context,name,VERSION);
	}
	public DBUtil(Context context,String name,int version){
		this(context,name,null,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user(id int,account varchar(40),pass varchar(40),count int)");
		db.execSQL("insert into user values(1,'','','1')");
		System.out.println("Create database");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}


	}
