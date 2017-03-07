package fnn.smirl.rdc.constitution.utils;
import android.widget.Toast;
import android.content.Context;

public class Toaster
{
 private static Context ctx;
 public static void init(Context ctx){Toaster.ctx = ctx;}
 
 public static void toast(String msg){
	toast(msg, Toast.LENGTH_SHORT);
 }
 public static void toast(String msg, int duration){
	Toast.makeText(ctx, msg, duration).show();
 }
}
