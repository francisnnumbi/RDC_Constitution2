package fnn.smirl.rdc.constitution.utils;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import java.io.File;
import android.speech.tts.UtteranceProgressListener;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.storage.StorageManager;

/*** the implementation of TextToSpeech */
public class Tts{
 private static Handler h = new Handler();
 private static TextToSpeech tts;
 private static boolean READING = false;

 /** check reading state */
 public static boolean isReading(){
	return READING;
 }

 /*** initialise the TTS */
 public Tts(final Context ctx){
	tts = new TextToSpeech(ctx.getApplicationContext(),
	 new TextToSpeech.OnInitListener() {
		@Override
		public void onInit(int status) {
		 if (status != TextToSpeech.ERROR){
			// confugure how it will speak
			//pitch, rate, language, engine, ...
			tts.setLanguage(Locale.FRANCE);
		 }}});
	
	 
 }

 /*** start reading text aloud */
 public static void speak(String txt){
	if(h == null)h = new Handler();
	//you can use TextToSpeech.QUEUE_ADD 
	//to append to the end of the queue
	tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
	READING = true;
	h.post(r);
 }

 /*** stop reading text aloud */
 public static void stop(){
	tts.stop();
	READING = false;
	h.removeCallbacks(r);
 }

 /*** close TTS and release resources */
 public static void shutDown(){
	tts.stop();
	tts.shutdown();
 }
 
 private static Runnable r = new Runnable(){

	@Override
	public void run()
	{
	 // TODO: Implement this method
	 if(!tts.isSpeaking()){
		READING = false;
		stop();
	 }
	 h.postDelayed(r, 1000);
	}

	
 };
}
