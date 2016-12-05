package js;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement(name = "meeting")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meeting {

	private String year;
	private String count;


	public Meeting() {
		//default constructor
	}
	
	public Meeting(String year, String count) {
		this.year = year;
		this.count = count;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}

	
	
	
	
	

}