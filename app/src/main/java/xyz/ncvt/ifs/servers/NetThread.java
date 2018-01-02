package xyz.ncvt.ifs.servers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_FAIL;
import static xyz.ncvt.ifs.utils.Configure.MSG_WHAT_SUCCESS;

public class NetThread extends Thread {
    private Handler handler;
    private String action = "";
    private Message message = new Message();
    private String specUrl;

    public NetThread(Handler handler, String specUrl, String action){
        this.handler = handler;
        this.specUrl = specUrl;
        this.action = action;
    }

    @Override
    public void run() {
        super.run();
        HttpURLConnection connection;
        BufferedReader reader;
        try {
            //"http://192.168.10.100:8000/show/ie"
            URL url = new URL(specUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(8000);
            connection.setRequestProperty("contentType", "application/json; charset=utf-8");
            Log.i("ResponseCode", action + connection.getResponseCode());
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                response.append(line);
            }
            //处理返回的数据
            if ("controls".equals(action) || "setControl".equals(action)) {
                Log.i("control","11111111111111111111");
                JSONArray jsonArray = new JSONArray(response.toString());
                message.what = MSG_WHAT_SUCCESS;
                message.obj = jsonArray;
            } else {
                JSONObject jsonObject = new JSONObject(response.toString());
                message.what = MSG_WHAT_SUCCESS;
                message.obj = jsonObject;
            }
            message.what = MSG_WHAT_SUCCESS;
        } catch (SocketTimeoutException timeExcep) {
            message.what = MSG_WHAT_FAIL;
            message.obj = "请求服务器超时";
        }catch (ProtocolException pe) {
            message.what = MSG_WHAT_FAIL;
            message.obj = "获取资讯超时";
        } catch (IOException e) {
            message.what = MSG_WHAT_FAIL;
            message.obj = "服务器无响应";
            //e.printStackTrace(); 可能是没有申请联网权限
        }catch (JSONException jsonExcep){
            message.what = MSG_WHAT_FAIL;
            message.obj = "数据格式出错";
        }
        sendMess();

    }

    private void sendMess() {
        handler.sendMessage(message);
    }

}
