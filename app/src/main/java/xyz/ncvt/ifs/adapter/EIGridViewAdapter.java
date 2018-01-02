package xyz.ncvt.ifs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.entity.EnvironmentIndex;

import java.util.List;

public class EIGridViewAdapter extends ArrayAdapter {
    private Context context;
    private int resource;

    private View v;
    private ViewHoler viewHoler;
    public EIGridViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            v = LayoutInflater.from(context).inflate(resource,null);
            viewHoler = new ViewHoler();
            viewHoler.bg = v.findViewById(R.id.img_ie_bg);
            viewHoler.sensorName = v.findViewById(R.id.sensorName);
            viewHoler.sensorStatus = v.findViewById(R.id.sensorStatus);
            viewHoler.sensorValue = v.findViewById(R.id.sensorValue);
            v.setTag(viewHoler);
        } else {
            v = convertView;
            viewHoler = (ViewHoler) v.getTag();
        }
        EnvironmentIndex environmentIndex = (EnvironmentIndex) getItem(position);
        //Log.i("ei",environmentIndex.toString());
        viewHoler.bg.setBackground(ContextCompat.getDrawable(context, environmentIndex.getColor()));
        viewHoler.sensorName.setText(environmentIndex.getName());
        viewHoler.sensorStatus.setText(environmentIndex.getStatus());
        viewHoler.sensorValue.setText(environmentIndex.getValue()+"");
        return v;
    }

    private class ViewHoler{
        ImageView bg;
        TextView sensorName;
        TextView sensorStatus;
        TextView sensorValue;

    }
}
