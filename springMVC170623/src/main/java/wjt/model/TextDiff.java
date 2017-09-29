package wjt.model;

import lombok.*;

import java.util.Date;

/**
 * Created by linux2014 on 17-6-23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TextDiff {
    private Integer id;
    private Date date;
    private String srcText;
    private String targetText;
    private String diffText;

//    @Override
//    public String toString(){
//        return "{" + id + "," + date + "," + srcText + "," + targetText + "," +diffText + "}";
//    }
}

