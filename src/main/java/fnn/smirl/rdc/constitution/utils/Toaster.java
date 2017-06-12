package fnn.smirl.rdc.constitution.utils;
import android.widget.Toast;
import android.content.Context;

public class Toaster
{
 private static Context ctx;
 private static Toaster t = new Toaster();
 public static Toaster init(Context ctx){
	t.ctx = ctx;
	return t;
	}
 
 public static void toast(String msg){
	toast(msg, Toast.LENGTH_SHORT);
 }
 public static void toast(String msg, int duration){
	Toast.makeText(ctx, msg, duration).show();
 }
}
