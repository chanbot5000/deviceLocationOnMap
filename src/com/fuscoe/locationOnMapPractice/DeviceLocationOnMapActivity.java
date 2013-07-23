package com.fuscoe.locationOnMapPractice;

import android.app.Activity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.esri.android.map.LocationService;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;

public class DeviceLocationOnMapActivity extends Activity {

	MapView mMapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mMapView = (MapView) findViewById(R.id.map);

		mMapView.addLayer(new ArcGISTiledMapServiceLayer(
				"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));

		mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {

			public void onStatusChanged(Object source, STATUS status) {
				if (source == mMapView && status == STATUS.INITIALIZED) {
					LocationService ls = mMapView.getLocationService();
					ls.setAutoPan(false);

					ls.start();
				}

			}

		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}

}