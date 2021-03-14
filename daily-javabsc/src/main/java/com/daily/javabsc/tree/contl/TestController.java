package com.daily.javabsc.tree.contl;

import com.daily.javabsc.tree.ClazzManagement;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import static com.google.common.collect.Lists.newArrayList;


import java.util.List;

public class TestController {

    public static void main(String[] args) {
//        Gson gson = new Gson();
//        String str = gson.toJson(loadTree());
//        System.out.println(str);
        System.out.println(3 * 0.1);
        System.out.println(0.3 * 3);
//        System.out.println(1+"a");
//        int a = 1;
//        System.out.println(a+"p");
    }


    private static List<ClazzManagement> loadTree() {
        String oldJson = "[{\"id\":\"6Ww63wiyn6aGnoPrVBFAtr\",\"name\":\"学生管理\",\"icon\":\"site-icon-category-MSG\",\"available\":true,\"orderLevel\":\"05\"},{\"id\":\"9SuSC2ZMX1YBS5bFgSJTuh\",\"name\":\"考勤管理\",\"icon\":\"\",\"url\":\"\",\"available\":true,\"orderLevel\":\"0504\",\"parentId\":\"6Ww63wiyn6aGnoPrVBFAtr\"},{\"id\":\"TgMu3K4bo6gXfBTvHHvCmV\",\"name\":\"即时考勤查看\",\"icon\":\"\",\"url\":\"/ea/attendance/attendance/todayAttendance\",\"available\":true,\"orderLevel\":\"050403\",\"parentId\":\"9SuSC2ZMX1YBS5bFgSJTuh\"},{\"id\":\"9kQkVBA3im1o4L2qRuiu4U\",\"name\":\"我的考勤\",\"icon\":\"\",\"url\":\"/ea/attendance/attendance/myAttendanceWithClass\",\"available\":true,\"orderLevel\":\"050404\",\"parentId\":\"9SuSC2ZMX1YBS5bFgSJTuh\"},{\"id\":\"212633835681361920\",\"name\":\"我的班级\",\"available\":true,\"orderLevel\":\"0511\",\"parentId\":\"6Ww63wiyn6aGnoPrVBFAtr\"},{\"id\":\"212634138052931584\",\"name\":\"班级信息\",\"url\":\"/base/user/student/class\",\"available\":true,\"orderLevel\":\"051101\",\"parentId\":\"212633835681361920\"},{\"id\":\"212634436817399808\",\"name\":\"审核信息\",\"url\":\"/base/user/student/apply\",\"available\":true,\"orderLevel\":\"051102\",\"parentId\":\"212633835681361920\"}]";
        List<ClazzManagement> cmList = jsonToList(oldJson);
        List<ClazzManagement> fatherList = newArrayList();
        for (ClazzManagement cm : cmList) {
            cm.setLeaf(false);
            if (StringUtils.isBlank(cm.getParentId())) {
                fatherList.add(cm);
            }
        }
        for (ClazzManagement father : fatherList) {
            father.setLevel(0);
            father.setChildren(getChild(father, cmList));
        }
        return fatherList;
    }


    private static List<ClazzManagement> jsonToList(String oldJson) {
        JSONArray jsonArray = JSONArray.fromObject(oldJson);
        JSONObject jsonObject;

        List<ClazzManagement> cms = newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            ClazzManagement cm = (ClazzManagement) JSONObject.toBean(jsonObject, ClazzManagement.class);
            cms.add(cm);
        }
        return cms;
    }

    private static List<ClazzManagement> getChild(ClazzManagement father, List<ClazzManagement> cmList) {
        List<ClazzManagement> childList = newArrayList();
        for (ClazzManagement cm : cmList) {
            if (StringUtils.isNotBlank(cm.getParentId())) {
                if (cm.getParentId().equals(father.getId())) {
                    cm.setLevel(father.getLevel() + 1);
                    childList.add(cm);
                }
            }
        }

        for (ClazzManagement cm : childList) {
            if (StringUtils.isBlank(cm.getUrl())) {
                cm.setChildren(getChild(cm, cmList));
            }
        }
        if (childList.size() == 0) {
            return newArrayList();
        }
        return childList;
    }

}
