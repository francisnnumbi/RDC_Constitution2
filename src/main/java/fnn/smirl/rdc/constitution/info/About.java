package fnn.smirl.rdc.constitution.info;
import android.app.Dialog;
import android.app.Activity;
import android.os.Bundle;
import fnn.smirl.rdc.constitution.R;
import android.widget.TextView;

public class About extends Activity
{
 
 TextView about_version_name,
 about_version_code;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
	// TODO: Implement this method
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
	
	about_version_name = (TextView)findViewById(R.id.about_version_name);
	String vn = AppInfo.getInstance(getApplicationContext())
	.getAppVersionName();
	about_version_name.setText("Version " + vn);
	
	about_version_code = (TextView)findViewById(R.id.about_version_code);
	int vc = AppInfo.getInstance(getApplicationContext())
	 .getAppVersionCode();
	about_version_code.setText("Build " + vc);
	
	}
 
 
}
