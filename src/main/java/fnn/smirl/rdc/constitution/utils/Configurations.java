package fnn.smirl.rdc.constitution.utils;
import android.content.*;
import java.util.*;

public class Configurations
{
  SharedPreferences prefs;
 SharedPreferences.Editor editor;

 public Configurations(Context ctx, String pkgDir){
	prefs = ctx.getSharedPreferences(pkgDir, 0);
	editor = prefs.edit();
 }
 
 public Configurations(Context ctx){
	
	this(ctx, "fnn_smirl_rdc_constitution");
 }
 
 public boolean remove(String key){
	editor.remove(key);
	return editor.commit();
 }
 
 public boolean removeAll(){
	editor.clear();
	return editor.commit();
 }
 
 public Object retrieve(String key, Object defaultValue){
	switch(defaultValue.getClass().getName()){
	 case "String":
	return prefs.getString(key, (String)defaultValue);
	case "Integer":
		return prefs.getInt(key, (Integer)defaultValue);
		case "Long":
		return prefs.getLong(key, (Long)defaultValue);
		case "Float":
		return prefs.getFloat(key, (Float)defaultValue);
		case "Boolean":
		return prefs.getBoolean(key, (Boolean)defaultValue);
	}
	return null;
 }
 
 public Set<String> retrieveSet(String key, Set<String> set){
	return prefs.getStringSet(key, set);
 }

 public boolean store(String key, Object value){
	switch	(value.getClass().getName()){
	 case "String":
		editor.putString(key, (String)value);
		return editor.commit();
	 case "Integer":
		editor.putInt(key, (Integer)value);
		return editor.commit();
	 case "Long":
		editor.putLong(key, (Long)value);
		return editor.commit();
	 case "Float":
		editor.putFloat(key, (Float)value);
		return editor.commit();
	 case "Boolean":
		editor.putBoolean(key, (Boolean)value);
		return editor.commit();
	}
	return false;
 }

 public void storeSet(String key, Set<String> set){
	editor.putStringSet(key, set);
	editor.commit();
 }
 
 public Map<String, ?> retrieveAll(){
	return prefs.getAll();
 }
}
