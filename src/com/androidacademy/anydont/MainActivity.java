package com.androidacademy.anydont;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private Button mButton;
	private Context mContext;
	private String[] strArr = { "First", "Second", "Third", "First", "Second",
			"Third", "First", "Second", "Third", "First", "Second", "Third" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* init a list view */

		setContentView(R.layout.activity_main);
		// init context var
		mContext = this;

		ListView mlistView = (ListView) findViewById(R.id.listView1);
		// Set custom var
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, strArr);
		// Connect adapter to listView
		mlistView.setAdapter(mAdapter);

		/* init button */
		mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// Check if the button that was pressed is button1. if yes go to second
		// activity
		if (v.getId() == R.id.button1) {
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);

		}

	}

}
