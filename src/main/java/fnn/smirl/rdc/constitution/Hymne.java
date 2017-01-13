package fnn.smirl.rdc.constitution;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fnn.smirl.rdc.constitution.R;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Hymne extends Fragment
{
 MediaPlayer mp = null;
 Button hymne_play_btn, hymne_stop_btn;

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
		 play();
		}

	
 });
	hymne_stop_btn = (Button)view.findViewById(R.id.hymne_stop_btn);
	hymne_stop_btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
		 // TODO: Implement this method
		 stop();
		}


	 });
	
	
	 return view;
	 }

 public void play(){
	start_play_hymn();
 }
 
 public void stop(){
	stop_play_hymn();
 }
 
 void start_play_hymn(){
	if(mp == null){
	 mp = MediaPlayer.
	 create(MaConstitution.APPLICATION_CONTEXT,
	 R.raw.hymne_rdc_fr); 
	}
	mp.start();
 }
 
 void stop_play_hymn(){
	if(mp != null && mp.isPlaying()){
	 mp.pause();
	 mp.reset();
	 mp = null;
	}
 }
}
