package com.daily.javabsc.bsc;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/20
 */
public class BscJson {

    public static void main(String[] args) {
        json1();
    }

    private void json2() {
        //JSONObject嵌套运用
        JSONObject e = new JSONObject();
        e.put("w", "luo");
        JSONObject json = new JSONObject();
        json.put("key", e);
        JSONObject temp = new JSONObject();
        temp.put("key", json);
        System.out.println(temp.getJSONObject("key").getJSONObject("key").get("w"));
    }

    private static void json1() {
        String res = "{\"0-10000\":3,\"10001-39999\":6,\"40000-99999999\":9}";
        BigDecimal a = new BigDecimal(18000);
        Map json = JSONObject.parseObject(res);
        Iterator<Map.Entry> irt = json.entrySet().iterator();
        while (irt.hasNext()) {
            Map.Entry<String, Object> entry = irt.next();
            String[] keyArr = entry.getKey().toString().split("-");
            if (a.compareTo(new BigDecimal(keyArr[0])) > 0 && a.compareTo(new BigDecimal(keyArr[1])) < 0) {
                System.out.println("当前值：" + a + ", 延长梯度：" + entry.getValue());
                break;
            }
        }

//        JSONArray jsonArray = json.getJSONArray("critical");
//        List<Critical> criticals = new ArrayList<>();
//
//        for (Object s:jsonArray) {
//            JSONObject jsonObject = JSONObject.parseObject(s.toString());
//            String[] keyArr = jsonObject.get("key").toString().split("-");
//            int j = 0;
//            for (String k: keyArr) {
//                if (!k.equals("0")){
//                    if (k.equals("Nan")){
//                        keyArr[1] = "0";
//                    }
//                    j++;
//                }
//            }
//            if (j == 2) {
//                Critical critical = new Critical(new BigDecimal(keyArr[0]),new BigDecimal(keyArr[1]) , true);
//                criticals.add(critical);
//            }else if (j == 1){
//                Critical critical = new Critical(new BigDecimal(keyArr[0]),new BigDecimal(keyArr[1]) , false);
//                criticals.add(critical);
//            }
//        }
//        System.out.println(criticals);
    }

}
