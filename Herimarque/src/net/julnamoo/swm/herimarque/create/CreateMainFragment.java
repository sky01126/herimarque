package net.julnamoo.swm.herimarque.create;

import java.util.List;

import net.julnamoo.R;
import net.julnamoo.swm.herimarque.SubMainActivity;
import net.julnamoo.swm.herimarque.common.TurnOnGPSFragment;
import net.julnamoo.swm.herimarque.db.HeritageSQLiteHelper;
import net.julnamoo.swm.herimarque.info.LoadingFragment;
import net.julnamoo.swm.herimarque.util.Constants;
import net.julnamoo.swm.herimarque.util.MapContainer;
import net.julnamoo.swm.herimarque.view.HeritageItemizedOverlay;
import net.julnamoo.swm.herimarque.view.LocationItemizedOverlay;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CreateMainFragment extends Fragment implements LocationListener, OnTouchListener{

	private String tag = CreateMainFragment.class.getSimpleName();

	private Context mContext;
	private MapView mapView;
	private MapController mapController;
	private List<Overlay> mapOverlay;
	private HeritageItemizedOverlay heritageOverlay;
	private LocationItemizedOverlay locationOverlay;

	//for update location
	private int minTime = 5; //default value
	private int minDistance = 5; //default value
	private LocationManager locationManager;

	//for detecting event
	private int currZoom;
	private GeoPoint currStart;
	private ImageView progress;

	private boolean turnOnHeritage; 
	private TurnOnGPSFragment gpsStarter;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		mContext = getActivity();

		//set locationmanger
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, this);
		//set overlay
		heritageOverlay = new HeritageItemizedOverlay(getResources().getDrawable(R.drawable.pin2), mContext, getFragmentManager(), 1);
		locationOverlay = new LocationItemizedOverlay(getResources().getDrawable(R.drawable.pin1), mContext);
		//set gps switcher
		gpsStarter = new TurnOnGPSFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(tag, "create CreateFragment view");
		mapView = MapContainer.mapView;

		//set mapview parent
		ViewGroup vg = (ViewGroup) mapView.getParent();
		if(vg != null)
		{
			vg.removeView(mapView);
		}

		//get heritages
		currStart = mapView.getProjection().fromPixels(0, 0);
		new LoadMapItems().execute();

		mapView.setOnTouchListener(this);
		mapView.setBuiltInZoomControls(false);
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapController = mapView.getController();
		mapOverlay = mapView.getOverlays();

		//init mapview
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(location == null)
		{
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(location == null)
			{
				location = new Location(LocationManager.NETWORK_PROVIDER);
				location.setLatitude(Double.valueOf("37.57273"));
				location.setLongitude(Double.valueOf("126.9687"));
			}
		}
		Log.d(tag, "last location :" + location.getLatitude() + ","+location.getLongitude());
		Double lat = location.getLatitude() * 1E6;
		Double lng = location.getLongitude() * 1E6;
		GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
		mapController.setZoom(18);
		mapController.animateTo(point);
		currZoom = 18;
		setMyLocationOverlay(point);

		//add the Button for moving to the current location
		Button getMyLoc = new Button(mContext);
		getMyLoc.setBackgroundDrawable(getResources().getDrawable(R.drawable.myloc));
		LayoutParams sizeParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.LEFT | Gravity.TOP);
		getMyLoc.setLayoutParams(sizeParam);
		getMyLoc.setOnClickListener(myLocButtonListener);

		mapView.addView(getMyLoc);
		mapView.invalidate();
		
		//add the start button
		
		//add near heritage switch button
		
		return mapView;
	}

	@Override
	public void onPause() 
	{
		locationManager.removeUpdates(this);
		Log.d(tag, "Remove all listener");
		super.onPause();
	}

	@Override
	public void onResume() 
	{
		Log.d(tag, "Resume location listener");
		List<String> providers = locationManager.getAllProviders();
		for(String provider : providers)
		{
			Log.d(tag, "Enroll the provider " + provider);
			locationManager.requestLocationUpdates(provider, minTime, minDistance, this);
		}
		super.onResume();
	}

	@Override
	public void onLocationChanged(Location location) 
	{
		setMyLocationOverlay(locationToGeoPoint(location));
		if(turnOnHeritage)
		{
			new LoadMapItems().execute();
		}
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
		Log.d(tag, "Disabled " + provider + ": " + ((SubMainActivity) getActivity()).getCurrMenu());
		//		Toast.makeText(mContext, "GPS를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
		if(((SubMainActivity) getActivity()).getCurrMenu() == 1 && (provider.equals(LocationManager.GPS_PROVIDER) || provider.equals(LocationManager.NETWORK_PROVIDER )))
		{
			if(!gpsStarter.isVisible())
			{
				gpsStarter.show(getFragmentManager(), "");
			}
		}
	}

	@Override
	public void onProviderEnabled(String provider) {	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {	}

	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			GeoPoint edge = mapView.getProjection().fromPixels(0, 0);
			if((mapView.getZoomLevel() != currZoom) || (edge != currStart))
			{
				new LoadMapItems().execute();

				if(mapOverlay.size() == 1)
				{
					mapOverlay.add(heritageOverlay);
				}else if(mapOverlay.size() == 2)
				{
					mapOverlay.set(1, heritageOverlay);
				}else
				{
					mapOverlay.add(heritageOverlay);
				}
				mapView.invalidate();
				return false;
			}
		}
		return false;
	}

	class LoadMapItems extends AsyncTask<Void, Void, Void>
	{
		LoadingFragment loadingFragment;

		@Override
		protected void onPreExecute() 
		{
			loadingFragment = new LoadingFragment();
			loadingFragment.show(getFragmentManager(), "주변 문화유산을 찾는 중...");
		}

		@Override
		protected Void doInBackground(
				Void... params) {

			GeoPoint topleft = mapView.getProjection().fromPixels(0, 0);
			GeoPoint bottomright = mapView.getProjection().fromPixels(mapView.getWidth(), mapView.getHeight());

			double topRightLat = topleft.getLatitudeE6()/1E6;
			double bottomLeftLat = bottomright.getLatitudeE6()/1E6;
			double topRightLong = topleft.getLongitudeE6()/1E6;
			double bottomLeftLong = bottomright.getLongitudeE6()/1E6;

			HeritageSQLiteHelper sqlHelper = new HeritageSQLiteHelper(mContext);
			SQLiteDatabase db = sqlHelper.getReadableDatabase();

			//build query
			StringBuilder query = new StringBuilder("SELECT * FROM ");
			query.append(Constants.TABLE_NAME);
			query.append(" WHERE XCnts > ").append(topRightLong).append(" AND XCnts < ").append(bottomLeftLong);
			query.append(" AND YCnts > ").append(bottomLeftLat).append(" AND YCnts < ").append(topRightLat);
			query.append(" LIMIT 200;");
			//get data
			Cursor cursor = db.rawQuery(query.toString(), null);
			Log.d(tag, query.toString() + "query, total size : " + cursor.getCount());
			db.close();
			sqlHelper.close();
			heritageOverlay.clear();
			while(cursor.moveToNext())
			{
				double latitude = Double.valueOf(cursor.getString(cursor.getColumnIndex("XCnts"))) * 1E6;
				double longitude = Double.valueOf(cursor.getString(cursor.getColumnIndex("YCnts"))) * 1E6;
				GeoPoint point = new GeoPoint(Double.valueOf(longitude).intValue(), Double.valueOf(latitude).intValue());

				//check existence of the overla at the point
				if(isExist(point)) continue;

				Log.d(tag, Double.valueOf(latitude).intValue()+ " ," +Double.valueOf(longitude).intValue());
				String title = cursor.getString(cursor.getColumnIndex("crltsNm"));
				String subTitle = cursor.getString(cursor.getColumnIndex("itemNm")) +" " + cursor.getString(cursor.getColumnIndex("crltsNoNm")) + "호";

				OverlayItem overlay = new OverlayItem(point, title, subTitle);
				try 
				{
					heritageOverlay.addOverlay(overlay);
				} catch (Exception e) {		}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) 
		{
			if(mapOverlay.size() == 1)
			{
				mapOverlay.add(heritageOverlay);
			}else if(mapOverlay.size() == 2)
			{
				mapOverlay.set(1, heritageOverlay);
			}else
			{
				mapOverlay.add(heritageOverlay);
			}
			loadingFragment.dismiss();
		}

		private boolean isExist(GeoPoint check)
		{
			for(int i = 0; i < heritageOverlay.size(); ++i)
			{
				GeoPoint point = heritageOverlay.getItem(i).getPoint();
				if(point.getLatitudeE6() == check.getLatitudeE6() 
						&& point.getLongitudeE6() == check.getLongitudeE6()) return true;
			}
			return false;
		}
	}

	private void setMyLocationOverlay(GeoPoint p)
	{
		OverlayItem overlayItem = new OverlayItem(p, "현재 위치", "");
		try 
		{
			locationOverlay.clear();
			locationOverlay.addOverlay(overlayItem);
		} catch (Exception e) {	}
		
		if(mapOverlay.size() == 0)
		{
			mapOverlay.add(locationOverlay);
		}else
		{
			mapOverlay.set(0, locationOverlay);
		}
		mapView.invalidate();
	}

	public GeoPoint locationToGeoPoint(Location location)
	{
		GeoPoint point = new GeoPoint((int) (location.getLatitude()*1E6), (int) (location.getLongitude()*1E6));
		return point;
	}

	/** Listeners **/
	OnClickListener myLocButtonListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) {
			//get user's location and move to the point
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location == null)
			{
				location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				if(location == null)
				{
					location = new Location(LocationManager.NETWORK_PROVIDER);
					location.setLatitude(Double.valueOf("37.57273"));
					location.setLongitude(Double.valueOf("126.9687"));
				}
			}
			GeoPoint p = locationToGeoPoint(location);
			setMyLocationOverlay(p);
			mapController.animateTo(p);
		}
	};
}
