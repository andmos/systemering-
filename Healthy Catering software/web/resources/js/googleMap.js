

window.google = window.google || {};
google.maps = google.maps || {};
(function() {
  
  function getScript(src) {
    document.write('<' + 'script src="' + src + '"' +
                   ' type="text/javascript"><' + '/script>');
  }
  
  var modules = google.maps.modules = {};
  google.maps.__gjsload__ = function(name, text) {
    modules[name] = text;
  };
  
  google.maps.Load = function(apiLoad) {
    delete google.maps.Load;
    apiLoad([0.009999999776482582,[[["https://mts0.googleapis.com/vt?lyrs=m@215000000\u0026src=api\u0026hl=no-NO\u0026","https://mts1.googleapis.com/vt?lyrs=m@215000000\u0026src=api\u0026hl=no-NO\u0026"],null,null,null,null,"m@215000000"],[["https://khms0.googleapis.com/kh?v=128\u0026hl=no-NO\u0026","https://khms1.googleapis.com/kh?v=128\u0026hl=no-NO\u0026"],null,null,null,1,"128"],[["https://mts0.googleapis.com/vt?lyrs=h@215000000\u0026src=api\u0026hl=no-NO\u0026","https://mts1.googleapis.com/vt?lyrs=h@215000000\u0026src=api\u0026hl=no-NO\u0026"],null,null,"imgtp=png32\u0026",null,"h@215000000"],[["https://mts0.googleapis.com/vt?lyrs=t@131,r@215000000\u0026src=api\u0026hl=no-NO\u0026","https://mts1.googleapis.com/vt?lyrs=t@131,r@215000000\u0026src=api\u0026hl=no-NO\u0026"],null,null,null,null,"t@131,r@215000000"],null,null,[["https://cbks0.googleapis.com/cbk?","https://cbks1.googleapis.com/cbk?"]],[["https://khms0.googleapis.com/kh?v=75\u0026hl=no-NO\u0026","https://khms1.googleapis.com/kh?v=75\u0026hl=no-NO\u0026"],null,null,null,null,"75"],[["https://mts0.googleapis.com/mapslt?hl=no-NO\u0026","https://mts1.googleapis.com/mapslt?hl=no-NO\u0026"]],[["https://mts0.googleapis.com/mapslt/ft?hl=no-NO\u0026","https://mts1.googleapis.com/mapslt/ft?hl=no-NO\u0026"]],[["https://mts0.googleapis.com/vt?hl=no-NO\u0026","https://mts1.googleapis.com/vt?hl=no-NO\u0026"]],[["https://mts0.googleapis.com/mapslt/loom?hl=no-NO\u0026","https://mts1.googleapis.com/mapslt/loom?hl=no-NO\u0026"]],[["https://mts0.googleapis.com/mapslt?hl=no-NO\u0026","https://mts1.googleapis.com/mapslt?hl=no-NO\u0026"]],[["https://mts0.googleapis.com/mapslt/ft?hl=no-NO\u0026","https://mts1.googleapis.com/mapslt/ft?hl=no-NO\u0026"]]],["no-NO","US",null,0,null,null,"https://maps.gstatic.com/mapfiles/","https://csi.gstatic.com","https://maps.googleapis.com","https://maps.googleapis.com"],["https://maps.gstatic.com/intl/no_no/mapfiles/api-3/12/10","3.12.10"],[1298973780],1.0,null,null,null,null,0,"",null,null,1,"https://khms.googleapis.com/mz?v=128\u0026","AIzaSyC6vTdCo7M8ojtba6-pkh5mpWrqHPLdHqM","https://earthbuilder.googleapis.com","https://earthbuilder.googleapis.com",null,"https://mts.googleapis.com/vt/icon"], loadScriptTime);
  };
  var loadScriptTime = (new Date).getTime();
  getScript("https://maps.gstatic.com/intl/no_no/mapfiles/api-3/12/10/main.js");
})();
