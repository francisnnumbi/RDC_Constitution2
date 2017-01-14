package fnn.smirl.rdc.constitution;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import fnn.smirl.rdc.constitution.MaConstitution;
import fnn.smirl.rdc.constitution.adapter.TabsPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import fnn.smirl.rdc.constitution.info.About;
import fnn.smirl.rdc.constitution.info.AppInfo;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.app.Activity;

public class MaConstitution extends FragmentActivity
implements ActionBar.TabListener
{
 
 public static Constitution SHARABLE_CONSTITUTION 
 = new Constitution("", "");

 public static Context APPLICATION_CONTEXT;

 public static Tts tts;

 private ViewPager viewPager;
 private TabsPagerAdapter tabsPagerAdapter;
 private ActionBar actionBar;

 private String[] tabs = {"Portail", "Articles", "Recherche", "Hymne", "Signet"};

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.maconstitution);
	APPLICATION_CONTEXT = getApplicationContext();
	init_tts();
	viewPager = (ViewPager)findViewById(R.id.pager);
	actionBar = getActionBar();
	actionBar.setSubtitle(
	AppInfo.getInstance(getApplicationContext()).toString());
	tabsPagerAdapter = new
	 TabsPagerAdapter(getSupportFragmentManager());
	viewPager.setAdapter(tabsPagerAdapter);
	actionBar.setHomeButtonEnabled(false);
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	for (String tab_name : tabs){
	 actionBar.addTab(actionBar.newTab()
										.setText(tab_name).setTabListener(this));
	}
	viewPager.setOnPageChangeListener(new 
	 ViewPager.OnPageChangeListener(){

		@Override
		public void onPageScrolled(int p1, float p2, int p3)
		{
		 // TODO: Implement this method
		}

		@Override
		public void onPageSelected(int p1)
		{
		 // TODO: Implement this method
		 actionBar.setSelectedNavigationItem(p1);
		}

		@Override
		public void onPageScrollStateChanged(int p1)
		{
		 // TODO: Implement this method
		}

	 });

	 
 }

 @Override
 public void onTabSelected(ActionBar.Tab p1, FragmentTransaction p2)
 {
	// TODO: Implement this method
	viewPager.setCurrentItem(p1.getPosition());
 }

 @Override
 public void onTabUnselected(ActionBar.Tab p1, FragmentTransaction p2)
 {
	// TODO: Implement this method
	stop_tts();
 }

 @Override
 public void onTabReselected(ActionBar.Tab p1, FragmentTransaction p2)
 {
	// TODO: Implement this method
 }

 /**
	* start reading text aloud
	*/
 public static void speak_tts(String txt)
 {
	if (txt.length() > 0){
	 Toast.makeText(MaConstitution.APPLICATION_CONTEXT, "Lecture de l'article", Toast.LENGTH_SHORT)
		.show();
	 tts.speak(txt);
	}
 }

 @Override
 protected void onStop()
 {
	// TODO: Implement this method
	super.onStop();
	stop_tts();
	
 }

 @Override
 protected void onDestroy()
 {
	// TODO: Implement this method
	super.onDestroy();
	shutDown_tts();
 }

 @Override
 public void onBackPressed()
 {
	// TODO: Implement this method
	super.onBackPressed();
	shutDown_tts();
 }
 
 /**
	* stop reading text aloud
	*/
 public static void stop_tts(){
	tts.stop();
 }

 /**
	* close TTS
	*/
 public static void shutDown_tts(){
	tts.shutDown();
 }

 private void init_tts(){
	tts = new Tts(MaConstitution.APPLICATION_CONTEXT);
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu)
 {
	// TODO: Implement this method
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.main_menu, menu);
	return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item)
 {
	// TODO: Implement this method
	switch(item.getItemId()){
	 case R.id.mm_share:
		share();
		break;
	 case R.id.mm_about:
		startActivity(new Intent(getApplicationContext(), About.class));
		break;
	}
	return super.onOptionsItemSelected(item);
 }
 
  public void share()
 {
if(SHARABLE_CONSTITUTION != null){
	 ShareCompat.IntentBuilder
	 .from((Activity)this)
	 .setText(SHARABLE_CONSTITUTION.toDetailedString())
	 .setType("text/plain")
	 .setChooserTitle(SHARABLE_CONSTITUTION.getArticle())
	.startChooser();
	 }
	
	 Toast
		.makeText(MaConstitution.APPLICATION_CONTEXT, 
		"Partage effective", Toast.LENGTH_SHORT)
		.show();

	}
 
}
