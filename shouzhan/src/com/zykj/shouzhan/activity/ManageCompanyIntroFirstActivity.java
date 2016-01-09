package com.zykj.shouzhan.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.StringUtil;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;
import com.zykj.shouzhan.view.UIDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageCompanyIntroFirstActivity extends BaseActivity {
	private String timeString;
	private File file, file1, file2;// file1为图片1 file2为营业执照
	private int type = 1;// 1为图片1 2为营业执照
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.img_person)
	ImageView img_person;// 头像
	@Bind(R.id.img_zhizhao)
	ImageView img_zhizhao;// 执照
	@Bind(R.id.tv_fangda1)
	TextView tv_fangda1;// 放大
	@Bind(R.id.tv_delete)
	TextView tv_delete;// 删除
	@Bind(R.id.tv_fangda2)
	TextView tv_fangda2;// 放大
	@Bind(R.id.ed_company_name)
	EditText ed_company_name;// 公司名称
	@Bind(R.id.ed_company_intro)
	EditText ed_company_intro;// 公司介绍
	@Bind(R.id.tv_submit)
	TextView tv_submit;// 提交
	@Bind(R.id.aci_edit_textview)
	TextView tv_complete;// 完成

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_manage_company_intro_first);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.manage_company_intro));
		myCommonTitle.setListener(this, this, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
		myCommonTitle.setEditTitle(getString(R.string.complete));
	}

	/**
	 * 完成
	 */
	@OnClick(R.id.aci_edit_textview)
	public void complete() {

	}

	/**
	 * 上传头像
	 */
	@OnClick(R.id.img_person)
	public void shangchuanAvatar() {
		type = 1;
		UIDialog.ForThreeBtn(this, new String[] { "拍照", "从相册中选取", "取消" }, this);
	}

	/**
	 * 上传执照
	 */
	@OnClick(R.id.img_zhizhao)
	public void shangchuanZhiZhao() {
		UIDialog.ForThreeBtn(this, new String[] { "拍照", "从相册中选取", "取消" }, this);
	}

	/**
	 * 放大
	 */
	@OnClick(R.id.tv_fangda1)
	public void fangdaAvatar() {

	}

	/**
	 * 删除
	 */
	@OnClick(R.id.tv_delete)
	public void deleteAvatar() {

	}

	/**
	 * 提交信息
	 */
	@OnClick(R.id.tv_submit)
	public void submitData() {
		// if (StringUtil.isEmpty(ed_company_name.getText().toString().trim()))
		// {
		// Tools.toast(ManageCompanyIntroFirstActivity.this, "公司名称不能为空");return;
		// }
		// if (StringUtil.isEmpty(ed_company_intro.getText().toString().trim()))
		// {
		// Tools.toast(ManageCompanyIntroFirstActivity.this, "公司介绍不能为空");return;
		// }
		startActivity(new Intent(ManageCompanyIntroFirstActivity.this, ManageCompanyIntroSecondActivity.class));
		finish();
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.dialog_modif_1:
			/* 拍照 */
			UIDialog.closeDialog();
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", new Locale("zh", "CN"));
			timeString = dateFormat.format(date);
			createSDCardDir();
			Intent shootIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			shootIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
					new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera", timeString + ".jpg")));
			startActivityForResult(shootIntent, 1);
			break;
		case R.id.dialog_modif_2:
			/* 从相册中选取 */
			UIDialog.closeDialog();
			Intent photoIntent = new Intent(Intent.ACTION_PICK, null);
			photoIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(photoIntent, 2);
			break;
		case R.id.dialog_modif_3:
			UIDialog.closeDialog();
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			/* 如果是调用相机拍照，图片设置名字和路径 */
			File temp = new File(
					Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + timeString + ".jpg");
			startPhotoZoom(Uri.fromFile(temp));

			break;
		case 2:
			/* 如果是直接从相册获取 */
			try {
				startPhotoZoom(data.getData());
			} catch (Exception e) {
				Toast.makeText(this, "您没有选择任何照片", Toast.LENGTH_LONG).show();
			}
			break;
		case 3:
			/* 取得裁剪后的图片 */
			if (data != null) {
				setPicToView(data);
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + "/DCIM/Camera";
			File path1 = new File(path);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();

			}
		}
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			// Drawable drawable = new BitmapDrawable(photo);
			/* 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似 */
			savaBitmap(photo);
			// avatar_head_image.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * 将剪切后的图片保存到本地图片上！
	 */
	public void savaBitmap(Bitmap bitmap) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", new Locale("zh", "CN"));
		String cutnameString = dateFormat.format(date);
		String filename = Environment.getExternalStorageDirectory().getPath() + "/" + cutnameString + ".jpg";
		file = new File(filename);
		FileOutputStream fOut = null;
		try {
			file.createNewFile();
			fOut = new FileOutputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);// 把Bitmap对象解析成流
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (type == 1) {
			img_person.setImageBitmap(bitmap);
			file1 = file;
		} else {
			img_zhizhao.setImageBitmap(bitmap);
			file2 = file;
		}

	}
}
