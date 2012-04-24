package com.huynt.samsung.view;

import java.util.Calendar;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huynt.libs.interfaces.GetDataCallBack;
import com.huynt.libs.interfaces.Screen;
import com.huynt.libs.io.HTTPStream;
import com.huynt.samsung.PConfig;
import com.huynt.samsung.R;
import com.huynt.samsung.ThoiTietActivity;
import com.huynt.samsung.objects.WeatherData;
import com.huynt.samsung.objects.WeatherItem;

public class HomeView extends LinearLayout implements Screen , GetDataCallBack<WeatherData>{
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = HomeView.class.getSimpleName();

	private ThoiTietActivity mFCBCActivity;
	
	private int mCurrentCtyId = 0;
	
	private TextView tvAreaName;
	
	//Day 1
	private ImageView imgDay1Icon;
	private TextView tvDay1Date;
	private TextView tvDay1Description;
	private TextView tvDay1LowTemp;
	private TextView tvDay1HighTemp;

	//Day 2
	private ImageView imgDay2Icon;
	private TextView tvDay2Date;
	private TextView tvDay2Description;
	private TextView tvDay2LowTemp;
	private TextView tvDay2HighTemp;

	//Day 3
	private ImageView imgDay3Icon;
	private TextView tvDay3Date;
	private TextView tvDay3Description;
	private TextView tvDay3LowTemp;
	private TextView tvDay3HighTemp;
	
	private TextView mTvUpdateTime;

	public HomeView(ThoiTietActivity fcbcActivity) {
		super(fcbcActivity);
		mFCBCActivity = fcbcActivity;

		fcbcActivity.getLayoutInflater().inflate(R.layout.area_layout, this, true);

		tvAreaName = (TextView) findViewById(R.id.tvAreaName);
		
		imgDay1Icon = (ImageView) findViewById(R.id.imgDay1);
		tvDay1Date = (TextView) findViewById(R.id.tvDate1);
		tvDay1Description = (TextView) findViewById(R.id.tvDescription1);
		tvDay1LowTemp = (TextView) findViewById(R.id.tvTempLow1);
		tvDay1HighTemp = (TextView) findViewById(R.id.tvTempHigh1);
		
		imgDay2Icon = (ImageView) findViewById(R.id.imgDay2);
		tvDay2Date = (TextView) findViewById(R.id.tvDate2);
		tvDay2Description = (TextView) findViewById(R.id.tvDescription2);
		tvDay2LowTemp = (TextView) findViewById(R.id.tvTempLow2);
		tvDay2HighTemp = (TextView) findViewById(R.id.tvTempHigh2);
		
		imgDay3Icon = (ImageView) findViewById(R.id.imgDay3);
		tvDay3Date = (TextView) findViewById(R.id.tvDate3);
		tvDay3Description = (TextView) findViewById(R.id.tvDescription3);
		tvDay3LowTemp = (TextView) findViewById(R.id.tvTempLow3);
		tvDay3HighTemp = (TextView) findViewById(R.id.tvTempHigh3);
		
		mTvUpdateTime = (TextView) findViewById(R.id.tvUpdateInfo);
	}

	@Override
	public boolean back() {
		return false;
	}

	@Override
	public int getScreenId() {
		return ThoiTietActivity.VIEW_HOME;
	}

	@Override
	public void loadScreenData() {
		loadScreenData(PConfig.ID_AREA[mCurrentCtyId]);
	}
	
	public void setCurrentCtyIndex(int idx){
		mCurrentCtyId = idx;
	}
	
	public void loadScreenData(String areaId){
		new GetDataTask(this).execute(PConfig.API_WEATHER + areaId, areaId);
	}

	@Override
	public void clearData() {
	}
	
	@Override
	public void returnData(WeatherData data) {
		if(data != null && data.getListWeather().size() > 0){
			tvAreaName.setText(data.getCityName());
			
			final WeatherItem item1 = data.getListWeather().get(0);
			imgDay1Icon.setImageBitmap(BitmapFactory.decodeResource(mFCBCActivity.getResources(), PConfig.getIdImageForWeather(item1.getIcon())));
			tvDay1Date.setText(item1.getDate());
			tvDay1Description.setText(item1.getDescription());
			tvDay1LowTemp.setText(item1.getLowTemp());
			tvDay1HighTemp.setText(item1.getHighTemp());
			
			final WeatherItem item2 = data.getListWeather().get(1);
			imgDay2Icon.setImageBitmap(BitmapFactory.decodeResource(mFCBCActivity.getResources(), PConfig.getIdImageForWeather(item2.getIcon())));
			tvDay2Date.setText(item2.getDate());
			tvDay2Description.setText(item2.getDescription());
			tvDay2LowTemp.setText(item2.getLowTemp());
			tvDay2HighTemp.setText(item2.getHighTemp());
			
			final WeatherItem item3 = data.getListWeather().get(2);
			imgDay3Icon.setImageBitmap(BitmapFactory.decodeResource(mFCBCActivity.getResources(), PConfig.getIdImageForWeather(item3.getIcon())));
			tvDay3Date.setText(item3.getDate());
			tvDay3Description.setText(item3.getDescription());
			tvDay3LowTemp.setText(item3.getLowTemp());
			tvDay3HighTemp.setText(item3.getHighTemp());
			
			mTvUpdateTime.setText(generateTimeUpdate());
		}
		
		mFCBCActivity.gotoScreen(mFCBCActivity.getHomeScreen(), false);
	}
	
	private static String generateTimeUpdate(){
		Calendar c = Calendar.getInstance(); 
		int seconds = c.get(Calendar.SECOND);
		int minutes = c.get(Calendar.MINUTE);
		int hours = c.get(Calendar.HOUR_OF_DAY);
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		
		return "Cập nhật lúc : " + day + "/" + month + "/" + year + " - " + hours + ":" + minutes + ":" + seconds;
	}

	// ///////////////////////////////////////////////
	// INNER CLASS
	// ///////////////////////////////////////////////
	class GetDataTask extends AsyncTask<String, Integer, WeatherData>{
		private GetDataCallBack<WeatherData> mCallback;
		
		public GetDataTask(GetDataCallBack<WeatherData> callback){
			mCallback = callback;
		}
		
		protected WeatherData doInBackground(String... params) {
			String url = params[0];
			String cityName = PConfig.AREA[mCurrentCtyId];
			
			WeatherData result = new WeatherData(cityName);
			
			String data = HTTPStream.getHTTPPostString(url, null);

			if(data == null)
				return null;
			
			JSONObject object;
			try {
				object = (JSONObject) new JSONTokener(data).nextValue();
				JSONObject obj3DayNext = (JSONObject) object.get(WeatherItem.KEY_3DAY_NEXT);
				JSONObject obj24h = (JSONObject) obj3DayNext.get(WeatherItem.KEY_24H_NEXT);
				JSONObject obj48h = (JSONObject) obj3DayNext.get(WeatherItem.KEY_48H_NEXT);
				JSONObject obj72h = (JSONObject) obj3DayNext.get(WeatherItem.KEY_72H_NEXT);
				
				result.add(WeatherItem.getInstanceFromJSON(obj24h));
				result.add(WeatherItem.getInstanceFromJSON(obj48h));
				result.add(WeatherItem.getInstanceFromJSON(obj72h));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(WeatherData result) {
			super.onPostExecute(result);
			if(mCallback != null)
				mCallback.returnData(result);
		}
	}
	
}
