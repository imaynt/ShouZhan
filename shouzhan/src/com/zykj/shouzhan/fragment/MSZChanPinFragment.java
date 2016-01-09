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
	
	private static int PERPAGE = 5;// perpage默认每页显示10条信息
	private int nowpage = 1;// 当前显示的页面
	private int mType = 1;// 1、首页    2、产品   3、公司   4、联系   5、english
	private XListView mListView;
	private Handler mHandler;
	
	CustomMenu customMenu;			//侧边栏滑动
	
	@Bind(R.id.rl_test) RelativeLayout rl_test;		//工艺品按钮
	@Bind(R.id.ll_bsq) LinearLayout ll_bsq;		//工艺品按钮

	/**
	 * @param type
	 *           // 1、首页    2、产品   3、公司   4、联系   5、english
	 * @return 实例化收入列表
	 */
	public static MSZChanPinFragment getInstance(int type) {
		MSZChanPinFragment fragment = new MSZChanPinFragment();
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

		customMenu = new CustomMenu(this.getActivity());
		
		customMenu.setContentView(R.layout.ui_product_list);
        customMenu.setRightMenu(R.layout.ui_class_product);
        ButterKnife.bind(this,customMenu);
        return customMenu;  
	}
	
	/**
	 * 测试工艺品事件
	 */
	@OnClick(R.id.rl_test)
	public void testOnClick(){
		Toast.makeText(this.getActivity(), "工艺品被点击", Toast.LENGTH_SHORT).show();
	}
	
	@OnClick(R.id.ll_bsq)
	public void bsqOnClick(){
		startActivity(new Intent(this.getActivity(),ProductForInfoActivity.class));
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

	}

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
