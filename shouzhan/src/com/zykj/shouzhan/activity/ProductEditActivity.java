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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductEditActivity extends BaseActivity {
	private String timeString;
	private File file;
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;// ����
	@Bind(R.id.img_product_pic)
	ImageView img_product_pic;// ��ƷͼƬ
	@Bind(R.id.ed_product_name)
	EditText ed_product_name;// ��Ʒ����
	@Bind(R.id.ed_product_price)
	EditText ed_product_price;// ��Ʒ�۸�
	@Bind(R.id.tv_product_sort)
	TextView tv_product_sort;// ��Ʒ����
	@Bind(R.id.tv_complete)
	TextView tv_complete;// ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_product_edit);
		ButterKnife.bind(this);
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.edit_product));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * ��ƷͼƬ
	 */
	@OnClick(R.id.img_product_pic)
	public void submitProductPic() {
		UIDialog.ForThreeBtn(this, new String[] { "����", "��������ѡȡ", "ȡ��" }, this);
	}

	/**
	 * ��Ʒ����
	 */
	@OnClick(R.id.tv_product_sort)
	public void getProductSort() {

	}

	/**
	 * ���,�ύ��Ϣ
	 */
	@OnClick(R.id.tv_product_sort)
	public void submitProductInfo() {
//		if (StringUtil.isEmpty(ed_product_name.getText().toString().trim())) {
//			Tools.toast(this, "��Ʒ���Ʋ���Ϊ��");
//			return;
//		}
//		if (StringUtil.isEmpty(tv_product_sort.getText().toString().trim())) {
//			Tools.toast(this, "��Ʒ���಻��Ϊ��");
//			return;
//		}
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.dialog_modif_1:
			/* ���� */
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
			/* �������ѡȡ */
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
			/* ����ǵ���������գ�ͼƬ�������ֺ�·�� */
			File temp = new File(
					Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + timeString + ".jpg");
			startPhotoZoom(Uri.fromFile(temp));

			break;
		case 2:
			/* �����ֱ�Ӵ�����ȡ */
			try {
				startPhotoZoom(data.getData());
			} catch (Exception e) {
				Toast.makeText(this, "��û��ѡ���κ���Ƭ", Toast.LENGTH_LONG).show();
			}
			break;
		case 3:
			/* ȡ�òü����ͼƬ */
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
			// ����һ���ļ��ж��󣬸�ֵΪ�ⲿ�洢����Ŀ¼
			File sdcardDir = Environment.getExternalStorageDirectory();
			// �õ�һ��·����������sdcard���ļ���·��������
			String path = sdcardDir.getPath() + "/DCIM/Camera";
			File path1 = new File(path);
			if (!path1.exists()) {
				// �������ڣ�����Ŀ¼��������Ӧ��������ʱ�򴴽�
				path1.mkdirs();

			}
		}
	}

	/**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// �������crop=true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * ����ü�֮���ͼƬ����
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			// Drawable drawable = new BitmapDrawable(photo);
			/* ����ע�͵ķ����ǽ��ü�֮���ͼƬ��Base64Coder���ַ���ʽ�� ������������QQͷ���ϴ����õķ������������ */
			savaBitmap(photo);
			// avatar_head_image.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * �����к��ͼƬ���浽����ͼƬ�ϣ�
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
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);// ��Bitmap�����������
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
