package fnn.smirl.rdc.constitution;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fnn.smirl.rdc.constitution.R;
import java.util.ArrayList;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

public class Recherche extends Fragment
{

 private String baseName;
 private int baseSize;
 private int occurences = 0;
 ToggleButton recherche_play_tts;

 EditText recherche_phrase;
 ImageButton recherche_btn;
 TextView recherche_compte, recherche_viewer;
 ListView recherche_list;

 private Constitution[] constitutions;
 private ArrayList<Constitution> map = new ArrayList<Constitution>();

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
 {
	// TODO: Implement this method
	View view = inflater.inflate(R.layout.recherche, container, false);
	
	return view;
 }

 @Override
 public void onActivityCreated(Bundle savedInstanceState)
 {
	// TODO: Implement this method
	super.onActivityCreated(savedInstanceState);
	init();
	fillConstitution();
	
 }

 private boolean fillConstitution()
 {
	int artSize = getResources().getInteger(R.integer.article_size);
	constitutions = new Constitution[artSize];
	try
	{
	 for (int i = 0; i < artSize; i++)
	 {
		String key = getResources().getString(R.string.article_name) + "_" + (i + 1);
		int thisID = getResources().getIdentifier(key, "string", this.getClass().getPackage().getName());
		String value = getResources().getString(thisID);

		constitutions[i] = new Constitution("", "", "", "", key.replace("_", " "), value);
	 }
	}
	catch (Exception exe)
	{
	 // Alert.shortMessage(this, exe.getMessage());
	}
	return true;
 }

 private boolean fillArray(String phrase)
 {
	map.clear();
	for (int i = 0; i < constitutions.length; i++)
	{
	 try
	 {
		String value = Harmony.harmonize(MaConstitution.APPLICATION_CONTEXT, constitutions[i].getText());
		phrase = Harmony.harmonize(MaConstitution.APPLICATION_CONTEXT, phrase);
		if (value.toLowerCase().contains(phrase.toLowerCase()))
		{

		 map.add(constitutions[i]);
		}
	 }
	 catch (Exception ex)
	 {}
	}
	occurences = map.size();
	ArrayAdapter list = new ArrayAdapter<Constitution>(MaConstitution.APPLICATION_CONTEXT, R.layout.listview_model, map);
	recherche_list.setAdapter(list);
	recherche_compte.setText("" + occurences);
	return true;
 }

 private void init()
 {

	recherche_play_tts = (ToggleButton)getView().findViewById(R.id.recherche_play_tts);
	recherche_play_tts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton p1, boolean p2)
		{
		 // TODO: Implement this method
		 if(p2){
			MaConstitution.speak_tts(recherche_viewer.getText().toString());
		 }else{
			MaConstitution.stop_tts();
		 }
		}
	 });
	
	baseName = getResources().getString(R.string.article_name);
	baseSize = getResources().getInteger(R.integer.article_size);

	recherche_viewer = (TextView)getView().findViewById(R.id.recherche_viewer);
	recherche_compte = (TextView)getView().findViewById(R.id.rechercher_compte);

	recherche_list = (ListView)getView().findViewById(R.id.recherche_list);
	recherche_phrase = (EditText)getView().findViewById(R.id.recherche_phrase);

	recherche_btn = (ImageButton)getView().findViewById(R.id.recherche_btn);
	recherche_btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
		 // TODO: Implement this method
		 search();
		}
	 });
	// listener
	recherche_list.setOnItemClickListener(new OnItemClickListener()
	 {
		@Override
		public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
		{
		 // TODO: Implement this method
		 String key = "";
		 try
		 {
			key = recherche_list.getItemAtPosition(p3).toString();
			String value = ((Constitution)recherche_list.getItemAtPosition(p3)).toDetailedString();
			//Alert.shortMessage(getApplicationContext(), "cccc: " + value);
			recherche_viewer.setText(value);
			//((TextView)findViewById(R.id.articleHeader)).setText(key);

		 }
		 catch (Exception ex)
		 {}
		}
	 });
 }

 /**
	* search the phrase from the input and search for the corresponding articles
	*/
 private void search()
 {
	String inputIt = recherche_phrase.getText().toString();
	fillArray(inputIt);
 }
}
