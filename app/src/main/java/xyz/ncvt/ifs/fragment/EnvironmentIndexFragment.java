package xyz.ncvt.ifs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.adapter.EIGridViewAdapter;
import xyz.ncvt.ifs.entity.EnvironmentIndex;
import xyz.ncvt.ifs.entity.Sensor;
import xyz.ncvt.ifs.servers.NetThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_FAIL;
import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnvironmentIndexFragment extends Fragment {
    private View v;
    private GridView gridView;

    List<EnvironmentIndex> eiList = new ArrayList<>();

    Handler handler;
    boolean flag = true;


    public EnvironmentIndexFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_environment_index, container, false);
        initView();
        initEvent();
        return v;
    }

    public void initView() {

        gridView =  v.findViewById(R.id.gridView_ei);
    }
    public void initEvent() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_WHAT_SUCCESS) {
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    initData(jsonObject);
                    EIGridViewAdapter adapter = new EIGridViewAdapter(getContext(), R.layout.ie_gridview_item, eiList);
                    gridView.setAdapter(adapter);
                }else if (msg.what == MSG_WHAT_FAIL) {
                    if (msg.obj != null) {
                        Toast.makeText(getContext(), msg.obj + "", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "获取实时环境数据失败", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        };

        flag = true;
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(flag){
                    new NetThread(handler,"http://192.168.10.100:8000/show/ei/","ie").start();
                    try{
                        Thread.sleep(5000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void initData(JSONObject resultJson) {

        eiList = new ArrayList<>();

        Gson gson = new Gson();
        Sensor sensor = gson.fromJson(resultJson.toString(), Sensor.class);

        EnvironmentIndex co2 = new EnvironmentIndex("co2","二氧化碳",sensor.getCo2());
        eiList.add(co2);
        EnvironmentIndex airHumidity = new EnvironmentIndex("airHumidity","空气湿度",sensor.getAirHumidity());
        eiList.add(airHumidity);
        EnvironmentIndex airTemperature = new EnvironmentIndex("airTemperature","空气温度",sensor.getAirTemperature());
        eiList.add(airTemperature);
        EnvironmentIndex soilMoisturs = new EnvironmentIndex("soilMoisturs","土壤湿度",sensor.getSoilMoisturs());
        eiList.add(soilMoisturs);
        EnvironmentIndex soilTemperature = new EnvironmentIndex("soilTemperature","土壤温度",sensor.getSoilTemperature());
        eiList.add(soilTemperature);
        EnvironmentIndex light = new EnvironmentIndex("light","光照",sensor.getLight());
        eiList.add(light);
    }

}
