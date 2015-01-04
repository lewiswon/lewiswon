package com.lewis.loginwork;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		onClick();
		//保持网络连接
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
				detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(){
		
		final Button switchButton=(Button)findViewById(R.id.switch_account_btn);
		final ImageView loginImage=(ImageView)findViewById(R.id.login_image);
		final TextView hintWord=(TextView)findViewById(R.id.text_view);
		final EditText account=(EditText)findViewById(R.id.edit_text);
		final EditText password=(EditText)findViewById(R.id.edit_text_password);
		final Button loginButton=(Button)findViewById(R.id.login_button);
		final TextView user_stat=(TextView)findViewById(R.id.user);
		final TextView resultText=(TextView)findViewById(R.id.result);

		DBUtil dbUtil=new DBUtil(MainActivity.this,"user_account");
		SQLiteDatabase db=dbUtil.getReadableDatabase();
		Cursor cursor=db.query("user", new String[]{"account","count"}, "id=?", new String[]{"1"}, null, null, null);
		
		while(cursor.moveToNext()){
			int count=cursor.getInt(cursor.getColumnIndex("count"));
			 System.out.println(count);
			if(count==1){
				
				loginImage.setVisibility(View.GONE);
				switchButton.setVisibility(View.GONE);
				user_stat.setVisibility(View.GONE);
				resultText.setVisibility(View.GONE);
				hintWord.setVisibility(View.VISIBLE);
				account.setVisibility(View.VISIBLE);
				password.setVisibility(View.VISIBLE);
				loginButton.setVisibility(View.VISIBLE);
				loginButton.setText("登陆");
			}else{
				hintWord.setVisibility(View.GONE);
				account.setVisibility(View.GONE);
				password.setVisibility(View.GONE);
				loginButton.setVisibility(View.GONE);
			}
			
			String accountText=cursor.getString(cursor.getColumnIndex("account"));
			user_stat.setText("当前账号: "+accountText);
		}
	
	
		
		//切换按钮
		switchButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				loginImage.setVisibility(View.GONE);
				switchButton.setVisibility(View.GONE);
				user_stat.setVisibility(View.GONE);
				resultText.setVisibility(View.GONE);
				hintWord.setVisibility(View.VISIBLE);
				account.setVisibility(View.VISIBLE);
				password.setVisibility(View.VISIBLE);
				loginButton.setVisibility(View.VISIBLE);
				DBUtil dbUtil=new DBUtil(MainActivity.this,"user_account");
				SQLiteDatabase db=dbUtil.getReadableDatabase();
				Cursor cursor=db.query("user", new String[]{"account"}, "id=?", new String[]{"1"}, null, null, null);
				while(cursor.moveToNext()){
					String accountText=cursor.getString(cursor.getColumnIndex("account"));
					account.setText(accountText);
				}
				}
				
			
		});
		
//		final Animation ani=(AnimationSet)AnimationUtils.loadAnimation(this,R.anim.rotation);
//			loginImage.setAnimation(ani);
//			LinearInterpolator lin=new LinearInterpolator();
//			ani.setInterpolator(lin);
			
		//登陆图标
//		loginImage.getBackground().setAlpha(100);
		loginImage.setOnTouchListener(new OnTouchListener(){

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
			//	ani.start();	
				
				
				
				DBUtil dbUtil=new DBUtil(MainActivity.this,"user_account");
				SQLiteDatabase db=dbUtil.getReadableDatabase();
				Cursor cursor=db.query("user", new String[]{"account","pass"}, "id=?", new String[]{"1"}, null, null, null);
				
				while(cursor.moveToNext()){
					String accountText=cursor.getString(cursor.getColumnIndex("account"));
					String passwordText=cursor.getString(cursor.getColumnIndex("pass"));
					if(rUtil.isConnectWifi(getApplicationContext())==3&&rUtil.isConnectToRCSWU()){
					String result=LoginUtil.sendPost("http://192.168.3.5/cgi-bin/do_login", "n=100&is_pad=1&type=1&username="+accountText+"&password="+passwordText);
					if(rUtil.match(result)){
						Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT ).show();
						switchButton.setVisibility(View.INVISIBLE);
						loginImage.setVisibility(View.GONE);
						new Timer().schedule(new TimerTask(){
							  public void run() {
							android.os.Process.killProcess(android.os.Process.myPid());
							}}, 500);
					}else{
						Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT ).show();
					}
					
					System.out.println(accountText+passwordText+result);
				}else{
					Toast.makeText(getApplicationContext(), "请连接校园网后再试", Toast.LENGTH_SHORT).show();
				}
				
				
			}
				return false;
			}
		});
		
		//登陆按钮
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String accountText=account.getText().toString();
				String passwordText=password.getText().toString();
				
				DBUtil dbUtil=new DBUtil(MainActivity.this,"user_account");
				SQLiteDatabase db=dbUtil.getWritableDatabase();
				ContentValues values=new ContentValues();
				Cursor cursor=db.query("user", new String[]{"count"}, "id=?", new String[]{"1"}, null, null, null);
			
				if(rUtil.isConnectWifi(getApplicationContext())==3&&rUtil.isConnectToRCSWU()){
					String result=LoginUtil.sendPost("http://192.168.3.5/cgi-bin/do_login", "n=100&is_pad=1&type=1&username="+accountText+"&password="+passwordText);
					if(rUtil.match(result)){
						Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT ).show();
						while(cursor.moveToNext()){
							int count=cursor.getInt(cursor.getColumnIndex("count"));
							count++;
							values.put("count", count);
							System.out.println(count);
							}
							values.put("account",accountText);
							values.put("pass", passwordText);
							db.update("user", values, "id=?",new String[]{"1"});
						new Timer().schedule(new TimerTask(){
							  public void run() {
							android.os.Process.killProcess(android.os.Process.myPid());
							}}, 500);
					}else{
						Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT ).show();
					}
					
					System.out.println(accountText+passwordText+result);
						}else{
					Toast.makeText(getApplicationContext(), "请连接校园网后再试", Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
