package com.example.medic;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import xmlrpc.XMLRPCClient;
import xmlrpc.XMLRPCException;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private int time;
	private TextView tv;
	
	private Intent intent;
	
	private XMLRPCClient client;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = new Intent(this, DisplayAlertActivity.class);
        
        tv = (TextView) findViewById(R.id.text);
        
        time = 0;
//        
        client = new XMLRPCClient("http://192.168.18.135/rpc");
       
        Timer t = new Timer();
      //Set the schedule function and rate
      t.scheduleAtFixedRate(new TimerTask() {

          @Override
          public void run() {
        	  runOnUiThread(new Runnable() {        		    
        		    public void run() {
        		        TextView tv = (TextView) findViewById(R.id.text);
        		        //tv.setText(String.valueOf(time));
        		        time += 1;
        		        if(time > 5 && time < 7){
        		        	try {
        		        		
        		        		//HttpPost postMethod;
        		        		//postMethod.addHeader("Content-Type", "text/xml");
        		        		//postMethod = new HttpPost("192.168.18.135");
        		        		
        		        		//String body = methodCall("ticket.query", null);

        		    			// set POST body
        		    			//HttpEntity entity = new StringEntity(body);
        		    			//postMethod.setEntity(entity);

        		    			//XMLRPCClient client = new DefaultHttpClient();
								//Log.d(Tag.LOG, "ros HTTP POST");
        		    			// execute HTTP POST request
        		        		Vector<Object> params = null;
        		        		Object a= new Object();
        		        		params.add(a);
        		        		Object obj = client.callEx("ticket.query",params.toArray());
        		        		
        		        		Object[] oArray = (Object[])obj;
        		        		
        		        		HashMap<String,String>[] results = new HashMap[oArray.length];
        		        		
        		        		for(int i=0; i<oArray.length;i++){
        		        			if(oArray[i] instanceof HashMap<?,?>){
        		        				results[i] = (HashMap<String,String>) oArray[i];
        		        				
        		        			}
        		        		}
        		        		if(obj.equals(null))
        		        			tv.setText("Es nulo!!!!");
        		        		else
        		        			tv.setText(results[0].toString());
        		        		//final Object result = client.callEx("ticket.query", params);
        		        		
        		        		//tv.setText(result.toString());
        		        		
        		        		//handler.post(new Runnable() {
        							//public void run() {
        								//callBack.callFinished(result);
        						//	}
        		    			//});
        		    			//Object  rrr = client.callEx("ticket.query",tv,null);
        		    			//if(rrr.getClass().isArray()){
//    		    				Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//    		    				Byte[] a2= (Byte[]) client.call("ticket.query","status!=closed");
//    		    				boolean[] a3= (boolean[]) client.call("ticket.query","status!=closed");
//    		    				long[] a4= (long[]) client.call("ticket.query","status!=closed");
//    		    				double[] a5= (double[]) client.call("ticket.query","status!=closed");
//    		    				String[] a6= (String[]) client.call("ticket.query","status!=closed");
//    		    				float[] a7= (float[]) client.call("ticket.query","status!=closed");
//    		    				int[] a8= (int[]) client.call("ticket.query","status!=closed");
    		    				//Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//    		    				Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//    		    				Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//    		    				Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//    		    				Integer[] ar= (Integer[]) client.call("ticket.query","status!=closed");
//        		    				//        		    			}
        		    				//tv.setText(String.valueOf(ar));
        		        	} catch (Exception e) {
        		    			// TODO Auto-generated catch block
								
								String stack = Log.getStackTraceString(e);
								tv.setText(stack);
        		    		}
        		        	
        		        	//if(client.call responde con alerta){
    		        		startActivity(intent);// para que no salga
    		        	//}
        		        }
        		    }	     
        		});
          }             
      },
      //Set how long before to start calling the TimerTask (in milliseconds)
      0,
      //Set the amount of time between each execution (in milliseconds)
      1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
