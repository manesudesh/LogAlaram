package com.credsu.demo.alarmlog.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.credsu.demo.alarmlog.LogFile;
import com.credsu.demo.data.TableRecord;
import static com.credsu.demo.alarmlog.Constant.STARTED;
import static com.credsu.demo.alarmlog.Constant.FINISHED;
import static com.credsu.demo.alarmlog.Constant.MILI_SEC_LIMIT;;
public class FileUtility {
	
	
	public static void processFile(String fileName, ArrayList<LogFile> arr) throws IOException, ParseException{
		
		//read all lines of source file . 
		//Every line has json data structure	
		List <String> list = Files.lines(Paths.get(fileName)).toList();;
		for(String str : list) 
		{
			readFields( arr,  str);	
            		
		}
		filterLogContainer(arr, MILI_SEC_LIMIT);
	}
	
	//filter loggerAlarm record which was took more than 4 milisecond to process
	private static void filterLogContainer(ArrayList<LogFile> arr, int limit) {
		arr.stream().filter(e->e.getDiffTime()>limit).forEach(e-> {
			try {
				TableRecord.insertRecord(e);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		});
	}
	
	private static void readFields(ArrayList<LogFile> arr, String str) 
	{
		 
		try {
			JSONObject json = (JSONObject)new JSONParser().parse(str);
			String strId = (String)json.get("id");
		    String strState = (String)json.get("state");
		    String strType = (String)json.get("type");
		    String strHost = (String)json.get("host");
		    long longTimestamp = (Long)json.get("timestamp");
		    	    
		    
		    System.out.println("thread:"+strId+"  "+"name:"+strState+"timestamp:"+longTimestamp);
		    manipulateLogContainer(arr, strId, strState, strType, strHost, longTimestamp);
		   
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}
	
	//Every thread has two log e.g. Started and Finished thread log. 
	//If same id thread has already in collection then just to add details of second log detail to previous 
	private static void  manipulateLogContainer(ArrayList<LogFile> arr, String strId, String state, String type, String host, long timestamp)
	{
		LogFile e1 = new LogFile(Integer.valueOf(strId));
		if(arr.contains(e1)){
		    e1 = arr.get(arr.indexOf(e1));
		}else{
		    arr.add(e1);
		}
		
				
		if(state.equals(STARTED)){e1.setStartTime(Long.valueOf(timestamp));}
		else if(state.equals(FINISHED)){e1.setEndTime(Long.valueOf(timestamp));}
		
		if(e1.getType() == null && type != null && !type.equals("")){e1.setType(type);}
		if(e1.getHost() == null &&host != null && !host.equals("")){e1.setHost(host);}
	}

}
