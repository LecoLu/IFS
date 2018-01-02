package xyz.ncvt.ifs.adapter;

import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.entity.Control;
import xyz.ncvt.ifs.servers.NetThread;


import java.util.List;

public class ControlAdapter extends ArrayAdapter {
    private List<Control> controls;
    private Context context;
    private int resource;

    private Handler handler;

    public ControlAdapter(Context context, int resource, List objects,Handler handler) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.handler = handler;
        this.controls = objects;
    }


    @Override
    public int getCount() {
        return controls.size();
    }

    @Override
    public Object getItem(int arg0) {
        return controls.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    int[] ons = {R.drawable.blower_on, R.drawable.water_pump_on, R.drawable.roadlamp_on, R.drawable.buzzer_on};
    int[] offs = {R.drawable.blower_off, R.drawable.water_pump_off, R.drawable.roadlamp_off, R.drawable.buzzer_off};

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHoler viewHoler;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, null);
            viewHoler = new ViewHoler();
            viewHoler.tvName = view.findViewById(R.id.tv_control_item_name);
            viewHoler.imgStatus = view.findViewById(R.id.img_cotrol_item_status);
            viewHoler.tvStatus = view.findViewById(R.id.tv_control_item_status);
            viewHoler.btnChangeStatus = view.findViewById(R.id.btn_control_item_changeStatus);
            view.setTag(viewHoler);
        } else {
            view = convertView;
            viewHoler = (ViewHoler) view.getTag();
        }

        final Control control = controls.get(position);
        //根据数据设置视图
        viewHoler.tvName.setText(control.getName());
        if (control.getValue() == true) {
            viewHoler.tvStatus.setText("开");
            viewHoler.btnChangeStatus.setText("关");
            viewHoler.imgStatus.setBackgroundResource(ons[position]);
        } else {
            viewHoler.tvStatus.setText("关");
            viewHoler.btnChangeStatus.setText("开");
            viewHoler.imgStatus.setBackgroundResource(offs[position]);
        }

        //设置按钮点击事件
        viewHoler.btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.10.100:8000/show/setcontrols/?key="+ control.getKey() +"&value=" + (control.getValue()?0:1);
                Log.i("url",url);
                new NetThread(handler, url,"setControl").start();
            }
        });
        return view;
    }
    private class ViewHoler {
        TextView tvName;
        ImageView imgStatus;
        TextView tvStatus;
        Button btnChangeStatus;
    }

}
