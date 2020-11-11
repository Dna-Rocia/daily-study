package com.daily.export.constant;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class ExportConst {

    public static Integer PER_NUM_2W = 20000;

    /*******************************************************************************************************************
     * 订单流水
     */
    public static String[] TITLE_ORDER_RECORD = new String[]{
            "序号", "学员编号", "姓名", "电话", "订单id", "卡类型", "课程名称", "上课老师","排课老师", "办卡校区", "消费产生时间", "课程开始时间", "课程结束时间",
            "期初余额", "期初实缴余额","期初折扣余额", "课程费用", "实缴费用", "折扣额", "课程时长", "节次", "期末余额", "期末实缴余额",
            "消费类型", "课程状态","教学计划", "教学计划上传时间", "发票号", "上课老师所属公司","排课老师所属公司","订单所属公司"
    };
    public static String[] THEAD_ORDER_RECORD = new String[]{
            "sequence","studentId","studentName","studentMobile","courseOrderId","feeGradeName","courseDetailName"," attendTeacherName","arrangeTeacherName",
            "oldSchoolName","costTime","courseStartTime","courseEndTime","startBalance","startCostBalance","startDiscountBalance","coursePrice","courseCostPrice",
            "courseDiscountPrice","coursePracticalDuration","section","endOrderBalance","endCostBalance","consumeType","classStatus","planStatus","planUploadTime",
            "invoicesNo","bookingCompanyTagName","bookingDetailCompanyTagName","companyTagName"
    };


}
