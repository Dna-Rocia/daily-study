package com.daily.export.els;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/6
 */
public class Temp {

    //  执行缺失消费数据
//    @RequestMapping("/handleConsumeMsg")
//    @ResponseBody
//    public ResultObject handleConsumeMsg() {
//        try {
//            String[] sr = new String[]{
//            };
//            for (String s:sr) {
//                String[] arr = s.split("\\$");
//                int j = this.finHandleProducerService.handleConsumeMsg(arr[1],arr[0]);
//                resultObject.setData(j);
//                resultObject.setMessage("执行缺失消费数据，重新消费，累计："+j);
//                Thread.sleep(3000);
//            }
//
//        } catch (Exception e) {
//            LogWriter.writeErrorLog("处理队列消息异常",e);
//            resultObject.setRetcode(ApiResponseConstant.API_RESQUEST_FAIL);
//            resultObject.setErrcode(ApiResponseConstant.API_UNKNOWN);
//            resultObject.setMessage("处理队列消息异常");
//        }
//        return resultObject;
//    }
}
