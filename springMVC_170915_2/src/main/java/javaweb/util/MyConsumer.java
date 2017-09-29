/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author jintao.wang  Date: 17-9-28 Time: 下午2:30
 */
@Getter
public class MyConsumer implements Consumer<String> {

    private List<String> stringList;

    public MyConsumer(){
        stringList = new LinkedList<String>();
    }

    public void accept(String s) {
        Preconditions.checkArgument(Strings.isNullOrEmpty(s));
        stringList.add(s);
    }
}
    