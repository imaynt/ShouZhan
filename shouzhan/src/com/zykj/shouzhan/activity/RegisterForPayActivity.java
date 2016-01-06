package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterForPayActivity extends BaseActivity {
	
	private final int PRICE = 280;
	
	private String whoSelected="card";	//支付方式card:银行卡  wechat:微信  ali:支付宝
	private String payName="";			//支付名称
	
	int num = 1;

	@Bind(R.id.aci_title_textview) 	  TextView aci_title_textview;	//顶部标题栏显示
	@Bind(R.id.ll_back_btn) 	   	  LinearLayout   ll_back_btn;	//顶部标题栏返回按钮
	@Bind(R.id.tv_num) 				  TextView tv_num;				//购买数量
	@Bind(R.id.tv_num_sub) 			  TextView tv_num_sub;			//减号按钮
	@Bind(R.id.tv_num_add) 			  TextView tv_num_add;			//加号按钮
	@Bind(R.id.tv_num_pay) 			  TextView tv_num_pay;			//钱数总和
	@Bind(R.id.tv_pay_years) 		  TextView tv_pay_years;		//支付年数显示
	@Bind(R.id.tv_pay_price) 		  TextView tv_pay_price;		//钱数总和显示
	@Bind(R.id.tv_pay_success) 		  TextView tv_pay_success;		//支付方式显示
	@Bind(R.id.rl_register_pay_yuyue)   RelativeLayout rl_register_pay_yuyue;	//预约按钮
	@Bind(R.id.rl_register_pay_queding) RelativeLayout rl_register_pay_queding;	//确定支付按钮
	@Bind(R.id.rl_reset_pay) 		  RelativeLayout rl_reset_pay;				//重选支付方式
	@Bind(R.id.ll_register_clickhide) LinearLayout   ll_register_clickhide;		//支付信息
	@Bind(R.id.rl_register_pay_zhifu) RelativeLayout rl_register_pay_zhifu;		//在线支付按钮
	@Bind(R.id.ll_yuyue) 		  	  LinearLayout   ll_yuyue;					//预约
	@Bind(R.id.ll_pay_success) 		  LinearLayout   ll_pay_success;			//支付成功
	@Bind(R.id.ll_pay_online) 		  LinearLayout   ll_pay_online;				//线上支付
	@Bind(R.id.rb_ali_pay)			  RadioButton 	 rb_ali_pay;				//支付宝支付
	@Bind(R.id.rb_card_pay)			  RadioButton 	 rb_card_pay;				//银行卡支付
	@Bind(R.id.rb_wechat_pay)		  RadioButton 	 rb_wechat_pay;				//微信支付
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_pay);
		ButterKnife.bind(this);// 绑定第三方
		aci_title_textview.setText(R.string.register_shouzhan);
		
		num = Integer.parseInt(tv_num.getText().toString());
	}
	
	/**
	 * 顶部标题栏返回按钮点击事件
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}
	
	/**
	 * 预约专员
	 */
	@OnClick(R.id.rl_register_pay_zhifu)
	public void payZhifuClick() {
		ll_pay_online.setVisibility(View.VISIBLE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * 预约专员
	 */
	@OnClick(R.id.rl_register_pay_yuyue)
	public void payYuyueClick() {
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.VISIBLE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * 重选支付方式按钮
	 */
	@OnClick(R.id.rl_reset_pay)
	public void resetPayClick() {
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.VISIBLE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * 减去购买数量
	 */
	@OnClick(R.id.tv_num_sub)
	public void numSubClick() {
		if (num < 1 && num > 3)
			num = 1;
		if (num == 1)
			return;
		num = num - 1;
		tv_num.setText(num+"");
		tv_num_pay.setText("￥"+num*PRICE);
		tv_pay_years.setText(num+"年");
		tv_pay_price.setText("￥"+num*PRICE);
	}

	/**
	 * 加上购买数量
	 */
	@OnClick(R.id.tv_num_add)
	public void numAddClick() {
		if (num < 1 && num > 3)
			num = 1;
		if (num == 3)
			return;
		num = num + 1;
		tv_num.setText(num+"");
		tv_num_pay.setText("￥"+num*PRICE+"");
		tv_pay_years.setText(num+"年");
		tv_pay_price.setText("￥"+num*PRICE);
	}
	
	
	/**
	 * 支付宝按钮被选中
	 */
	@OnClick(R.id.rb_ali_pay)
	public void aliPayClick() {
		rb_ali_pay.setChecked(true);
		rb_card_pay.setChecked(false);
		rb_wechat_pay.setChecked(false);
		whoSelected="ali";
	}
	
	/**
	 * 银行卡按钮被选中
	 */
	@OnClick(R.id.rb_card_pay)
	public void cardPayClick() {
		rb_ali_pay.setChecked(false);
		rb_card_pay.setChecked(true);
		rb_wechat_pay.setChecked(false);
		whoSelected="card";
	}
	
	/**
	 * 微信按钮被选中
	 */
	@OnClick(R.id.rb_wechat_pay)
	public void wechatPayClick() {
		rb_ali_pay.setChecked(false);
		rb_card_pay.setChecked(false);
		rb_wechat_pay.setChecked(true);
		whoSelected="wechat";
	}
	
	/**
	 * 确定支付按钮
	 */
	@OnClick(R.id.rl_register_pay_queding)
	public void registerPayOnClick(){
		payName = whoSelected.equals("ali")?"支付宝":(whoSelected.equals("card")?"银行卡":"微信");
		tv_pay_success.setText(payName+"支付成功");
		
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.VISIBLE);
		
	}

}
