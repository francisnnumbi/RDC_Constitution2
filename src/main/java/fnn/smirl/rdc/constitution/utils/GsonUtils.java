package fnn.smirl.rdc.constitution.utils;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import android.content.res.*;

/**
 * Simplified serialization and deserialization of file using Gson API
 *
 * @author Francis Nduba Numbi
 * @version 1.1
 * @since 2017
 */
public class GsonUtils{

	private Gson gson;
	private static GsonUtils gu = new GsonUtils();

	/**
	 * Default constructor
	 */
	private GsonUtils() {
		gson = new GsonBuilder()
		.enableComplexMapKeySerialization()
		.serializeNulls()
		.setPrettyPrinting().create();
	}

	public static GsonUtils getInstance(){
		return gu;
	}

	public <T> T fromJson(String objectTString, Class<T> classOfT) {
		return gson.fromJson(objectTString, classOfT);
	}


	public <T> T deserializeInternal(Resources res, int resId, Class<T> classOfT) {
		try {
			InputStream inputStream = res.openRawResource(resId);
			try {
				BufferedReader reader =
				new BufferedReader(new InputStreamReader(inputStream));
				return gson.fromJson(reader, classOfT);
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			// process exception
		}
		return null;
	}
}

