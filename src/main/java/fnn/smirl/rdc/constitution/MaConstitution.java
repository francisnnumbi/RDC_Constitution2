package fnn.smirl.rdc.constitution;

import android.support.design.widget.*;
import android.view.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.adapter.TabsPagerAdapter;
import fnn.smirl.rdc.constitution.info.About;
import fnn.smirl.rdc.constitution.info.AppInfo;
import fnn.smirl.rdc.constitution.utils.Tts;
import fnn.smirl.rdc.constitution.utils.core.Article;
import fnn.smirl.rdc.constitution.utils.core.Articles;
import fnn.smirl.simple.Serializer;
import java.io.File;
import android.widget.PopupWindow;
import fnn.smirl.rdc.constitution.utils.*;
import android.content.res.*;

public class MaConstitution extends AppCompatActivity
implements OnClickListener {

 static Serializer serializer = new Serializer();

 static File sdcard;
 static File dir;
 static String filename;

 public static Articles articles;

 public static Context APPLICATION_CONTEXT;
 public static String PACKAGE_NAME;
 public static Activity ACTIVITY;
 public static Resources res;

 public static Tts tts;
 private static Article currArticle;

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
		init();
 }

 private void init() {
	tabs = getResources().getStringArray(R.array.tabs);
	APPLICATION_CONTEXT = getApplicationContext();
	 sdcard = APPLICATION_CONTEXT.getExternalFilesDir(null);
	 dir =	new File(sdcard.getAbsolutePath(), "/db");
	 if(!dir.exists())dir.mkdirs();
	 filename = dir.getAbsolutePath() + "/constitution.json";
	 ACTIVITY = this;
	res = getResources();
	PACKAGE_NAME = getClass().getPackage().getName();
	setupToolbar();
	setupTabbar();
	setupPager();
	setupFab();
	retrieve();
	init_tts();
 }

 public static void setCurrentArticle(Article art) {
	currArticle = art;
	ACTIVITY.invalidateOptionsMenu();
 }

 public static Article getCurrentArticle() {
	return currArticle;
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
	tabbar.setTabGravity(TabLayout.GRAVITY_CENTER);
	tabbar.setHorizontalScrollBarEnabled(true);
	tabbar.setTabMode(TabLayout.MODE_SCROLLABLE);
	for (int i = 0; i < tabs.length; i++) {
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
	//fab.setVisibility(View.INVISIBLE);
 }

 @Override
 public void onClick(View v) {
switch(v.getId()){
 case R.id.fab:
	showBottomView();
	break;
}
 }
 /**
	* start reading text aloud
	*/
 public static void speak_tts(String txt) {
	if (txt.length() > 0) {
	 showSnack("Lecture de l'article");
	 tts.read(txt);
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
	shutDown_tts();
 }

 @Override
 public void onBackPressed() {
	// TODO: Implement this method
	super.onBackPressed();
	shutDown_tts();
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
 public boolean onPrepareOptionsMenu(Menu menu) {
	// TODO: Implement this method
	MenuItem mi = menu.findItem(R.id.mm_bookmark);
	if (currArticle.bookmark) {
	 mi.setIcon(APPLICATION_CONTEXT.getResources().getDrawable( R.drawable.ic_bookmark_check));
	} else {
	 mi.setIcon(APPLICATION_CONTEXT.getResources().getDrawable( R.drawable.ic_bookmark_outline));
	}
	
	
	return true;
 }
 
 

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	// TODO: Implement this method
	switch (item.getItemId()) {
	 case R.id.mm_share:
		share();
		break;
	 case R.id.mm_read:
		if (!tts.isReading()) {
		 speak_tts(currArticle.content);
		} else {
		 stop_tts();
		}
		break;
	 case R.id.mm_bookmark:
		bookmark();
		break;
	 case R.id.mm_quit:
		store();
		finish();
		break;
		case R.id.mm_help:
		 // help info intent on how to use the app
		 showSnack("will be available soon");
		 break;
		 case R.id.mm_sync:
			// sync with a database file from the net
			// download a file from the net and copy the content
			// to local file.
			showSnack("will be available soon.");
			break;
	 case R.id.mm_about:
		startActivity(new Intent(getApplicationContext(), About.class));
		break;
	}
	return super.onOptionsItemSelected(item);
 }

 public void share() {
	if (currArticle != null) {
	 ShareCompat.IntentBuilder
		.from((Activity)this)
		.setText(currArticle.toDetailedString())
		.setType("text/plain")
		.setChooserTitle(currArticle.toString())
		.startChooser();
	}
	showSnack("Partage effective");
 }

 private static void store() {
	showSnack("Mise à jour de la base des données en cours...");
	serializer.serialize(filename, articles, Articles.class);
	showSnack(/*"Mise à jour terminée at : " + */filename);
 }

 private static void retrieve() {
	
	Articles artExt = serializer.deserialize(filename, Articles.class);
	Articles artInt = GsonUtils.getInstance().deserializeInternal(res, R.raw.constitution, Articles.class);
	if(artExt == null){
	 articles = artInt;
	}else{
	 if(artInt.versionId > artExt.versionId){
		articles = artInt;
	 }else{
		articles = artExt;
	 }
	}
	
	currArticle = articles.list.get(0);
 }

 private static void showSnack(String msg) {
	Snackbar.make(ACTIVITY.findViewById(R.id.coordinatorLayout),
								msg,
								Snackbar.LENGTH_SHORT)
	 .show();
 }
 
 private void showBottomView() {
	PopupWindow popWindow = new PopupWindow();
	View view = getLayoutInflater().inflate(R.layout.bottom_view, pager, false);
	popWindow.setContentView(view);
	popWindow.setFocusable(true);
	popWindow.setHeight(200);
	popWindow.setWidth(400);
 }
 
 public static void refreshItemBGColor(AdapterView<?> p1, int p3) {
	View v = p1.getChildAt(p3);
	v.setBackgroundResource(R.drawable.list_view_d);
	for (int i = 0; i < p1.getChildCount(); i++) {
	 if(i != p3){
		p1.getChildAt(i).setBackgroundColor(R.color.window_background);
	 }
	}

 }
 
 public static void bookmark(){
	if (currArticle.bookmark) {
	 currArticle.bookmark = false;
	} else {
	 currArticle.bookmark = true;
	}
	store();
ACTIVITY.	invalidateOptionsMenu();
 }
}
