 $.getJSON("http://localhost:3000/weather", function (json) {
  var a=[];
  var b=[]; 
  json.map(function(item) {       
		 a.push(item.date.slice(11,16));
  });
  json.map(function(item) {       
		 b.push(item.temp.toString().slice("",4));
  });
  var temp=[];
  var time=[];
  for(var i=0; i<10; i++ ){
	  time.push(a[i]);
	  temp.push(b[i]);
  }
  
  var data = {
    labels: time,
    datasets: [{
            data: temp,
	
            label: "My Second dataset",
			fillColor: "rgba(151,187,205,0.2)",
			strokeColor: "rgba(151,187,205,1)",
			pointColor: "rgba(151,187,205,1)",
			pointStrokeColor: "#fff",
			pointHighlightFill: "#fff",
			pointHighlightStroke: "rgba(151,187,205,1)",               
        }]
  };

  var ctx = document.getElementById("myChart").getContext("2d");
  ctx.canvas.width = 1000;
  ctx.canvas.height = 800;

  var myChart = new Chart(ctx).Line(data);
});