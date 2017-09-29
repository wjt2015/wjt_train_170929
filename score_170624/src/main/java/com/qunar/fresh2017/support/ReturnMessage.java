package com.qunar.fresh2017.support;

import lombok.Data;

/**
 * Created by lj on 17-6-24.
 */
@Data
public final class ReturnMessage {

    private int status;//状态码

    private String message;

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
