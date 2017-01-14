package fnn.smirl.rdc.constitution;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import fnn.smirl.rdc.constitution.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.os.Handler;
import android.widget.Toast;

public class Articles extends Fragment
{

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
 {
	// TODO: Implement this method
	View view = inflater.inflate(R.layout.articles, container, false);

	return view;
 }

 @Override
 public void onActivityCreated(Bundle savedInstanceState)
 {
	// TODO: Implement this method
	super.onActivityCreated(savedInstanceState);
	init();
	populateList();
 }

 //populate list
 private void populateList()
 {
	int artSize = getResources().getInteger(R.integer.article_size);
	constitutions = new Constitution[artSize];
	for (int i = 0; i < artSize; i++)
	{
	 String rd = getResources().getString(R.string.article_name) + " " + (i + 1);
	 fillConstitution(rd, i);

	}
	arrayList = new ArrayAdapter<Constitution>(MaConstitution.APPLICATION_CONTEXT, R.layout.listview_model, constitutions);
	articleslist.setAdapter(arrayList);
 }

 /**
	* Read the articles and their contents into an array of type Constitution.
	* @returns a boolean to certify the filling is completed.
	*/
 private boolean fillConstitution(String key, int index)
 {
	key = key.replace(" ", "_");
	int thisID = getResources().getIdentifier(key, "string", this.getClass().getPackage().getName());
	String value = getResources().getString(thisID);

	constitutions[index] = new Constitution("", "", "", "", key.replace("_", " "), value);

	return true;
 }

 // initialisation of variables
 private void init()
 {
	bmPrefs = MaConstitution.APPLICATION_CONTEXT.getSharedPreferences("fnn.smirl.rdc.constitution", 0);
	editor = bmPrefs.edit();
	

	articles_play_tts = (ToggleButton)getView().findViewById(R.id.articles_play_tts);
	articles_play_tts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton p1, boolean p2)
		{
		 // TODO: Implement this method
		 if (p2){
			start();
			MaConstitution.speak_tts(article_viewer.getText().toString());
			articles_play_tts.setTextOn("");
			
		 }else{
			MaConstitution.stop_tts();
			articles_play_tts.setTextOff("");
			
		 }
		}
	 });

	active_article = (TextView)getView().findViewById(R.id.active_article);
	article_viewer = (TextView)getView().findViewById(R.id.article_viewer);

	articleslist = (ListView)getView().findViewById(R.id.articleslist);

	articleslist.setOnItemClickListener(new OnItemClickListener(){


		@Override
		public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
		{
		 // TODO: Implement this method
		 try
		 {
			selectedListItemId = p3;
			String dex = articleslist.getItemAtPosition(p3).toString();
			cons = (Constitution)articleslist.getItemAtPosition(p3);
			String value = cons.toDetailedString();
			article_viewer.setText(value);
			active_article.setText(dex);
			MaConstitution.SHARABLE_CONSTITUTION = cons;
		 }
		 catch (Exception ex)
		 {
			//article_viewer1.setText("");
		 }
		}
	 });

	r1 = new MyRunner();
 }

 private void start(){
	if (handler1 == null){
	 handler1 = new Handler();
	}
	// call runner with handler
	handler1.post(r1);
 }

 private void pause(){
	handler1.removeCallbacks(r1);
	
 }

 private class MyRunner implements Runnable{

	@Override
	public void run()
	{
	 if (!Tts.isReading()){
		
//		Toast.makeText(MaConstitution.APPLICATION_CONTEXT, "Reading completed",
//		Toast.LENGTH_SHORT)
//		.show();
		
		articles_play_tts.setChecked(false);
		articles_play_tts.setBackgroundResource(R.drawable.speech_toggle_selector);
		pause();
	 }

	 handler1.postDelayed(r1, PAUSE);
	}
 }


 // Declaration of field variables

 String[] articleLabels;
 ArrayAdapter arrayList;
 private Constitution[] constitutions;

 Set<String> bookmark = new HashSet<String>();
 ListView articleslist;
 TextView active_article, article_viewer;
 ToggleButton articles_play_tts;
 
 Constitution cons = null;

 private Handler handler1 = new Handler();
 private MyRunner r1;
 private final int PAUSE = 500;

 ArrayList<String> bm = new ArrayList<String>();
 public final String PREFS_NAME = "rdc_const_bm";
 private SharedPreferences bmPrefs;
 private SharedPreferences.Editor editor;
 private int selectedListItemId = 0;

}
