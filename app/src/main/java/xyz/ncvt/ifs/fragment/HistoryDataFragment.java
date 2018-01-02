package xyz.ncvt.ifs.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.servers.NetThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_FAIL;
import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryDataFragment extends Fragment {
    private View v;

    public HistoryDataFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history_data, container, false);
        //initView();
        //initEvent();
        return v;
    }

}
