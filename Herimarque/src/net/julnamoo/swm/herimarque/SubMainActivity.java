package net.julnamoo.swm.herimarque;

import net.julnamoo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SubMainActivity extends Activity {

	View info, create, show, config;
	RadioGroup menuG;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submain);
		
		info = (View) findViewById(R.id.info);
		create = (View) findViewById(R.id.create);
		show = (View) findViewById(R.id.show);
		config = (View) findViewById(R.id.config);
		
		menuG = (RadioGroup) findViewById(R.id.gmenu);
		
		findViewById(R.id.infoButt).setOnClickListener(flipper);
		findViewById(R.id.createButt).setOnClickListener(flipper);
		findViewById(R.id.showButt).setOnClickListener(flipper);
		findViewById(R.id.configButt).setOnClickListener(flipper);
		
		Intent intent = getIntent();
		int menu = intent.getIntExtra("menu", 0);
		
		changeView(menu);
	}
	
	OnClickListener flipper = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.infoButt:
				changeView(0);
				break;
			case R.id.createButt:
				changeView(1);
				break;
			case R.id.showButt: 
				changeView(2);
				break;
			case R.id.configButt: 
				changeView(3);
				break;
			default:
				break;
			}
		}
	};
	
	private void changeView(int id)
	{
		info.setVisibility(View.INVISIBLE);
		create.setVisibility(View.INVISIBLE);
		show.setVisibility(View.INVISIBLE);
		config.setVisibility(View.INVISIBLE);

		switch (id) {
		case 0:
			info.setVisibility(View.VISIBLE);
			menuG.check(R.id.infoButt);
			break;
		case 1:
			create.setVisibility(View.VISIBLE);
			menuG.check(R.id.createButt);
			break;
		case 2:
			show.setVisibility(View.VISIBLE);
			menuG.check(R.id.showButt);
			break;
		case 3:
			config.setVisibility(View.VISIBLE);
			menuG.check(R.id.configButt);
			break;
		default:
			break;
		}
	}
}
