package fnn.smirl.rdc.constitution.fragment;
import android.view.*;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.R;
import fnn.smirl.rdc.constitution.adapter.MyListAdapter;
import fnn.smirl.rdc.constitution.utils.Tts;
import fnn.smirl.rdc.constitution.utils.core.Article;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Collections;
import android.widget.AdapterView;
import android.widget.Adapter;

public class MyArticles extends Fragment {

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO: Implement this method
	View view = inflater.inflate(R.layout.articles, container, false);
	return view;
 }

 @Override
 public void onActivityCreated(Bundle savedInstanceState) {
	// TODO: Implement this method
	super.onActivityCreated(savedInstanceState);
	init();
 }

 // initialisation of variables
 public void init() {
	//articles_play_tts = (ToggleButton)getView().findViewById(R.id.articles_play_tts);
//	articles_play_tts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

//		@Override
	//	public void onCheckedChanged(CompoundButton p1, boolean p2) {
	// TODO: Implement this method
//		 if (p2){
//			start();
//			MaConstitution.speak_tts(article_viewer.getText().toString());
//			articles_play_tts.setTextOn("");
//			
//		 }else{
//			MaConstitution.stop_tts();
//			articles_play_tts.setTextOff("");
//			
//		 }
	//}
	// });
	titre_spinner = (Spinner)getView().findViewById(R.id.titre_spinner);
	ArrayAdapter<Long> spinner_adapter_titre 
	 = new ArrayAdapter<Long>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, getTitleList());
	titre_spinner.setAdapter(spinner_adapter_titre);
	titre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
		 // TODO: Implement this method
		 setChapterList(p3);
		}

		@Override
		public void onNothingSelected(AdapterView<?> p1) {
		 // TODO: Implement this method
		}
	 });

	chapitre_spinner = (Spinner)getView().findViewById(R.id.chapitre_spinner);
	chapitre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
		 // TODO: Implement this method
		 setSectionList(p3);
		}

		@Override
		public void onNothingSelected(AdapterView<?> p1) {
		 // TODO: Implement this method
		}
	 });
	section_spinner = (Spinner)getView().findViewById(R.id.section_spinner);
	section_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
		 // TODO: Implement this method
		 setArticleList(p3);
		}

		@Override
		public void onNothingSelected(AdapterView<?> p1) {
		 // TODO: Implement this method
		}
	 });
	active_article = (TextView)getView().findViewById(R.id.active_article);
	article_viewer = (TextView)getView().findViewById(R.id.article_viewer);

	articleslist = (ListView)getView().findViewById(R.id.articleslist);

	r1 = new MyRunner();
 }

 private ArrayList<Long> getTitleList() {
	ArrayList<Long> al = new ArrayList<Long>();
	al.add(new Long(0));
	for (int i = 0; i < MaConstitution.articles.list.size(); i++) {
	 Long o = MaConstitution.articles.list.get(i).titre.id;
	 if (!al.contains(o))al.add(o);
	}
	Collections.sort(al);
	currTitre = 0;
	return al;
 }

 private void setChapterList(long titreId) {
	ArrayList<Long> al = new ArrayList<Long>();
	al.add(new Long(0));
	for (int i = 0; i < MaConstitution.articles.list.size(); i++) {
	 Article art = MaConstitution.articles.list.get(i);
	 if (titreId == 0) {
		if (!al.contains(art.chapitre.id)) {
		 al.add(art.chapitre.id);
		}

	 } else if (titreId != 0 && art.titre.id == titreId) {
		if (!al.contains(art.chapitre.id)) {
		 al.add(art.chapitre.id);
		}

	 }
	}
	Collections.sort(al);
	ArrayAdapter<Long> aa =
	 new ArrayAdapter<Long>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, al);
	chapitre_spinner.setAdapter(aa);
	aa.setNotifyOnChange(true);
	chapitre_spinner.setSelection(0);
	currTitre = titreId;
	currChap = 0;
 }

 private void setSectionList(long chapId) {
	ArrayList<Long> al = new ArrayList<Long>();
	al.add(new Long(0));
	for (int i = 0; i < MaConstitution.articles.list.size(); i++) {
	 Article art = MaConstitution.articles.list.get(i);
	 if (art.titre.id == currTitre && chapId == 0) {
		if (!al.contains(art.section.id)) {
		 al.add(art.section.id);
		}
	 } else if (chapId != 0 && art.titre.id == currTitre && art.chapitre.id == chapId) {
		if (!al.contains(art.section.id)) {
		 al.add(art.section.id);
		}
	 }
	}
	Collections.sort(al);
	ArrayAdapter<Long> aa =
	 new ArrayAdapter<Long>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, al);
	section_spinner.setAdapter(aa);
	aa.setNotifyOnChange(true);
	section_spinner.setSelection(0);
	currChap = chapId;
	currSect = 0;
 }

 private void setArticleList(long sectId) {
	ArrayList<Article> al = new ArrayList<Article>();
	for (int i = 0; i < MaConstitution.articles.list.size(); i++) {
	 Article art = MaConstitution.articles.list.get(i);
	 if (art.titre.id == currTitre && art.chapitre.id == currChap && sectId == 0) {
		al.add(art);
	 } else if (sectId != 0 && art.titre.id == currTitre && art.chapitre.id == currChap && art.section.id == sectId) {
		al.add(art);
	 }
	}
	//Collections.sort(al);
	listAdapter = new MyListAdapter(MaConstitution.APPLICATION_CONTEXT, al);
	articleslist.setAdapter(listAdapter);
	listAdapter.setNotifyOnChange(true);
	currSect = sectId;
	articleslist.setSelection(0);
 }


 public static void display(Article art) {
	active_article.setText(art.toString());
	article_viewer.setText(art.content);
 }

 private void start() {
	if (handler1 == null) {
	 handler1 = new Handler();
	}
	// call runner with handler
	handler1.post(r1);
 }

 private void pause() {
	handler1.removeCallbacks(r1);

 }

 private class MyRunner implements Runnable {

	@Override
	public void run() {
	 if (!Tts.isReading()) {

//		Toast.makeText(MaConstitution.APPLICATION_CONTEXT, "Reading completed",
//		Toast.LENGTH_SHORT)
//		.show();

		//articles_play_tts.setChecked(false);
		//articles_play_tts.setBackgroundResource(R.drawable.speech_toggle_selector);
		pause();
	 }

	 handler1.postDelayed(r1, PAUSE);
	}
 }


 // Declaration of field variables

 //String[] articleLabels;
 MyListAdapter listAdapter;
 private Spinner titre_spinner, chapitre_spinner, section_spinner;

 ListView articleslist;
 static TextView active_article, article_viewer;
 //ToggleButton articles_play_tts;
 private long currTitre=0, currChap=0, currSect=0, currArt=0;

 private Handler handler1 = new Handler();
 private MyRunner r1;
 private final int PAUSE = 500;

}
