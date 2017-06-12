package fnn.smirl.rdc.constitution.utils;
import android.content.res.*;
import android.app.*;
import android.content.*;
import fnn.smirl.rdc.constitution.*;

public class Harmony
	{
	 
		private static String[] symb = {"a", "e", "i", "o", "u", "c", "n"};
		
		public static String harmonize(Context context, String sequence)
			{
			 
			 
				Resources res = context.getResources();
				for (int dd = 0; dd < symb.length; dd++)
					{
						int ff = res.getIdentifier(symb[dd], "array", MaConstitution.PACKAGE_NAME);
						TypedArray a = res.obtainTypedArray(ff);
						for (int ii = 0; ii < a.length(); ii++)
							{
							 
								sequence = sequence.replace(a.getString(ii), symb[dd]);
							}
					}
			 
				return sequence;
			}
	}
