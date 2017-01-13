package fnn.smirl.rdc.constitution;
import android.content.*;
import android.app.*;

public class Sharing
{
	
	
	public static boolean share(Activity context, Constitution constitution){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		String text = constitution.toDetailedString();
		
		intent.putExtra(Intent.EXTRA_SUBJECT, constitution.getArticle());
		intent.putExtra(Intent.EXTRA_TEXT, text);
		
		context.startActivity(
		Intent.createChooser(intent, context.getResources().getString(R.string.sharing_statement)));
		return true;
	}
}
