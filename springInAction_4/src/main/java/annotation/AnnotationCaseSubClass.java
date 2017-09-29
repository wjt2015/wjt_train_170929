/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author jintao.wang  Date: 17-9-7 Time: 下午7:28
 */
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationCaseSubClass extends AnnotationCase {
    @UserAnnotation(id = 1000,age = 20)
    private String name;

}


