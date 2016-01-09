package com.zykj.shouzhan.fragment;

import com.loopj.android.http.RequestParams;
import com.zykj.shouzhan.R;
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

public class MSZLianXiFragment extends Fragment implements IXListViewListener {

	private static int PERPAGE = 5;// perpageĬ��ÿҳ��ʾ10����Ϣ
	private int nowpage = 1;// ��ǰ��ʾ��ҳ��
	private int mType = 1;// 1����ҳ 2����Ʒ 3����˾ 4����ϵ 5��english
	private XListView mListView;
	private Handler mHandler;
	private RequestParams params;
	@Bind(R.id.tv_mobile)
	TextView tv_mobile;// �ֻ�
	@Bind(R.id.tv_telephone)
	TextView tv_telephone;// �̶��绰
	@Bind(R.id.tv_contacts)
	TextView tv_contacts;// ��ϵ��
	@Bind(R.id.tv_qq)
	TextView tv_qq;// qq
	@Bind(R.id.tv_weixin)
	TextView tv_weixin;// ΢��
	@Bind(R.id.tv_address)
	TextView tv_address;// ��ַ
	@Bind(R.id.tv_map_navigation)
	TextView tv_map_navigation;// ��ͼ����
	@Bind(R.id.btn_manage_lianxi)
	TextView btn_manage_lianxi;// ������ϵ

	/**
	 * @param type
	 *            // 1����ҳ 2����Ʒ 3����˾ 4����ϵ 5��english
	 * @return ʵ���������б�
	 */
	public static MSZLianXiFragment getInstance(int type) {
		MSZLianXiFragment fragment = new MSZLianXiFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	/**
	 * ��ʼ��ҳ��
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.ui_ms_contacts, null);
		
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

	@OnClick(R.id.btn_manage_lianxi) // ������ϵ
	public void manageLianXi() {
		
		startActivity(new Intent(getActivity(), ManageContactsStyleAcyivity.class));
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
