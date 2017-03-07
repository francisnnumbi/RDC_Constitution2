package fnn.smirl.rdc.constitution.adapter;
import android.support.v4.app.*;
import fnn.smirl.rdc.constitution.fragment.*;


public class TabsPagerAdapter  extends FragmentPagerAdapter {
 String[] titles;
 @Override
 public Fragment getItem(int p1) {
	// TODO: Implement this method
	switch (p1) {
	 case 0:
		return new Portail();
	 case 1:
		return new MyArticles();
	 case 2:
		return new Recherche();
	 case 3:
		return new Hymne();
	 case 4:
		return new Signet();
	}
	return null;
 }

 @Override
 public CharSequence getPageTitle(int position) {
	// TODO: Implement this method

	switch (position) {
	 case 0:
		return titles[0];
	 case 1:
		return titles[1];
	 case 2:
		return titles[2];
	 case 3:
		return	 titles[3];
	 case 4:
		return titles[4];
	}
	return null;
 }

 @Override
 public int getCount() {
	// TODO: Implement this method
	return titles.length;
 }

 public TabsPagerAdapter(FragmentManager fm, String[] titles) {
	super(fm);
	this.titles = titles;
 }
}
