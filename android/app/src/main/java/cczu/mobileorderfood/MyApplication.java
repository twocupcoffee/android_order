package cczu.mobileorderfood;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by f on 2018-04-24.
 */

public class MyApplication extends Application {
    MyUser g_user; //用户
    ShoppingCart g_cart; //与登录用户相关联的购物车
    ArrayList<Order> g_orders; //与登录用户相关联的订单
    Dishes g_dishes;  //菜品列表
    public String g_ip = "";       //TCP通讯时店面服务器IP地址
    public String g_http_ip = ""; //用HTTP通信时的店面IP地址
    public int g_communiMode = 1; //通信模式，1为TCP通信，2为HTTP通信
    public int g_objPort = 35885;    //店面服务器监听端口号
    Context g_context;
}
