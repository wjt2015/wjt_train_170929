package com.qunar.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by linux2014 on 17-7-19.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListOnDuty {
    private Integer id;
    private String onDutyModule;
    private String qtalkList;
    private Integer idx;
}
