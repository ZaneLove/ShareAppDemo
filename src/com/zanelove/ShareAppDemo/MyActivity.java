package com.zanelove.ShareAppDemo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.api.FrontiaAuthorization;
import com.baidu.frontia.api.FrontiaSocialShare;
import com.baidu.frontia.api.FrontiaSocialShareContent;
import com.baidu.frontia.api.FrontiaSocialShareListener;

public class MyActivity extends Activity {

    // 分享
    private FrontiaSocialShare mSocialShare;
    private FrontiaSocialShareContent mImageContent;
    private Toast toast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        initShare();

        this.findViewById(R.id.tv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocialShare.show(MyActivity.this.getWindow().getDecorView(), mImageContent, FrontiaSocialShare.FrontiaTheme.LIGHT, new ShareListener());
            }
        });
    }

    private void initShare() {
        Frontia.init(this.getApplicationContext(), Conf.APIKEY); //自定义
        mImageContent = new FrontiaSocialShareContent();
        mSocialShare = Frontia.getSocialShare();
        mSocialShare.setContext(this);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.SINAWEIBO.toString(), Conf.SINA_APP_KEY);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.QZONE.toString(), Conf.QQ_APP_KEY);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.QQFRIEND.toString(), Conf.QQ_APP_KEY);
        mSocialShare.setClientName(FrontiaAuthorization.MediaType.QQFRIEND.toString(), getResources().getString(R.string.app_name));
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.WEIXIN.toString(), Conf.WEIXIN_APP_KEY);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.RENREN.toString(), Conf.RENREN_APP_KEY);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.KAIXIN.toString(), Conf.KAIXIN_APP_KEY);
        mSocialShare.setClientId(FrontiaAuthorization.MediaType.QQWEIBO.toString(), Conf.QQWEIBO_APP_KEY);
        mImageContent.setTitle("共和国客户端"); //自定义
        mImageContent.setContent("共和国客户端  http://china.com/app/"); //自定义
        mImageContent.setImageData(BitmapFactory.decodeResource(getResources(), R.drawable.ghg)); //自定义
        mImageContent.setLinkUrl(Conf.LINK_URL); //自定义
    }

    private class ShareListener implements FrontiaSocialShareListener {

        @Override
        public void onSuccess() {
            toast.setText("分享成功");
            toast.show();
        }

        @Override
        public void onFailure(int errCode, String errMsg) {
            toast.setText("分享失败");
            toast.show();
        }

        @Override
        public void onCancel() {
            toast.setText("取消分享");
            toast.show();
        }
    }
}
