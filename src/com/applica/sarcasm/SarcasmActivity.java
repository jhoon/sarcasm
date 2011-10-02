package com.applica.sarcasm;

import com.github.droidfu.activities.BetterDefaultActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class SarcasmActivity extends BetterDefaultActivity {
//	private boolean sarcasmOn = false;
	private MediaPlayer mMediaPlayer = null; 
	private ImageView imgSarcasm;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        ((TextView)findViewById(R.id.txtSarcasm)).setText(((TextView)findViewById(R.id.txtSarcasm)).getText().toString().toUpperCase());
        imgSarcasm = (ImageView)findViewById(R.id.imgSarcasm);
        imgSarcasm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mMediaPlayer != null) { 
					mMediaPlayer.stop(); 
					mMediaPlayer.release(); 
				} 
				mMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.sw); 
				mMediaPlayer.start();	
				toggleSarcasm();
			}
		});        
    }

    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.global, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    		case R.id.mnuAbout:
    			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
    
    protected void onResume(){
    	super.onResume();
    	if(isRestoring() || isResuming()){
//    		toggleSarcasm();
        	Application app = ((Application)getApplication());
        	int drawId = (!app.sarcasmOn)? R.drawable.sarcasm_off: R.drawable.sarcasm_on;
        	imgSarcasm.setImageDrawable(getApplicationContext().getResources().getDrawable(drawId));
    	}
    }
    
    void toggleSarcasm(){
    	Application app = ((Application)getApplication());
    	int drawId = (app.sarcasmOn)? R.drawable.sarcasm_off: R.drawable.sarcasm_on;
    	((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(300);
    	imgSarcasm.setImageDrawable(getApplicationContext().getResources().getDrawable(drawId));
    	app.sarcasmOn = !app.sarcasmOn;
    }
}