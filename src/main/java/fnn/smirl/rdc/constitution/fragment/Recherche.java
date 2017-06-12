package fnn.smirl.rdc.constitution.fragment;
import android.view.*;
import android.widget.*;
import fnn.smirl.rdc.constitution.utils.*;

import fnn.smirl.rdc.constitution.R;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.utils.core.Article;

public class Recherche extends Fragment {

 EditText recherche_phrase;
 ImageButton recherche_btn;
 TextView recherche_compte, recherche_viewer;
 ListView recherche_list;

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO: Implement this method
	return inflater.inflate(R.layout.recherche, container, false);
 }

 @Override
 public void onActivityCreated(Bundle savedInstanceState) {
	// TODO: Implement this method
	super.onActivityCreated(savedInstanceState);
	init();
 }

 private boolean fillArray(String phrase) {
	ArrayAdapter<Article> list = 
	 new ArrayAdapter<Article>(MaConstitution.APPLICATION_CONTEXT, 
														 R.layout.tamplate, MaConstitution.articles.getArticlesThatContain(phrase));
	recherche_list.setAdapter(list);
	String lb = list.getCount() > 1 ? "Articles trouvés\n" : "Article trouvé\n";
	recherche_compte.setText(lb + list.getCount());
	return true;
 }

 private void init() {

	recherche_viewer = (TextView)getView().findViewById(R.id.recherche_viewer);
	recherche_compte = (TextView)getView().findViewById(R.id.rechercher_compte);

	recherche_list = (ListView)getView().findViewById(R.id.recherche_list);
	recherche_phrase = (EditText)getView().findViewById(R.id.recherche_phrase);

	recherche_btn = (ImageButton)getView().findViewById(R.id.recherche_btn);
	recherche_btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View p1) {
		 // TODO: Implement this method
		 search();
		}
	 });
	// listener
	recherche_list.setOnItemClickListener(new OnItemClickListener()
	 {
		@Override
		public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
		 // TODO: Implement this method

		 try {
			MaConstitution.setCurrentArticle((Article)p1.getItemAtPosition(p3));
			String value = MaConstitution.getCurrentArticle().toDetailedString();
			recherche_viewer.setText(value);
			MaConstitution.refreshItemBGColor(p1, p3);
		 }
		 catch (Exception ex) {}
		}
	 });
 }

 /**
	* search the phrase from the input and search for the corresponding articles
	*/
 private void search() {
	String inputIt = recherche_phrase.getText().toString();
	fillArray(inputIt);
 }

 

}
