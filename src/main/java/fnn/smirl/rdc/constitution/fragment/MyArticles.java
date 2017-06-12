package fnn.smirl.rdc.constitution.fragment;
import android.view.*;
import android.widget.*;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.R;
import fnn.smirl.rdc.constitution.adapter.MyListAdapter;
import fnn.smirl.rdc.constitution.utils.Tts;
import fnn.smirl.rdc.constitution.utils.core.Article;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;

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
	return MaConstitution.articles.getTitres();
 }

 private void setChapterList(long titreId) {
	ArrayList<Long> al = MaConstitution.articles.getChapitres(titreId);
	ArrayAdapter<Long> aa =
	 new ArrayAdapter<Long>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, al);
	chapitre_spinner.setAdapter(aa);
	aa.setNotifyOnChange(true);
	chapitre_spinner.setSelection(0);
	currTitre = titreId;
	currChap = 0;
 }

 private void setSectionList(long chapId) {
	ArrayList<Long> al = MaConstitution.articles.
	getSections(currTitre, chapId);
	ArrayAdapter<Long> aa =
	 new ArrayAdapter<Long>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, al);
	section_spinner.setAdapter(aa);
	aa.setNotifyOnChange(true);
	section_spinner.setSelection(0);
	currChap = chapId;
	currSect = 0;
 }

 private void setArticleList(long sectId) {
	ArrayList<Article> al = MaConstitution.articles.
	getArticles(currTitre, currChap, sectId);
	listAdapter = new MyListAdapter(MaConstitution.APPLICATION_CONTEXT, al);
	articleslist.setAdapter(listAdapter);
	listAdapter.setNotifyOnChange(true);
	currSect = sectId;
	articleslist.setSelection(0);
 }


 public static void display(Article art) {
	active_article.setText(art.toString());
	article_viewer.setText(art.toDetailedString());
	MaConstitution.setCurrentArticle(art);
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
