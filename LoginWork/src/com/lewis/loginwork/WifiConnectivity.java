package com.lewis.loginwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class WifiConnectivity extends BroadcastReceiver {

	private String netInfo;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net=(NetworkInfo) connectivityManager.getActiveNetworkInfo();
		if(net==null){
			netInfo="无网络连接";
		}else{
			 State wifi = connectivityManager.getNetworkInfo(
		                ConnectivityManager.TYPE_WIFI).getState();
		}
		
	}

}
