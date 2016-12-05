package js;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Path("/openstack")
public class OpenStackMeetingsResource {
	
	
	public OpenStackMeetingsResource() {
	}
	
	@GET
	@Path("/helloworld")
	@Produces("text/html")
	public String helloWorld() {
		System.out.println("Inside helloworld");
		return "Hello world ";
	}
	
	
	@GET
	@Path("/getmeetings")
	@Produces("text/html")
	public String getMettings() {
		String meetings = getMeetingsList();
		return meetings;
	}
	
	
	
	
	public String getMeetingsList() {
	
		String source = "http://eavesdrop.openstack.org/meetings/solum_team_meeting/";
		
		//ArrayList<Meeting> meetingsList = new ArrayList<Meeting>();
		ArrayList<String> yearsList = getNumYears(); 
		String meetingString = "";
		
		for (int i = 0; i < yearsList.size(); i++) {
			Integer count = getCount(source+yearsList.get(i));
			//Meeting meeting = new Meeting(yearsList.get(i), count.toString());	
			meetingString += yearsList.get(i) + count.toString() + "/";
			
			
		}
		//System.out.println(meetingString);
		return meetingString;
	}
	
	
	public ArrayList<String> getNumYears() {
		ArrayList<String> years = new ArrayList<String>();
		
		String source = "http://eavesdrop.openstack.org/meetings/solum_team_meeting/";
		
		Document doc;
		try {
			doc = Jsoup.connect(source).get();
		}
		catch (Exception exp) {
			exp.printStackTrace();
			return null;
		} 
		try {	
			doc = Jsoup.connect(source).get();
			Elements links = doc.select("a");
			if(doc != null) {
		    	ListIterator<Element> iter = links.listIterator();
		    	while(iter.hasNext()) {
		    		Element e = (Element) iter.next();
		    		String year = e.html();
		    		if ( year != null 
		    				&& !year.equalsIgnoreCase("Name") 
		    				&& !year.equalsIgnoreCase("Last modified") 
		    				&& !year.equalsIgnoreCase("Size") 
		    				&& !year.equalsIgnoreCase("Description") 
		  					&& !year.equalsIgnoreCase("Parent Directory")) 
		    		{
		    			
		    			years.add(year);
		    		}
		    	}	
		    }
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
		return years;
	}
	
	
	private Integer getCount(String url) {
		HashSet<String> meetings = new HashSet<String>();
		Document doc;
		try {	
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("td");
			if(doc != null) {
		    	ListIterator<Element> iter = links.listIterator();
		    	while(iter.hasNext()) {
		    		Element e = (Element) iter.next();
		    		String prev_meeting = "";
		    		String meeting = e.text();
		    		if ( meeting != null 
		    				&& meeting.contains("solum")) 
		    		{
		    			//System.out.println("Meeting : " + meeting);
		    			String meetingDate = meeting.substring(19,29);
		    			//System.out.println("Meeting Date: " +meetingDate);
		    			meetings.add(meetingDate);
		    		}
		    	}	
		    }
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
			
		return (Integer) meetings.size();
	}
}
