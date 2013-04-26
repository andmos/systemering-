var directionDisplay, map;
var directionsService = new google.maps.DirectionsService();
var geocoder = new google.maps.Geocoder();

function initialize() {
    // set the default center of the map
    var latlng = new google.maps.LatLng(63.429469, 10.389029);
    // set route options (draggable means you can alter/drag the route in the map)
    var rendererOptions = {draggable: true};
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    // set the display options for the map
    var myOptions = {
        zoom: 13,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: false
    };
    // add the map to the map placeholder
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    // bind the map to the directions
    directionsDisplay.setMap(map);
    // point the directions to the container for the direction details
    directionsDisplay.setPanel(document.getElementById("directionsPanel"));
    // start the geolocation API
    if (navigator.geolocation) {
        // when geolocation is available on your device, run this function
        navigator.geolocation.getCurrentPosition(foundYou, notFound);
    } else {
        // when no geolocation is available, alert this message
        alert('Geolocation not supported or not enabled.');
    }
}

//Initialize the map
google.maps.event.addDomListener(window, 'load', initialize);

function notFound(msg) {
    alert('Could not find your location :(')
}

function foundYou(position) {
    // convert the position returned by the geolocation API to a google coordinate object
    var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    // then try to reverse geocode the location to return a human-readable address
    geocoder.geocode({'latLng': latlng}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            // if the geolocation was recognized and an address was found
            if (results[0]) {
                // add a marker to the map on the geolocated point
                marker = new google.maps.Marker({
                    position: latlng,
                    map: map
                });
                // compose a string with the address parts
                var address = results[0].address_components[1].long_name + ' ' + results[0].address_components[0].long_name + ', ' + results[0].address_components[3].long_name
                // set the located address to the link, show the link and add a click event handler
                $('.autoLink span').html(address).parent().show().click(function() {
                    // onclick, set the geocoded address to the start-point formfield
                    $('#routeStart').val(address);
                    // call the calcRoute function to start calculating the route
                    calcRoute();
                });
            }
        } else {
            // if the address couldn't be determined, alert and error with the status message
            alert("Geocoder failed due to: " + status);
        }
    });
}

function calcRoute() {
    
    // get the travelmode, startpoint and via point from the form
    var start, end, via;
    var travelMode = "DRIVING";
    var elar = [];
    start = "HIST - høgskolen i Sør-Trøndelag, E C Dahls gt 2, 7012 Trondheim";

    var waypoints = []; // init an empty waypoints array

    elar = document.getElementsByClassName("stops") //creates an array of the hiddenfields with class name ="stops"
           
//runs trough the entire elar array and adds a waypoint if the type are hidden
    for (var i = 0; i < elar.length; i++) {
        if (elar[i].type == "hidden") {
            via = elar[i].value;
            waypoints.push({ //pushing waypoints
                location: via, // the actual waypoint "via"
                stopover: true
            });
        }
    }

    end = start;

    var request = {
        origin: start,
        destination: end,
        waypoints: waypoints,
        unitSystem: google.maps.UnitSystem.IMPERIAL,
        travelMode: google.maps.DirectionsTravelMode[travelMode]
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            $('#directionsPanel').empty(); // clear the directions panel before adding new directions
            directionsDisplay.setDirections(response);
        } else {
            // alert an error message when the route could not be calculated.
            if (status == 'ZERO_RESULTS') {
                alert('No route could be found between the origin and destination.');
            } else if (status == 'UNKNOWN_ERROR') {
                alert('A directions request could not be processed due to a server error. The request may succeed if you try again.');
            } else if (status == 'REQUEST_DENIED') {
                alert('This webpage is not allowed to use the directions service.');
            } else if (status == 'OVER_QUERY_LIMIT') {
                alert('The webpage has gone over the requests limit in too short a period of time.');
            } else if (status == 'NOT_FOUND') {
                alert('At least one of the origin, destination, or waypoints could not be geocoded.');
            } else if (status == 'INVALID_REQUEST') {
                alert('The DirectionsRequest provided was invalid.');
            } else {
                alert("There was an unknown error in your request. Requeststatus: nn" + status);
            }
        }
    });
}