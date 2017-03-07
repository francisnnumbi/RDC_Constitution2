package fnn.smirl.rdc.constitution.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.R;

public class Hymne extends Fragment
{
 MediaPlayer mp = null;
 Button hymne_play_btn;
 private Handler handler;

 @Override
 public View onCreateView(LayoutInflater inflater,
													ViewGroup container, Bundle savedInstanceState)
 {
	// TODO: Implement this method
	View view = inflater.inflate(R.layout.hymne, container, false);
	hymne_play_btn = (Button)view.findViewById(R.id.hymne_play_btn);
	hymne_play_btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
		 // TODO: Implement this method
		 if (mp != null && mp.isPlaying()){
			//stop();
		 }else{
			//play();
		 }
		}
	 });

	return view;
 }

 public void play(){
	if(handler == null) handler = new Handler();
	handler.post(hymnTask);
	start_play_hymn();
	hymne_play_btn.setText("Arreter");
 }

 public void stop(){
	stop_play_hymn();
	hymne_play_btn.setText("Ecouter");
	handler.removeCallbacks(hymnTask);
 }

 void start_play_hymn(){
	if (mp == null){
	 mp = MediaPlayer.
		create(MaConstitution.APPLICATION_CONTEXT,
					 R.raw.hymne_rdc_fr); 
	}
	mp.start();
 }

 void stop_play_hymn(){
	if (mp != null && mp.isPlaying()){
	 mp.pause();
	 mp.reset();
	 mp = null;
	}
 }
 
 private Runnable hymnTask = new Runnable(){

	@Override
	public void run()
	{
	 // TODO: Implement this method
	 
	 if (mp != null && !mp.isPlaying()){
		stop();
	 }
	 handler.postDelayed(hymnTask, 1000);
	}

	
	
 };
}
