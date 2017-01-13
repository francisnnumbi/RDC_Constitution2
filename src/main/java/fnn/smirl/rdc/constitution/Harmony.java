package fnn.smirl.rdc.constitution;
import android.content.res.*;
import android.app.*;
import android.content.*;

public class Harmony
	{
		private static String[] symb = {"a", "e", "i", "o", "u", "c", "n"};
		
		public static String harmonize(Context context, String sequence)
			{
				Resources res = context.getResources();
				for (int dd = 0; dd < symb.length; dd++)
					{
						int ff = res.getIdentifier(symb[dd], "array", (new Harmony()).getClass().getPackage().getName());
						TypedArray a = res.obtainTypedArray(ff);
						for (int ii = 0; ii < a.length(); ii++)
							{
								sequence = sequence.replace(a.getString(ii), symb[dd]);
							}
					}
				return sequence;
			}
	}
