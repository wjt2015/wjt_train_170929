/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation;

import lombok.AllArgsConstructor;

/**
 * @author jintao.wang  Date: 17-9-7 Time: 下午4:51
 */
@AllArgsConstructor
public class AnnotationCase {
    @UserAnnotation
    public Integer vi;
    public AnnotationCase(){
        vi = 1;
    }
}


