package xyz.ncvt.ifs.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;

import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.adapter.ControlAdapter;
import xyz.ncvt.ifs.adapter.EIGridViewAdapter;
import xyz.ncvt.ifs.entity.Control;
import xyz.ncvt.ifs.servers.NetThread;

import java.util.List;

import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_FAIL;
import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManualControlFragment extends Fragment {
    Handler h;
    ListView listView;

    public ManualControlFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manual_control, null);
        listView =  v.findViewById(R.id.listView_control);
        h = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_WHAT_SUCCESS) {
                    JSONArray jsonArray = (JSONArray) msg.obj;
                    Gson gson = new Gson();
                    List<Control> list = gson.fromJson(jsonArray.toString(), new TypeToken<List<Control>>(){}.getType());
                    ControlAdapter controlAdapter = new ControlAdapter(getActivity(), R.layout.control_item, list, h);
                    listView.setAdapter(controlAdapter);
                }else if (msg.what == MSG_WHAT_FAIL) {
                    if (msg.obj != null) {
                        Toast.makeText(getContext(), msg.obj + "", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "获取实时环境数据失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        };
        new NetThread(h,"http://192.168.10.100:8000/show/controls/","controls").start();
        return v;
    }

}
