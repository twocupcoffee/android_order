package cczu.mobileorderfood;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final int REGISTERACTIVITY = 1;
    static MyApplication mAppInstance;
    public ImageButton mImgBtnLogin, mImgBtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppInstance = (MyApplication) getApplication(); //获得全局变量对象
        mAppInstance.g_context = getApplicationContext();
        mAppInstance.g_user = new MyUser();
        mAppInstance.g_orders = new ArrayList<Order>();
        mAppInstance.g_dishes = new Dishes();
        mAppInstance.g_dishes.mDishes = FillDishesList();

        ImageButton imgBtnRest = (ImageButton) findViewById(R.id.imgBtnRest);
        ImageButton imgBtnTakeout = (ImageButton) findViewById(R.id.imgBtnTakeout);
        ImageButton imgBtnUserInfo = (ImageButton) findViewById(R.id.imgBtnUserInfo);
        ImageButton imgBtnSetting = (ImageButton) findViewById(R.id.imgBtnMyOrderes);
        mImgBtnLogin = (ImageButton) findViewById(R.id.imgBtnLogin);
        mImgBtnLogout = (ImageButton) findViewById(R.id.imgBtnLogout);

        //将各图像按钮注册到myImageButton点击事件监听器
        imgBtnRest.setOnClickListener(new myImageButtonListener());
        imgBtnTakeout.setOnClickListener(new myImageButtonListener());
        imgBtnUserInfo.setOnClickListener(new myImageButtonListener());
        imgBtnRest.setOnClickListener(new myImageButtonListener());
        mImgBtnLogin.setOnClickListener(new myImageButtonListener());
        mImgBtnLogout.setOnClickListener(new myImageButtonListener());
    }

    private ArrayList<Dish> FillDishesList() {
        ArrayList<Dish> theDishesList = new ArrayList<Dish>();
        Dish theDish = new Dish();
        //添加菜品
        theDish.mId = 1001;
        theDish.mName = "宫保鸡丁";
        theDish.mPrice = (float) 20.0;
        theDish.mImage = (R.raw.food01gongbaojiding);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1002;
        theDish.mName = "椒盐玉米";
        theDish.mPrice = (float) 24.0;
        theDish.mImage = (R.raw.food02jiaoyanyumi);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1003;
        theDish.mName = "清蒸武昌鱼";
        theDish.mPrice = (float) 48.0;
        theDish.mImage = (R.raw.food03qingzhengwuchangyu);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1004;
        theDish.mName = "鱼香肉丝";
        theDish.mPrice = (float) 20.0;
        theDish.mImage = (R.raw.food04yuxiangrousi);
        theDishesList.add(theDish);
        return theDishesList;
    }

    public class myImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgBtnRest:
                    Toast.makeText(MainActivity.this, "单击了点餐按钮!", Toast.LENGTH_LONG).show();
                    return;
                case R.id.imgBtnTakeout:
                    Toast.makeText(MainActivity.this, "单击了外卖按钮!", Toast.LENGTH_LONG).show();
                    return;
                case R.id.imgBtnLogin://用户未登录时该按钮才会出现
                    final LoginDialog loginDlg = new LoginDialog(MainActivity.this);
                    loginDlg.show();
                    loginDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            switch (loginDlg.mBtnClicked) {
                                case BUTTON_OK:
                                    MyApplication appInstance = (MyApplication) getApplication();
                                    if (appInstance.g_user.mUserid.equals(loginDlg.mUserId) && appInstance.g_user.mPassword.equals(loginDlg.mPsword)) {
                                        appInstance.g_user.mIslogined = true;
                                        mImgBtnLogin.setVisibility(Button.GONE);
                                        mImgBtnLogout.setVisibility(Button.VISIBLE);
                                        appInstance.g_cart = new ShoppingCart(appInstance.g_user.mUserid);
                                        if (loginDlg.mIsHoldUserId) {
                                            //保存用户名
                                        } else {
                                            //清除保存的用户名
                                        }
                                        Toast.makeText(MainActivity.this, "login success!", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "wrong id or password !", Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case BUTTON_REGISTER:
                                    //跳转到注册页面
                                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                                    startActivityForResult(intent, REGISTERACTIVITY);
                                    break;
                            }
                        }

                    });


                    return;
                case R.id.imgBtnUserInfo:
                    Toast.makeText(MainActivity.this, "单击了用户信息按钮!", Toast.LENGTH_LONG).

                            show();
                    return;
                case R.id.imgBtnLogout: //用户登录后该按钮才会出现
                    //隐藏“注销”按钮，显示“登录”按钮
                    mImgBtnLogout.setVisibility(Button.GONE);
                    mImgBtnLogin.setVisibility(Button.VISIBLE);
                    return;
            }

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REGISTERACTIVITY:
                if(resultCode == Activity.RESULT_OK){
                    String userid = data.getStringExtra("user");
                    String userpsd = data.getStringExtra("password");
                    String userphone = data.getStringExtra("phone");
                    String useraddress = data.getStringExtra("address");
                    mAppInstance.g_user.mUserid= userid;
                    mAppInstance.g_user.mPassword = userpsd;
                    mAppInstance.g_user.mUserphone= userphone;
                    mAppInstance.g_user.mUseraddress = useraddress;

                }
                break;
        }

    }


}
