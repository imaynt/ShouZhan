package com.zykj.shouzhan.fragment;

import com.loopj.android.http.RequestParams;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.activity.ManageCompanyIntroSecondActivity;
import com.zykj.shouzhan.activity.ManageContactsStyleAcyivity;
import com.zykj.shouzhan.view.XListView;
import com.zykj.shouzhan.view.XListView.IXListViewListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MSZGongSiFragment extends Fragment implements IXListViewListener {

	private static int PERPAGE = 5;// perpageĬ��ÿҳ��ʾ10����Ϣ
	private int nowpage = 1;// ��ǰ��ʾ��ҳ��
	private int mType = 1;// 1����ҳ    2����Ʒ   3����˾   4����ϵ   5��english
	private XListView mListView;
	private Handler mHandler;
	private RequestParams params;
	@Bind(R.id.btn_manage_company)
	TextView btn_manage_lianxi;// ������ϵ

	/**
	 * @param type
	 *           1����ҳ    2����Ʒ   3����˾   4����ϵ   5��english
	 * @return ʵ���������б�
	 */
	public static MSZGongSiFragment getInstance(int type) {
		MSZGongSiFragment fragment = new MSZGongSiFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;
		
	}

	/**
	 * ��ʼ��ҳ��
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.ui_ms_company_intro, null);
		
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mListView = new XListView(getActivity(), null);
		mListView.setDividerHeight(0);
		mListView.setLayoutParams(params);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		ButterKnife.bind(this,view);
		return view;

	}
	@OnClick(R.id.btn_manage_company) // ������ϵ
	public void manageLianXi() {
		
		startActivity(new Intent(getActivity(), ManageCompanyIntroSecondActivity.class));
	}
	
	/**
	 * ����ҳ�����
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mType = getArguments().getInt("type");
		mHandler = new Handler();
		
		requestData();

	}

	/**
	 * �������������
	 */
	private void requestData() {
		
	}

	/**
	 * ����ˢ��
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
	 * ��������
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
	 * ��������/ˢ��
	 */
	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("�ո�");
	}

}
