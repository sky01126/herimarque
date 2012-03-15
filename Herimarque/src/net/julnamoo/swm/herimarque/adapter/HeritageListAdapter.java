package net.julnamoo.swm.herimarque.adapter;

import net.julnamoo.R;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeritageListAdapter extends CursorAdapter {

	Context mContext;

	LayoutInflater inflater;
	private Cursor cursor;

	
	public HeritageListAdapter(Context context, Cursor cursor) {

		super(context, cursor, true);
		this.mContext = context;
		this.cursor = cursor;
	}
	
	@Override
	public void bindView(View view, Context arg1, Cursor arg2) 
	{
		TextView target_name = (TextView) view.findViewById(R.id.text1);
		String name = cursor.getString(getCursor().getColumnIndex("crltsNm"));
		target_name.setText(name);
		
		TextView target_code = (TextView) view.findViewById(R.id.text2);		
		String codeNum = cursor.getString(getCursor().getColumnIndex("crltsNoNm")); 
		StringBuilder sb = new StringBuilder();
		sb.append(codeNum).append("호");
		String code = sb.toString();
		target_code.setText(code);
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) 
	{
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.list_item_2, arg2, false);
		return view;
	}
}
