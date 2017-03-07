package fnn.smirl.rdc.constitution;

import android.view.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.widget.Toast;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.adapter.TabsPagerAdapter;
import fnn.smirl.rdc.constitution.info.About;
import fnn.smirl.rdc.constitution.info.AppInfo;
import fnn.smirl.rdc.constitution.utils.Tts;
import fnn.smirl.rdc.constitution.utils.core.Articles;
import fnn.smirl.simple.Serializer;
import java.io.File;
import android.support.design.widget.Snackbar;
import android.app.Activity;

public class MaConstitution extends AppCompatActivity
implements OnClickListener{
 
 static Serializer serializer = new Serializer();

 static File sdcard = Environment.getExternalStorageDirectory();
 static File dir = new File(sdcard.getAbsolutePath() + "/constitution/db");
 static File filename = new File(dir, "/constitution.json");

 public static Articles articles;
 

 public static Context APPLICATION_CONTEXT;
 public static String PACKAGE_NAME;
 public static Activity ACTIVITY;

 public static Tts tts;

 private Toolbar toolbar;
 private ViewPager pager;
 private TabsPagerAdapter tabsPagerAdapter;
 private TabLayout tabbar;
 private ActionBar actionbar;
 private FloatingActionButton fab;
 private String[] tabs;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.maconstitution);
	tabs = getResources().getStringArray(R.array.tabs);
	APPLICATION_CONTEXT = getApplicationContext();
	ACTIVITY=this;
	PACKAGE_NAME = getClass().getPackage().getName();
	init();
	//init_tts();

 }

 private void init() {
	setupToolbar();
	setupTabbar();
	setupPager();
	setupFab();
	retrieve();
 }

 private void setupToolbar() {
	toolbar = (Toolbar)findViewById(R.id.toolbar); 
	setSupportActionBar(toolbar);
	actionbar = getSupportActionBar();
	actionbar.setSubtitle(
	 AppInfo.getInstance(getApplicationContext()).toString());

	actionbar.setHomeAsUpIndicator(R.drawable.rdc_flag_1);
	actionbar.setDisplayHomeAsUpEnabled(true);
 }

 private void setupTabbar() {
	tabbar = (TabLayout)findViewById(R.id.tablayout);
	tabbar.setTabGravity(TabLayout.GRAVITY_FILL);
	tabbar.setHorizontalScrollBarEnabled(true);
	tabbar.setTabMode(TabLayout.MODE_SCROLLABLE);
	for (int i = 0; i< tabs.length; i++){
 tabbar.addTab(tabbar.newTab());
}

 }

 private void setupPager() {
	pager = (ViewPager)findViewById(R.id.pager);
	tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), tabs);
	pager.setAdapter(tabsPagerAdapter);
	tabbar.setupWithViewPager(pager);
 }

 private void setupFab() {
	fab = (FloatingActionButton)findViewById(R.id.fab);
	fab.setOnClickListener(this);
 }

 @Override
public void onClick(View v){
 
}
 /**
	* start reading text aloud
	*/
 public static void speak_tts(String txt) {
	if (txt.length() > 0) {
	 Toast.makeText(MaConstitution.APPLICATION_CONTEXT, "Lecture de l'article", Toast.LENGTH_SHORT)
		.show();
	 tts.speak(txt);
	}
 }

 @Override
 protected void onStop() {
	// TODO: Implement this method
	super.onStop();
	//stop_tts();

 }

 @Override
 protected void onDestroy() {
	// TODO: Implement this method
	super.onDestroy();
//	shutDown_tts();
 }

 @Override
 public void onBackPressed() {
	// TODO: Implement this method
	super.onBackPressed();
	//shutDown_tts();
 }

 /**
	* stop reading text aloud
	*/
 public static void stop_tts() {
	tts.stop();
 }

 /**
	* close TTS
	*/
 public static void shutDown_tts() {
	tts.shutDown();
 }

 private void init_tts() {
	tts = new Tts(MaConstitution.APPLICATION_CONTEXT);
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
	// TODO: Implement this method
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.main_menu, menu);
	return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	// TODO: Implement this method
	switch (item.getItemId()) {
	 case R.id.mm_share:
		//share();
		break;
	 case R.id.mm_bookmark:
		
		break;
	 case R.id.mm_about:
		startActivity(new Intent(getApplicationContext(), About.class));
		break;
	}
	return super.onOptionsItemSelected(item);
 }

// public void share() {
//	if (SHARABLE_CONSTITUTION != null) {
//	 ShareCompat.IntentBuilder
//		.from((Activity)this)
//		.setText(SHARABLE_CONSTITUTION.toDetailedString())
//		.setType("text/plain")
//		.setChooserTitle(SHARABLE_CONSTITUTION.getArticle())
//		.startChooser();
//	}
//
//	Toast
//	 .makeText(MaConstitution.APPLICATION_CONTEXT, 
//						 "Partage effective", Toast.LENGTH_SHORT)
//	 .show();
// }

 private static void store() {
	showSnack("Mise à jour de la base des données en cours...");
	serializer.serialize(filename.getAbsolutePath(), articles, Articles.class);
	showSnack("Mise à jour terminée.");
 }

 private static void retrieve() {
	articles = serializer.deserialize(filename.getAbsolutePath(), Articles.class);
 }
 
 private static void showSnack(String msg) {
	Snackbar.make(ACTIVITY.findViewById(R.id.coordinatorLayout),
								msg,
								Snackbar.LENGTH_SHORT)
	 .show();
 }
}
