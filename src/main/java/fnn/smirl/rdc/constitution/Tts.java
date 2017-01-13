package fnn.smirl.rdc.constitution;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import java.io.File;
import android.speech.tts.UtteranceProgressListener;
import android.content.SharedPreferences;

/*** the implementation of TextToSpeech */
public class Tts{
 private static TextToSpeech tts;
 private static boolean READING = false;
 public static final String UTTERANCE_ID = TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID;

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
	tts.setOnUtteranceProgressListener(new UtteranceProgressListener(){

		@Override
		public void onStart(String p1)
		{
		 // TODO: Implement this method
		 READING = true;
		}

		@Override
		public void onDone(String p1)
		{
		 // TODO: Implement this method
		 READING = false;
		}

		@Override
		public void onError(String p1)
		{
		 // TODO: Implement this method
		}

			
		 });
 }

 /*** start reading text aloud */
 public static void speak(String txt){
	//you can use TextToSpeech.QUEUE_ADD 
	//to append to the end of the queue
	tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
	READING = true;
 }

 /*** stop reading text aloud */
 public static void stop(){
	tts.stop();
 }

 /*** close TTS and release resources */
 public static void shutDown(){
	tts.stop();
	tts.shutdown();
 }
}
