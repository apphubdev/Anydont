package com.androidacademy.anydont;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LoggedInActivity extends Activity {

	private ListView list;
	private ArrayList<Model> modelsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged_in);
		Intent intent = getIntent();
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null) {
				String data = extras.getString(SignInActivity.PREF_EMAIL);
				TextView email = (TextView) findViewById(R.id.textView1);
				if (!TextUtils.isEmpty(data)) {
					email.setText(data);
				}
			}
		}

		list = (ListView) findViewById(R.id.listView1);

		Button b = (Button) findViewById(R.id.button1);
//		b.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (list.getVisibility() == View.INVISIBLE) {
//					list.setVisibility(View.VISIBLE);
//				} else {
//					list.setVisibility(View.INVISIBLE);
//				}
//			}
//		});

		initData();
		list.setAdapter(new MyAdapter());
	}

	private void initData() {
		modelsList = new ArrayList<Model>();
		modelsList.add(new Model("title1", "subtitle1"));
		modelsList.add(new Model("title2", "subtitle2"));
		modelsList.add(new Model("title3", "subtitle3"));
		modelsList.add(new Model("title4", "subtitle4"));
		modelsList.add(new Model("title5", "subtitle5"));
	}

	class Model {
		String title;
		String subTitle;

		public Model(String title, String subTitle) {
			this.title = title;
			this.subTitle = subTitle;
		}
	}

	class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflator;

		@Override
		public int getCount() {
			return modelsList.size();
		}

		@Override
		public Object getItem(int position) {
			return modelsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return modelsList.get(position).hashCode();
		}

		class ViewHolder {
			TextView title;
			TextView subTitle;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			ViewHolder holder = null;
			if (null == v) {
				if (mInflator == null) {
					mInflator = LayoutInflater.from(LoggedInActivity.this);
				}
				v = mInflator.inflate(R.layout.list_row, parent, false);
				holder = new ViewHolder();
				holder.title = (TextView) v.findViewById(R.id.title);
				holder.subTitle = (TextView) v.findViewById(R.id.sub);
				v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
			}
			Model model = modelsList.get(position);
			holder.title.setText(model.title);
			holder.subTitle.setText(model.subTitle);

			return v;
		}

	}
}
