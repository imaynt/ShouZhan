package com.zykj.shouzhan.activity;

import java.util.ArrayList;

import com.zykj.shouzhan.banner.ImagePagerAdapter;
import com.zykj.shouzhan.banner.ViewFlow;
import com.zykj.shouzhan.banner.CircleFlowIndicator;
import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewFlipper;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductForInfoActivity  extends BaseActivity {
	
	@Bind(R.id.viewflow) ViewFlow mViewFlow;
	@Bind(R.id.viewflowindic) CircleFlowIndicator mFlowIndicator;
	private int mCurrPos;
	private ViewFlipper notice_vf;
	ArrayList<String> imageUrlList = new ArrayList<String>();
	ArrayList<String> linkUrlArray= new ArrayList<String>();
	ArrayList<String> titleList= new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_product_info);
		ButterKnife.bind(this);// 绑定第三方
		
		imageUrlList.add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
		imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
		imageUrlList.add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
		imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");

		linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44301359");
		linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/43486527");
		linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44648121");
		linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44619589");
		
		titleList.add("常见Android进阶笔试题");
		titleList.add("GridView之仿支付宝钱包首页");
		titleList.add("仿手机QQ网络状态条的显示与消失 ");
		titleList.add("Android循环滚动广告条的完美实现 ");
		
		initBanner(imageUrlList);
	}
	
	private void moveNext() {
		setView(this.mCurrPos, this.mCurrPos + 1);
		this.notice_vf.setInAnimation(this, R.anim.in_bottomtop);
		this.notice_vf.setOutAnimation(this, R.anim.out_bottomtop);
		this.notice_vf.showNext();
	}
	
	private void setView(int curr, int next) {

		View noticeView = getLayoutInflater().inflate(R.layout.notice_item,
				null);
		TextView notice_tv = (TextView) noticeView.findViewById(R.id.notice_tv);
		if ((curr < next) && (next > (titleList.size() - 1))) {
			next = 0;
		} else if ((curr > next) && (next < 0)) {
			next = titleList.size() - 1;
		}
		notice_tv.setText(titleList.get(next));
		notice_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			}
		});
		if (notice_vf.getChildCount() > 1) {
			notice_vf.removeViewAt(0);
		}
		notice_vf.addView(noticeView, notice_vf.getChildCount());
		mCurrPos = next;

	}
	
private void initBanner(ArrayList<String> imageUrlList) {
		
		mViewFlow.setAdapter(new ImagePagerAdapter(this, imageUrlList,
				linkUrlArray, titleList).setInfiniteLoop(true));
		mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
														// 我的ImageAdapter实际图片张数为3

		mViewFlow.setFlowIndicator(mFlowIndicator);
		mViewFlow.setTimeSpan(4500);
		mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
		mViewFlow.startAutoFlowTimer(); // 启动自动播放
	}

}