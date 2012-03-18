package net.julnamoo.swm.herimarque.info;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.DetailFragment;
import net.julnamoo.swm.herimarque.adapter.HeritageListAdapter;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.model.Item;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.CursorToItem;
import net.julnamoo.swm.herimarque.view.SearchBar;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SearchResultFragment extends Fragment {

	private String tag = SearchResultFragment.class.getSimpleName();
	private Context mContext;
	private SearchBar searchBar;
	private String query;
	private HeritageSQLiteHelper sqlHelper;
	private Cursor cursor;
	private HeritageListAdapter adapter;

	public SearchResultFragment(Context mContext, String query) 
	{
		this.mContext = mContext;
		this.query = query;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setCursor(query);

		if(savedInstanceState != null)
		{
			query = savedInstanceState.getString("query");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle arg2) 
	{
		//set basic list view
		LinearLayout v = (LinearLayout) inflater.inflate(R.layout.list, parent, false);
		ListView list = (ListView) v.findViewById(R.id.list);
//		HeritageListAdapter adapter = new HeritageListAdapter(inflater.getContext(), cursor);
		adapter = new HeritageListAdapter(inflater.getContext(), cursor);
		list.setAdapter(adapter);
		list.setOnItemClickListener(itemClickListener);
		list.setTextFilterEnabled(true);

		//set queryString
		searchBar = (SearchBar) v.findViewById(R.id.searchbar);
		EditText queryText = (EditText) searchBar.findViewById(R.id.query);
		queryText.setOnKeyListener(onKeyListener);
		queryText.setHint(query);
		sqlHelper.close();
		return v;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putString("query", query);
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() 
	{
		public void onItemClick(android.widget.AdapterView<?> arg0, View view, int selected, long id) {
			Log.d(tag, "search list, selected : " + selected);

			Item item = CursorToItem.cursor2Item((Cursor) arg0.getItemAtPosition(selected));
			Fragment f = new DetailFragment(item, mContext);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.info_main, f, "info");
			ft.addToBackStack("info");
			ft.commit();
		};
	};

	/**
	 * execute the query get from the savedInstance,
	 * and execute the query then get the cursor of sqlite
	 */
	private void setCursor(String query)
	{
		sqlHelper = new HeritageSQLiteHelper(mContext); 
		SQLiteDatabase db = sqlHelper.getReadableDatabase();
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
		queryBuilder.append(Constants.TABLE_NAME).append(" WHERE ( crltsDc like '%").append(query).append("%');");
		String execSQL = queryBuilder.toString();
		cursor = db.rawQuery(execSQL, null);
		Log.d(tag, "setCursor : " + execSQL);
	}

	public void onPause() 
	{
		super.onPause();
	}
	
	OnKeyListener onKeyListener = new OnKeyListener() {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
			{
				String queryText = searchBar.getQueryString();
				query = queryText;
				sqlHelper = new HeritageSQLiteHelper(mContext); 
				SQLiteDatabase db = sqlHelper.getReadableDatabase();
				StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
				queryBuilder.append(Constants.TABLE_NAME).append(" WHERE ( crltsDc like '%").append(query).append("%');");
				String execSQL = queryBuilder.toString();
				Cursor cursor2 = db.rawQuery(execSQL, null);
				
				ListView l = (ListView) getView().findViewById(R.id.list);
				adapter = new HeritageListAdapter(mContext, cursor2);
				l.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				Log.d(tag, "setCursor2 : " + execSQL);
				
				return true;
			}
			return false;
		}
		
	};
}
