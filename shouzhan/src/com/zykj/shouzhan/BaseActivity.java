package com.zykj.shouzhan;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zykj.shouzhan.utils.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity implements android.view.View.OnClickListener{
	private SharedPreferences defalut_sp;
	private SharedPreferences appoint_sp;
	
	/**
	 * 所有省
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - 省 value - 市
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
	
	/**
	 * key - 区 values - 邮编
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>(); 

	/**
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	protected String mCurrentDistrictName ="";
	
	/**
	 * 当前区的邮政编码
	 */
	protected String mCurrentZipCode ="";
	
	/**
	 * 解析省市区的XML数据
	 */
//	 protected void initProvinceDatas()
//		{
//			List<ProvinceModel> provinceList = null;
//	    	AssetManager asset = getAssets();
//	        try {
//	            InputStream input = asset.open("province_data.xml");
//	            // 创建一个解析xml的工厂对象
//				SAXParserFactory spf = SAXParserFactory.newInstance();
//				// 解析xml
//				SAXParser parser = spf.newSAXParser();
//				XmlParserHandler handler = new XmlParserHandler();
//				parser.parse(input, handler);
//				input.close();
//				// 获取解析出来的数据
//				provinceList = handler.getDataList();
//				//*/ 初始化默认选中的省、市、区
//				if (provinceList!= null && !provinceList.isEmpty()) {
//					mCurrentProviceName = provinceList.get(0).getName();
//					List<CityModel> cityList = provinceList.get(0).getCityList();
//					if (cityList!= null && !cityList.isEmpty()) {
//						mCurrentCityName = cityList.get(0).getName();
//						List<DistrictModel> districtList = cityList.get(0).getDistrictList();
//						mCurrentDistrictName = districtList.get(0).getName();
//						mCurrentZipCode = districtList.get(0).getZipcode();
//					}
//				}
//				//*/
//				mProvinceDatas = new String[provinceList.size()];
//	        	for (int i=0; i< provinceList.size(); i++) {
//	        		// 遍历所有省的数据
//	        		mProvinceDatas[i] = provinceList.get(i).getName();
//	        		List<CityModel> cityList = provinceList.get(i).getCityList();
//	        		String[] cityNames = new String[cityList.size()];
//	        		for (int j=0; j< cityList.size(); j++) {
//	        			// 遍历省下面的所有市的数据
//	        			cityNames[j] = cityList.get(j).getName();
//	        			List<DistrictModel> districtList = cityList.get(j).getDistrictList();
//	        			String[] distrinctNameArray = new String[districtList.size()];
//	        			DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
//	        			for (int k=0; k<districtList.size(); k++) {
//	        				// 遍历市下面所有区/县的数据
//	        				DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
//	        				// 区/县对于的邮编，保存到mZipcodeDatasMap
//	        				mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
//	        				distrinctArray[k] = districtModel;
//	        				distrinctNameArray[k] = districtModel.getName();
//	        			}
//	        			// 市-区/县的数据，保存到mDistrictDatasMap
//	        			mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
//	        		}
//	        		// 省-市的数据，保存到mCitisDatasMap
//	        		mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
//	        	}
//	        } catch (Throwable e) {  
//	            e.printStackTrace();  
//	        } finally {
//	        	
//	        } 
//		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		defalut_sp = getSharedPreferences(BaseApp.config, Context.MODE_PRIVATE);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		Tools.M_SCREEN_WIDTH = metrics.widthPixels;
		Tools.M_SCREEN_HEIGHT = metrics.heightPixels;

		BaseApp.getInstance().addActivity(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Tools.Log("BaseActivity关闭");
		BaseApp.getInstance().finishActivity(this);
	}

	/**
	 * 触摸监听
	 */
	public void onClick(View view) {
	}

	public void initView(int viewId) {
		setContentView(viewId);
	}

	public void setListener(View... view) {
		for (int i = 0; i < view.length; i++) {
			view[i].setOnClickListener(this);
		}
	}

	public String getSharedPreferenceValue(String key) {
		String value = defalut_sp.getString(key, "");
		return value;
	}

	public void putSharedPreferenceValue(String key, String value) {
		SharedPreferences.Editor editor;
		editor = defalut_sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getSharedPreferenceValue(String tag, String key) {
		appoint_sp = getSharedPreferences(tag, Context.MODE_PRIVATE);
		String value = appoint_sp.getString(key, "");
		return value;
	}

	public void putSharedPreferenceValue(String tag, String key, String value) {
		appoint_sp = getSharedPreferences(tag, 0);
		SharedPreferences.Editor editor;
		editor = appoint_sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 回复当前界面的操作
	 */
	protected void onResume() {
		super.onResume();
		BaseApp.getInstance().resumeActivity(this);
	}

}
