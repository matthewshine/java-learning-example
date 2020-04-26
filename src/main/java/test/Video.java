package test;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Video {
    public static void main(String[] args) {
        String url = null;
        try {
            url = getRealSrcBvid("h0851061duv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(url);
    }

    static  String getRealSrcBvid(String vid) throws UnsupportedEncodingException, IOException {
        String url ="http://vv.video.qq.com/getinfo?vids="+vid+"&platform=101001&charge=0&otype=json&defn=shd";
        String line = "";
        StringBuffer sb = new StringBuffer();
        HttpURLConnection urlConnection = (HttpURLConnection)new URL(url).openConnection();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);// 网页传回的只有一行
            }
        }
        String json = sb.toString();
        System.out.println(json);
        int fvkey_index = json.indexOf("\"fvkey\":\"")+9;
        int endIndex = json.indexOf("\"",fvkey_index);
        String fvkey = json.substring(fvkey_index,endIndex);//获取到fvkey
        int fn_index = json.indexOf("\"fn\":\"")+6;
        int fn_end = json.indexOf("\"",fn_index);
        String fn = json.substring(fn_index,fn_end);//获取到视频文件名
        String head = "http://ugcws.video.gtimg.com/";
        StringBuffer real_url = new StringBuffer();
        real_url.append(head);//加入头部
        real_url.append(fn+"?");//加入文件名
        real_url.append("vkey="+fvkey);//加入解锁码
        /*构造成功*/
        return real_url.toString();
    }

}
