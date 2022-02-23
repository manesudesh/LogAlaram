package com.credsu.demo.alarmlog;

public class LogFile {
	int id;
	   // String state ;
	    long startTime;
	    long endTime;
	    String type;
	    String host;
	    
	    public LogFile(int id){
	        this.id = id;
	    }
	    
	    public int getId(){
	        return this.id;
	    }
	    
	    public void setType(String type){
	        this.type = type;
	    }
	    
	    public void setHost(String host){
	        this.host = host;
	    }
	    
	    public void setStartTime(long startTime){
	        this.startTime = startTime;
	    }
	    
	    public void setEndTime(long endTime){
	        this.endTime = endTime;
	    }
	    
	    public long getStartTime(){
	       return  this.startTime ;
	    }
	    
	    public long getEndTime(){
	       return this.endTime;
	    }
	    
	    public long getDiffTime(){
	        return this.endTime - this.startTime;
	    }
	    
	    public String getHost(){
	       return this.host;
	    }
	    
	    public String getType(){
		       return this.type;
		}
	    
	    @Override
	    public int hashCode()
	    {
	        return this.id;
	    }
	    @Override
	    public boolean equals(Object obj) {
	       if(obj instanceof LogFile){
	           if(this.id == ((LogFile)obj).id) return true;
	       }
	       return false;
	    }
	    
	    @Override
	    public String toString() {
	       return id+"   "+startTime+"   "+endTime+"  "+type+"   "+host;
	    }

}
