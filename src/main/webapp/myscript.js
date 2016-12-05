/**
 * 
 */

function myFunction() {
	
	var url = new XMLHttpRequest();
	url.open("GET", "/js/openstack/getmeetings", false);
	url.send();
 
	var content = url.response;
	
	
	var table = document.getElementById("meetings-table");
	var year; 
	var num_meetings;
	
	var d = content.split("/");
	var count = 1;
	for (var i = 0; i <= d.length-1; i++) {
		if(d[i+1] != null) {
			year = d[i];
			num_meetings = d[i+1];
			var row = table.insertRow(count);
			count++;
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			cell1.innerHTML = year;
			cell2.innerHTML = num_meetings;			
		}
		i++;
	}
	
	document.getElementById("meetings-header").innerHTML = "";
    document.getElementById("meetings-table-div").style.visibility = "visible";
}