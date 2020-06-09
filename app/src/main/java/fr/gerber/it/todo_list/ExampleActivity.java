package fr.gerber.it.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ExampleActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		Intent intent = getIntent();
		String action = intent.getAction();
		Uri data = intent.getData();

		Log.d("Main", "action : " + action);
		if(data!=null){

			Log.d("Main", "data : " + data.getPath());
		}

		setContentView(R.layout.activity_example);


	}


}
