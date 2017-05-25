function getJsonData(){
	
    var today = new Date();
	var dd = today.getDate();
	
	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	var hour=today.getHours();
	var min=today.getMinutes();
	var sec=today.getSeconds();
	if(dd<10) 
	{
		dd='0'+dd;
	} 

	if(mm<10) 
	{
		mm='0'+mm;
	} 
	today =yyyy+'-'+ mm+'-'+dd+" "+hour+":"+min+":"+sec;
		
	var time=hour+":"+min+":"+sec;

    var uri="http://localhost:3000/api/"+today;
	 
    $.getJSON(uri, function (json) { 
       var a=json.temp_min;
	   var b=json.temp_min;
	   var temp_min=a.toString().slice("",5);
	   var temp_max=b.toString().slice("",5);
	   document.getElementById("p1").innerHTML="Timing="+time+"<br>Min temperature= "+temp_max+"<br> Max Temperature="+temp_min;
    });
 }
 
 getJsonData();
	