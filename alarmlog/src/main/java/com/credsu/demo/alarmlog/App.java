package com.credsu.demo.alarmlog;

import static com.credsu.demo.alarmlog.Constant.SOURCE_FILE;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;

import com.credsu.demo.alarmlog.utility.FileUtility;
import com.credsu.demo.data.TableCreate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
            
        File file = new File(new App().getClass().getClassLoader().getResource(SOURCE_FILE).getFile());
        
       String fileName = URLDecoder.decode(file.getAbsolutePath(), "UTF-8");
        
       
        TableCreate tc = new TableCreate();
        tc.createTable();
        FileUtility.processFile(fileName,  new ArrayList<LogFile>()); 
    }
}
