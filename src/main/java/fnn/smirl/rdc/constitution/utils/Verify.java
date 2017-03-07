package fnn.smirl.rdc.constitution.utils;
import android.content.*;
import android.content.res.*;

public class Verify
{
	private final String RESOURCE_TYPE = "array";
	Context context;
	Resources res;
	public Verify(Context context){
		this.context = context;
		res = context.getResources();
	}
	
	public boolean isIn(String value, String key){
			int ff = res.getIdentifier(key, RESOURCE_TYPE, this.getClass().getPackage().getName());
			TypedArray a = res.obtainTypedArray(ff);
			for (int ii = 0; ii < a.length(); ii++)
				{
					if(a.getString(ii).equalsIgnoreCase(value)){
						return true;
					}
				}
			
		return false;
	}
	
	// inner class Article.class
	protected class Article{
		private String value;
		protected Article(String key){
			
		}
		
		protected boolean isItemOf(Paragraphe paragraphe){
			
			return false;
		}
	}
	
		// inner class Article.class
		protected class Paragraphe{
				private String value;
				protected Paragraphe(String key){

					}
			}
	
}
