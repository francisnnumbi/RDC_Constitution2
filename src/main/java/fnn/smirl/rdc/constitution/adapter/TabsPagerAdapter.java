package fnn.smirl.rdc.constitution.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import fnn.smirl.rdc.constitution.Articles;
import fnn.smirl.rdc.constitution.Hymne;
import fnn.smirl.rdc.constitution.Portail;
import fnn.smirl.rdc.constitution.Recherche;
import fnn.smirl.rdc.constitution.Signet;

public class TabsPagerAdapter  extends FragmentPagerAdapter
{

 @Override
 public Fragment getItem(int p1)
 {
	// TODO: Implement this method
	switch (p1){
	 case 0:
		return new Portail();
	 case 1:
		return new Articles();
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
 public int getCount()
 {
	// TODO: Implement this method
	return 5;
 }

 public TabsPagerAdapter(FragmentManager fm){
	super(fm);
 }
}
