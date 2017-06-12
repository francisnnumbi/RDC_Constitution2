package fnn.smirl.rdc.constitution.adapter;
import android.view.*;
import android.widget.*;

import android.content.Context;
import android.view.View.OnLongClickListener;
import fnn.smirl.rdc.constitution.R;
import fnn.smirl.rdc.constitution.utils.core.Article;
import java.util.ArrayList;
import fnn.smirl.rdc.constitution.fragment.MyArticles;
import android.view.View.OnClickListener;
import fnn.smirl.rdc.constitution.MaConstitution;

public class MyListAdapter extends ArrayAdapter<Article> {
 Context ctx;

 public MyListAdapter(Context ctx, ArrayList<Article> list) {
	super(ctx, 0, list);
	this.ctx = ctx;
	setNotifyOnChange(true);
 }

 @Override
 public View getView(final int position, View convertView, ViewGroup parent) {
	// TODO: Implement this method
	final Article article = getItem(position);
	if (convertView == null) {
	 convertView = LayoutInflater.from(ctx)
		.inflate(R.layout.tamplate, parent, false);
	}
	TextView tv = (TextView)convertView.findViewById(R.id.item1);
	tv.setText(article.toString());

	convertView.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View p1) {
		 // TODO: Implement this method
		 MyArticles.display(article);
	
		}

	 
	});

	convertView.setOnLongClickListener(new OnLongClickListener(){

		@Override
		public boolean onLongClick(View p1) {
		 //TODO: Implement this method
		 PopupMenu popup = new PopupMenu(ctx, p1);
		 popup.inflate(R.menu.article_pop_menu);
		 popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

			 @Override
			 public boolean onMenuItemClick(MenuItem p1) {
				// TODO: Implement this method
				switch (p1.getItemId()) {
				 case R.id.a_p_show:
					MyArticles.display(article);
					break;
				 case R.id.a_p_bookmark:
					MaConstitution.bookmark();
					break;
				 case R.id.a_p_note:

					break;

				}
				return false;
			 }
			});

		 popup.show();
		 return false;
		}

	 });
	 
	 convertView.setBackgroundResource(R.drawable.list_view_d_selector);
	
	return convertView;
 }

}
