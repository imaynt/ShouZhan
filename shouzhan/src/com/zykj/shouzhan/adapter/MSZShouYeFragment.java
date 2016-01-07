package com.zykj.shouzhan.adapter;

import com.loopj.android.http.RequestParams;
import com.zykj.shouzhan.view.XListView;
import com.zykj.shouzhan.view.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MSZShouYeFragment extends Fragment implements IXListViewListener {

	private static int PERPAGE = 5;// perpage默认每页显示10条信息
	private int nowpage = 1;// 当前显示的页面
	private int mType = 1;// 1、首页    2、产品   3、公司   4、联系   5、english
	private XListView mListView;
	private Handler mHandler;
	private RequestParams params;

	/**
	 * @param type
	 *            1全部 2任务3学徒4兑换
	 * @return 实例化收入列表
	 */
	public static MSZShouYeFragment getInstance(int type) {
		MSZShouYeFragment fragment = new MSZShouYeFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;
		
	}

	/**
	 * 初始化页面
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mListView = new XListView(getActivity(), null);
		mListView.setDividerHeight(0);
		mListView.setLayoutParams(params);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		return mListView;

	}

	/**
	 * 配置页面参数
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mType = getArguments().getInt("type");
		mHandler = new Handler();
		
		
		
		requestData();

	}

	/**
	 * 请求服务器数据
	 */
	private void requestData() {
		if (mType == 1) {//1、首页    
//			params = new RequestParams();
//			params.put("userid", BaseApp.getModel().getUserid());
//			params.put("nowpage", nowpage);
//			params.put("perpage", PERPAGE);
//			HttpUtils.allTaskList(res_getAllTaskList, params);
		} else if (mType == 2) {// 2、产品   
//			params = new RequestParams();
//			params.put("userid", BaseApp.getModel().getUserid());
//			params.put("nowpage", nowpage);
//			params.put("perpage", PERPAGE);
//			HttpUtils.taskList(res_getTaskList, params);
		} else if (mType == 3) {// 3、公司  
//			params = new RequestParams();
//			params.put("userid", BaseApp.getModel().getUserid());
//			params.put("nowpage", nowpage);
//			params.put("perpage", PERPAGE);
//			HttpUtils.shouTuTaskList(res_getShouTuTaskList, params);
		} else if (mType == 4) {// 4、联系   
//			params = new RequestParams();
//			params.put("userid", BaseApp.getModel().getUserid());
//			params.put("nowpage", nowpage);
//			params.put("perpage", PERPAGE);
//			HttpUtils.duiHuanTaskList(res_getDuiHuanTaskList, params);
		}
		else if (mType == 5) {// 5、english
//			params = new RequestParams();
//			params.put("userid", BaseApp.getModel().getUserid());
//			params.put("nowpage", nowpage);
//			params.put("perpage", PERPAGE);
//			HttpUtils.duiHuanTaskList(res_getDuiHuanTaskList, params);
		}
	}

//	// 全部
//	private AsyncHttpResponseHandler res_getAllTaskList = new EntityHandler<Income>(Income.class) {
//
//		@Override
//		public void onReadSuccess(List<Income> list) {
//			if (nowpage == 1) {
//				incomes.clear();
//			}
//			incomes.addAll(list);
//			incomeAdapter.notifyDataSetChanged();
//		}
//	};
//	// 任务
//	private AsyncHttpResponseHandler res_getTaskList = new EntityHandler<Income>(Income.class) {
//
//		@Override
//		public void onReadSuccess(List<Income> list) {
//			if (nowpage == 1) {
//				incomes.clear();
//			}
//			incomes.addAll(list);
//			incomeAdapter.notifyDataSetChanged();
//		}
//	};
//	// 学徒
//	private AsyncHttpResponseHandler res_getShouTuTaskList = new EntityHandler<Income>(Income.class) {
//
//		@Override
//		public void onReadSuccess(List<Income> list) {
//			if (nowpage == 1) {
//				incomes.clear();
//			}
//			incomes.addAll(list);
//			incomeAdapter.notifyDataSetChanged();
//		}
//	};
//	// 兑换
//	private AsyncHttpResponseHandler res_getDuiHuanTaskList = new EntityHandler<Income>(Income.class) {
//
//		@Override
//		public void onReadSuccess(List<Income> list) {
//			if (nowpage == 1) {
//				incomes.clear();
//			}
//			incomes.addAll(list);
//			incomeAdapter.notifyDataSetChanged();
//		}
//	};

	/**
	 * 下拉刷新
	 */
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage = 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 上拉加载
	 */
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage += 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 结束加载/刷新
	 */
	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

}
