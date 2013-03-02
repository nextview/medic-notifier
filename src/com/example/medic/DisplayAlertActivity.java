package com.example.medic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DisplayAlertActivity extends Activity{
	
	private Vibrator v;
	
	private Window win;
	
	private Button Baceptar, Brechazar;
	private TextView TVvoy;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_main);

        Baceptar = (Button) findViewById(R.id.aceptar);
        Brechazar = (Button) findViewById(R.id.rechazar);
        TVvoy = (TextView) findViewById(R.id.voy);        
        
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 0, 200, 500 };
        
        v.vibrate(pattern, 0);
        
        win = getWindow();
        
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
   		     | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        
        Baceptar.setOnClickListener(new OnClickListener() {
        	 
			public void onClick(View view) {
				TVvoy.setText("VOY");
				TVvoy.setTextColor(Color.GREEN);
				v.cancel();
			}
 
		});

        Brechazar.setOnClickListener(new OnClickListener() {
       	 
			public void onClick(View view) {
				DisplayAlertActivity.this.finish();
				v.cancel();
			}
		});
    }
}
