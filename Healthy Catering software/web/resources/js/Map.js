/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 63.44,10.37
 **/

function initialize()
{
    var mapProp = {
        center: new google.maps.LatLng(63.44, 10.37),
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("googleMap")
            , mapProp);
}



google.maps.event.addDomListener(window, 'load', initialize);