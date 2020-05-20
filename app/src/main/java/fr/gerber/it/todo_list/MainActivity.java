package fr.gerber.it.todo_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
	private ArrayList<String> items;
	private ArrayAdapter<String> itemsAdapter;
	private ListView lvItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvItems = (ListView) findViewById(R.id.lvItems);
		items = new ArrayList<String>();
		readItems(); // <---- Add this line

		itemsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);

		setupListViewListener();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onAddItem(View v) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		String itemText = etNewItem.getText().toString();
		itemsAdapter.add(itemText);
		etNewItem.setText("");
		writeItems();
	}

	// Attaches a long click listener to the listview
	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(
				new AdapterView.OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View item, int pos, long id) {
						// Remove the item within array at position
						items.remove(pos);
						// Refresh the adapter
						itemsAdapter.notifyDataSetChanged();
						// Return true consumes the long click event (marks it handled)
						writeItems();
						return true;
					}

				});
	}

	private void readItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			items.clear();
			items.addAll(new ArrayList<String>(FileUtils.readLines(todoFile)));
		} catch (IOException e) {
			items.clear();
		}
	}

	private void writeItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
