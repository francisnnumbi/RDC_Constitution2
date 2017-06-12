package fnn.smirl.rdc.constitution.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fnn.smirl.rdc.constitution.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import fnn.smirl.rdc.constitution.MaConstitution;

public class Signet extends Fragment
{
 SharedPreferences sign_pref;
 SharedPreferences.Editor sign_edit;

 ListView signet_lv;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
 {
	// TODO: Implement this method
	View view = inflater.inflate(R.layout.signet, container, false);

	return view;
 }

 @Override
 public void onViewCreated(View view, Bundle savedInstanceState)
 {
	// TODO: Implement this method
	super.onViewCreated(view, savedInstanceState);
	signet_lv = (ListView)getView().findViewById(R.id.signet_lv);
	
	//fill_list();

 }

 private void fill_list(){
	if (sign_pref.contains("bookmarks")){
	 Set<String> bookies = sign_pref.getStringSet("bookmarks", new HashSet<String>());
	 if (bookies.size() > 0){
		Iterator<String> iter = bookies.iterator();
		ArrayList<String> list = new ArrayList<String>();
		while (iter.hasNext()){
		 list.add(iter.next());
		}
		Collections.sort(list);
		ArrayAdapter<String> array = new ArrayAdapter<String>(MaConstitution.APPLICATION_CONTEXT, R.layout.tamplate, list);
		signet_lv.setAdapter(array);

		signet_lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
			{
			 // TODO: Implement this method
			 // String sig = signet_lv.getItemAtPosition(p3).toString();
			 // Articles frag = (Articles)
			 // getFragmentManager().findFragmentById(R.layout.articles);
			 // frag.init();
			 //frag.populateList();
			 // frag.selectItem(sig);

			}


		 });
	 }
	}
 }


}
