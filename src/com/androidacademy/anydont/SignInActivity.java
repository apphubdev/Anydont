package com.androidacademy.anydont;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends Activity {

	private static final String PREFS_FILE_NAME = "demo3";
	public static final String PREF_EMAIL = "pref_email";
	public static final String PREF_PASSWORD = "pref_password";

	private EditText email;
	private EditText password;
	private SharedPreferences mPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		email = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);

		Button submit = (Button) findViewById(R.id.button1);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Editor editor = mPref.edit();
				editor.putString(PREF_EMAIL, email.getText().toString());
				editor.putString(PREF_PASSWORD, password.getText().toString());
				editor.commit();

				// Verify email password
				// move to next activity
				String email = mPref.getString(PREF_EMAIL, "");
				String pass = mPref.getString(PREF_PASSWORD, "");
				if (email.equalsIgnoreCase("eyalbe@gmail.com")
						&& pass.equalsIgnoreCase("12345")) {
					goToNextActivity(email);
				} else {
					editor.putString(PREF_EMAIL, "");
					editor.putString(PREF_PASSWORD, "");
					Toast.makeText(getApplicationContext(), "wrong!",
							Toast.LENGTH_SHORT).show();
					editor.commit();
				}
			}
		});

		mPref = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
		String email = mPref.getString(PREF_EMAIL, "");
		String pass = mPref.getString(PREF_PASSWORD, "");
		if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
			if (email.equalsIgnoreCase("eyalbe@gmail.com")
					&& pass.equalsIgnoreCase("12345")) {
				goToNextActivity(email);
			} else {
				Editor editor = mPref.edit();
				editor.putString(PREF_EMAIL, "");
				editor.putString(PREF_PASSWORD, "");
				Toast.makeText(this, "wrong!", Toast.LENGTH_SHORT).show();
				editor.commit();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	private void goToNextActivity(String email) {
		Intent intent = new Intent(this, LoggedInActivity.class);
		Bundle extras = new Bundle();
		extras.putString(PREF_EMAIL, email);
		intent.putExtras(extras);
		startActivity(intent);
	}
}
