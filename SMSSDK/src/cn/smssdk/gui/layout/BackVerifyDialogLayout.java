/*
 * 瀹樼綉鍦扮珯:http://www.mob.com
 * 鎶�湳鏀寔QQ: 4006852216
 * 瀹樻柟寰俊:ShareSDK   锛堝鏋滃彂甯冩柊鐗堟湰鐨勮瘽锛屾垜浠皢浼氱涓�椂闂撮�杩囧井淇″皢鐗堟湰鏇存柊鍐呭鎺ㄩ�缁欐偍銆傚鏋滀娇鐢ㄨ繃绋嬩腑鏈変换浣曢棶棰橈紝涔熷彲浠ラ�杩囧井淇′笌鎴戜滑鍙栧緱鑱旂郴锛屾垜浠皢浼氬湪24灏忔椂鍐呯粰浜堝洖澶嶏級
 *
 * Copyright (c) 2014骞�mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import com.mob.tools.utils.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BackVerifyDialogLayout {

	public static LinearLayout create(Context context) {
		SizeHelper.prepare(context);

		LinearLayout root = new LinearLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		root.setLayoutParams(params);
		root.setOrientation(LinearLayout.VERTICAL);

		TextView dialogHint = new TextView(context);
		dialogHint.setId(Res.id.tv_dialog_hint);
		LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		hintParams.topMargin = SizeHelper.fromPxWidth(32);
		hintParams.bottomMargin = SizeHelper.fromPxWidth(32);
		dialogHint.setLayoutParams(hintParams);
		dialogHint.setPadding(SizeHelper.fromPxWidth(18), 0, SizeHelper.fromPxWidth(18), 0);
		dialogHint.setLineSpacing(SizeHelper.fromPxWidth(8), 1);
		int resid = R.getStringRes(context, "smssdk_make_sure_mobile_detail");
		dialogHint.setText(resid);
		dialogHint.setTextColor(0xffffffff);
		dialogHint.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPxWidth(26));
		dialogHint.setGravity(Gravity.CENTER);
		root.addView(dialogHint);

		View line = new View(context);
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(1));
		line.setLayoutParams(lineParams);
		line.setBackgroundColor(0xff737373);
		root.addView(line);

		LinearLayout wrapper = new LinearLayout(context);
		LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		wrapper.setLayoutParams(wrapperParams);


		Button ok = new Button(context);
		ok.setId(Res.id.btn_dialog_ok);
		LinearLayout.LayoutParams okParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(78),1);
		okParams.leftMargin = SizeHelper.fromPxWidth(3);
		ok.setLayoutParams(okParams);
		resid = R.getBitmapRes(context, "smssdk_dialog_btn_back");
		ok.setBackgroundResource(resid);
		int padding = SizeHelper.fromPxWidth(8);
		ok.setPadding(padding, padding, padding, padding);
		resid = R.getStringRes(context, "smssdk_ok");
		ok.setText(resid);
		ok.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		ok.setTextColor(0xffffffff);
		wrapper.addView(ok);

		View line2 = new View(context);
		LinearLayout.LayoutParams line2Params = new LinearLayout.LayoutParams(SizeHelper.fromPxWidth(1),LinearLayout.LayoutParams.MATCH_PARENT);
		line2.setLayoutParams(line2Params);
		line2.setBackgroundColor(0xff737373);
		wrapper.addView(line2);

		Button cancel = new Button(context);
		cancel.setId(Res.id.btn_dialog_cancel);
		LinearLayout.LayoutParams cancelParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(78),1);
		cancelParams.rightMargin = SizeHelper.fromPxWidth(3);
		cancel.setLayoutParams(cancelParams);
		resid = R.getBitmapRes(context, "smssdk_dialog_btn_back");
		cancel.setBackgroundResource(resid);
		cancel.setPadding(padding, padding, padding, padding);
		resid = R.getStringRes(context, "smssdk_cancel");
		cancel.setText(resid);
		cancel.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		cancel.setTextColor(0xffffffff);
		wrapper.addView(cancel);

		root.addView(wrapper);
		return root;
	}
}
