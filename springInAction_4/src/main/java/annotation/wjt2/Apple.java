/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation.wjt2;

import lombok.Data;
import lombok.ToString;

/**
 * @author jintao.wang  Date: 17-9-12 Time: 下午6:27
 */
@Data
@ToString
@FruitName
public class Apple {
    @FruitName(value = "红富士")
    private String appleName;
    @FruitColor(value = FruitColor.Color.BLUE)
    private String appleColor;
    @FruitProvider(id=1,name = "陕西红富士果业有限公司",address = "陕西省西安市雁塔区天津路53号")
    private String appleProvider;

    @FruitName("苹果")
    public String getName(){
     /*   return "灵宝苹果";*/
     return null;
    }


    @FruitColor(value = FruitColor.Color.BLUE)
    public FruitColor.Color getColor(){
        return FruitColor.Color.RED;
    }
}
    