/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.verification;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author jintao.wang  Date: 17-9-20 Time: 下午4:50
 */

@Setter
@Getter
public class VerificationCode {

    private String verificationCode;

    private BufferedImage verificationImage;

    /*验证码来自该字符串*/
    private String codeSrc;

    private int width;

    private int height;

    public VerificationCode(){
        codeSrc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        produce();
    }

    public void produce(){
        width = 120;
        height = 40;
        verificationImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics2D g = (Graphics2D)verificationImage.getGraphics();

        /*设定背景*/
        g.setColor(getRandColor(200,255));
        g.fillRect(0,0,width,height);

        /*画干扰线*/
        g.setColor(getRandColor(160,200));
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        int  i;
        int n = 155;
        int w = 30;
        int h = 24;
        Random random = new Random();
        for (i = 0;i < n;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawLine(x,y,x + w,y + h);
        }

        /*生成四位验证码*/
        verificationCode = new String();
        char[] codeSrcArr = codeSrc.toCharArray();
        n = 4;
        for(i = 0; i < n; i++){
            char c = codeSrcArr[random.nextInt(codeSrcArr.length)];
            verificationCode += c;
            g.drawString("" + c,25 * i + 6,16);
        }

        /*图片生效*/
        g.dispose();

    }

    private Color getRandColor(int fc,int bc){
        if(fc > 255){
            fc = 255;
        }
        if(fc < 0){
            fc = 0;
        }

        if(bc > 255){
            bc = 255;
        }
        if(bc < 0){
            bc = 0;
        }

        if(fc >= bc){
            fc = 0;
            bc = 255;
        }

        Random random = new Random();

        int bound = bc - fc;
        int r = fc + random.nextInt(bound);
        int g = fc + random.nextInt(bound);
        int b = fc + random.nextInt(bound);
        Color color = new Color(r,g,b);

        return color;
    }
}
    