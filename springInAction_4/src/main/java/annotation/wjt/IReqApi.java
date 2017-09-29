/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation.wjt;

/**实例,(http://www.cnblogs.com/whoislcj/p/5671622.html)
 * @author jintao.wang  Date: 17-9-7 Time: 下午8:19
 */
public interface IReqApi {
    @ReqType(reqType = ReqTypeEnum.POST)
    @ReqUrl(reqUrl = "http://www.cnblogs.com")
    String login(@ReqParam(value = "userId")String userId,@ReqParam(value = "pwd")String pwd);
}



    