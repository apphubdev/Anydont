package com.androidacademy.anydont;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {

	private Button mButton;
	private EditText eText;
	static String userInput = "User's Input";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		/* init button */
		mButton = (Button) findViewById(R.id.secondButton);
		mButton.setText("Click Here");
		eText = (EditText) findViewById(R.id.editText1);
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		// Check if the button that was pressed is secondButton. if yes go to main activity
		if (v.getId() == R.id.secondButton) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(userInput, eText.getText().toString()); // add the edit text to the intent
			Toast.makeText(this, eText.toString() + "was added", Toast.LENGTH_LONG).show();
			startActivity(intent); // start main activity

		}

	}
}
