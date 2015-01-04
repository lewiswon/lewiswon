package com.lewis.loginwork;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlertWindowService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		
	}
}
