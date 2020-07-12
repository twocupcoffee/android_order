package cczu.mobileorderfood;
/**
 * Created by f on 2018-04-24.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaipinActivity extends Activity {

    static List<Map<String, Object>> mfoodinfo;
    public ListView mlistview;
    static SimpleAdapter mlistItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caipin_list);

        mlistview = (ListView) findViewById(R.id.ListViewCainpin);
        mfoodinfo=getFoodData();
        //构造SimpleAdapter适配器，将它和自定义的布局文件、List数据源关联
        mlistItemAdapter = new SimpleAdapter(this,mfoodinfo,//数据源
                R.layout.listitem,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"dishid", "image","title", "price", "order"},
                //ImageItem的XML文件里面的1个ImageView,3个TextView ID
                new int[] {R.id.dishid, R.id.img, R.id.title, R.id.price});

        mlistItemAdapter.notifyDataSetChanged();
        mlistview.setAdapter(mlistItemAdapter);

        //设置ListView选项点击监听器
        this.mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ListView templist = (ListView)arg0;
                View mView = templist.getChildAt(arg2);//选中子项(即item)在listview中的位置
                final TextView tvTitle = (TextView)mView.findViewById(R.id.title);
                Toast.makeText(CaipinActivity.this,tvTitle.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private ArrayList<Map<String, Object>> getFoodData()
    {
        ArrayList<Map<String, Object>> fooddata=new ArrayList<Map<String,Object>>();
        //将菜品信息填充进foodinfo列表
        MyApplication appInstance = (MyApplication)getApplication();
        int s = appInstance.g_dishes.GetDishQuantity(); //得到菜品数量
        for (int i=0; i<s; i++) {
            Dish theDish = appInstance.g_dishes.GetDishbyIndex(i); //得到当前菜品
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("dishid", theDish.mId);
            map.put("image", theDish.mImage);
            map.put("title", theDish.mName);
            map.put("price", theDish.mPrice);
            fooddata.add(map);
        }
        return fooddata;
    }

}
