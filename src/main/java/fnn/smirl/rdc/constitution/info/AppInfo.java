package fnn.smirl.rdc.constitution.info;
import android.content.Context;
import android.content.pm.PackageInfo;

/** by Francis Nduba Numbi */
public final class AppInfo
{
 private static String vn= ""; // version name
 private static int vc = 0; // version code
 private static final AppInfo INSTANCE = new AppInfo();

 private AppInfo(){} // private not instantiable

 /** eg. AppInfo.getInstance(getApplicationContext()); */
 public static AppInfo getInstance(Context ctx){
	PackageInfo p = getPackageInfo(ctx);
	INSTANCE.vn = p.versionName;
	INSTANCE.vc = p.versionCode;
	return INSTANCE;
 }

 @Override
 public String toString(){return vn + vc;}

 public static String getAppVersionName(){return vn;}

 public static int getAppVersionCode(){return vc;}

 private static PackageInfo getPackageInfo(Context ctx){
	PackageInfo p = null;
	try{
	 p = ctx.getPackageManager()
		.getPackageInfo(ctx.getPackageName(), 0);
	}catch (Exception e){}
	return p;
 }
}
