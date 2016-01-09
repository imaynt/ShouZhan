package com.zykj.shouzhan.fragment;

import com.zykj.shouzhan.activity.CustomMenu;
import com.zykj.shouzhan.activity.ProductForInfoActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.XListView;
import com.zykj.shouzhan.view.XListView.IXListViewListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MSZChanPinFragment extends Fragment implements IXListViewListener {
	
	private static int PERPAGE = 5;// perpageĬ��ÿҳ��ʾ10����Ϣ
	private int nowpage = 1;// ��ǰ��ʾ��ҳ��
	private int mType = 1;// 1����ҳ    2����Ʒ   3����˾   4����ϵ   5��english
	private XListView mListView;
	private Handler mHandler;
	
	CustomMenu customMenu;			//���������
	
	@Bind(R.id.rl_test) RelativeLayout rl_test;		//����Ʒ��ť
	@Bind(R.id.ll_bsq) LinearLayout ll_bsq;		//����Ʒ��ť

	/**
	 * @param type
	 *           // 1����ҳ    2����Ʒ   3����˾   4����ϵ   5��english
	 * @return ʵ���������б�
	 */
	public static MSZChanPinFragment getInstance(int type) {
		MSZChanPinFragment fragment = new MSZChanPinFragment();
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

		customMenu = new CustomMenu(this.getActivity());
		
		customMenu.setContentView(R.layout.ui_product_list);
        customMenu.setRightMenu(R.layout.ui_class_product);
        ButterKnife.bind(this,customMenu);
        return customMenu;  
	}
	
	/**
	 * ���Թ���Ʒ�¼�
	 */
	@OnClick(R.id.rl_test)
	public void testOnClick(){
		Toast.makeText(this.getActivity(), "����Ʒ�����", Toast.LENGTH_SHORT).show();
	}
	
	@OnClick(R.id.ll_bsq)
	public void bsqOnClick(){
		startActivity(new Intent(this.getActivity(),ProductForInfoActivity.class));
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
