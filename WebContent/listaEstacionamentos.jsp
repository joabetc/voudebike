<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>

<meta name="viewport"
	content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	crossorigin="anonymous"></script>

<style>
#map {
	height: 400px;
	width: 100%;
}
</style>

</head>

<script>
		
			$.ajax({
				crossOrigin: true, 
				url: "/Voudebike/EstacionamentoServlet?latitude=-25.4526806&longitude=-49.2461181" 
				,success: function(result){
					var jsonFile = result;
					for(var i = 0; i < jsonFile.listaEstacionamentos.length; i++){
						$( "ul" ).append('<li class="list-group-item">' + jsonFile.listaEstacionamentos[i].endereco + '</li>');
					}
					
				      var locations = [
				          {lat: -25.4526806, lng: -49.2461181},
				          {lat: -25.471051, lng: -49.2680434}
				      ]
				      
				      initMap(locations);
				}
				,error: function(xhr,status,error){
					console.log(error);
				}
			});
		
	</script>

	<script>

      function initMap(locations) {

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 12,
          center: locations[0]
        });

        // Create an array of alphabetical characters used to label the markers.
        var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        // Add some markers to the map.
        // Note: The code uses the JavaScript Array.prototype.map() method to
        // create an array of markers based on a given "locations" array.
        // The map() method here has nothing to do with the Google Maps API.
        var markers = locations.map(function(location, i) {
          return new google.maps.Marker({
            position: location,
            label: labels[i % labels.length]
          });
        });

        // Add a marker clusterer to manage the markers.
        var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
      }
    </script>

	<script
		src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
     </script>
     
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjm1RV5d-GkMhLyctiR8M_dQZQTjVbHEg&callback=initMap">
    </script>
    
	<body>
		<div id="map"></div>
		<ul class="list-group" style="color: green;">
		</ul>
	</body>
</html>