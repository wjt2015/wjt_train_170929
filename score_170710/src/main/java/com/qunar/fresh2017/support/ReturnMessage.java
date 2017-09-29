package com.qunar.fresh2017.support;

import lombok.Data;

/**
 * Created by lj on 17-6-24.
 */
@Data
public class ReturnMessage {
    /**
     * 返回状态,0:成功,-1:失败
     */
    private int status;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    private ReturnMessage(int status,String message,Object data){
        this.status=status;
        this.data=data;
        this.message=message;
    }
    public static ReturnMessage getReturnMessage(int status,String message,Object data){
        return new ReturnMessage(status,message,data);
    }
}
