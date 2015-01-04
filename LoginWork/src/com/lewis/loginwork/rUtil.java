package com.lewis.loginwork;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Service;
import android.content.Context;
import android.net.wifi.WifiManager;

public class rUtil {
public static boolean match(String input){
	Pattern p=Pattern.compile("[\\u767b\\u9646\\u6210\\u529f]");
	Matcher m=p.matcher(input);
	return m.find();
		}

public static int isConnectWifi(Context context){
	WifiManager wifiManager=(WifiManager)context.getSystemService(Service.WIFI_SERVICE);
	int wifiState=wifiManager.getWifiState();
	System.out.println(wifiState);
	return wifiState;
//	ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//	NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//	State wifi=networkInfo.getState();
//	if(wifiState==State.CONNECTED){
//		return true;
//	}
//	return false;
//}
}
public static boolean isConnectToRCSWU(){
	System.out.println("running");
	try {
		Process p=Runtime.getRuntime().exec("/system/bin/ping -c 1 -w 100 192.168.3.5");
		int status=p.waitFor();
		if(status==0){
			System.out.println(status);
			return true;
		}else{
			return false;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}
}
