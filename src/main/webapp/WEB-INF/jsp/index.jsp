<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/js/map.js"></script>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script type="text/javascript" src="/js/markerclusterer.js"></script>
<head>
</head>

<body style='border: 0; margin: 0'>


<script type="text/javascript">
		window.onload = initialize;

		function initialize() {
			
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 4,
				center : new google.maps.LatLng(51.6, -0.125),
				mapTypeId : google.maps.MapTypeId.ROADMAP
			});

			var locations = [];
			locations = JSON.parse('${locations}');

			var markers = [];
			for (var i = 0; i < locations.length; i++) {
				var marker = new google.maps.Marker({
					position : new google.maps.LatLng(locations[i][1],
							locations[i][2]),
					map : map
				});
				google.maps.event.addListener(marker, 'click', (function(
						marker, i) {
					return function() {
						infowindow.setContent(locations[i][0]);
						infowindow.open(map, marker);
					}
				})(marker, i));
				markers.push(marker);
			}
			var markerClusterer = new MarkerClusterer(map, markers, {});
		}
		
	</script>
	
		<div id="map" style="height: 700px; width: 100;"></div>

</body>
</html>