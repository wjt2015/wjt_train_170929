package com.qunar.fresh2017.util;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by linux2014 on 17-6-13.
 */
/*类TextReader用于读取文件内容*/
public class TextReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextReader.class);

    /*text保存便于在网页显示的文本内容*/
    private String text;
    private Map<String,String> map;
    private LineProcessorImp lineProcessor;
    private File f;


    public TextReader(LineProcessorImp lineProcessor,File f){
        this.lineProcessor = lineProcessor;
        this.f = f;
    }
    /*将指定的文件内容以<K,V>对的形式存储，并将文件内容按行存储在text内*/
    public void readText(){
        try {/*由于文件容量<1k，故用Files.readLines方法读取*/
             Files.readLines(f, Charsets.UTF_8,lineProcessor);
        } catch (IOException e) {
            LOGGER.error("IOException happens!!",e);
        }
        map = lineProcessor.getMap();
        text = lineProcessor.getResult();
    }

    public String getText() {
        return text;
    }

    public Map<String, String> getMap() {
        return map;
    }
}


