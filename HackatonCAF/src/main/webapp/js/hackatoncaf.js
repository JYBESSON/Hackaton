(function() {

  // icon pour les markers en fonction du niveau (score de la ville)
  var LEVELS = ["data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABf0lEQVQoU4WSTyjDYRjHP+/PjE2zyAjFuKBNCqWtlAMnRZI4KVe1dqE4yMGBRI4uclTk5MCRg+Xg34pWDqZY2Swas7Zlv00/7adZtr2H98/T5/t93ud5X0F6GCRWSyWmarUUyymk4BexOGy/J5hTEKFMrSXsD5sYXGmiVBUq66yX2OEbB54o40Jxmq7DkQ2pghkv8a0AG6JaQzBgoyrTKXtfdcab6Cjj3d1JeT7Qek5YWHR83HZjyAe2nfMpajS8+G2YCqR+FUYNK45anEvmvxWrwvkH4pt+1n7a06zFddiOvUX/19cTgSEPp/cxen9AYMCqZ+umi4ZM1HKBzxNlEjhWQbSCo50WOkdNVCvw7gvBiTtcwMjvy6RdzMYiLkN2KpVzuYtQOEk74MsGMUosLzbilFOw9Mj6R5IF9Sq/qdOB/h4De/EksjvCGHCSCzRXFHGdAikkYwWecoFKXAaSQHFmB7JToxM8K2A0RX1eUANXCfgCevKCGQX0FQL//R/fS+VrlatLQvcAAAAASUVORK5CYII=",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABTUlEQVQoU4XSPUhcURCG4edKlggmkohaGIhisNPGRhAkgqZKJaKxEmwECyN2IlhKRBQFLYOtRISAjZAIWmRLtU8RCw2CYuLPCq7BRe5dNqsXdz3VmeGd7zszZwK5U2ZawoBKCRkl/rpybcmlsRAJIq7WqnbvDSv9Xxhe5l1JWrPnQyBU6jbsYwzKVcxL+2ou8MKxDZX3lOJBhz+BBmeWlRcFe10E6p1b8bwo2C0VeOnId1WPWJ+EzUzpM2KoQDOL0lbNZMdTI2lBq9qY7i+M+uG3tizIO2989sXre2iPA3v6sZkDeWLdpGYdqiP4m2PjkujK/0xWps4z27ZURNFbpy414SAOUuaTQSNusGRWykTuKXnrbKZToxXXbvzUg61CYJ1yu9H2pDRivxAY5kPjDBJ3JxC35qnDCEx7VRwssSPjH1qKg/kG2h8DH9yPWxORTVRHy5mwAAAAAElFTkSuQmCC"]
  // URL du service utilisé pour retrouver la list des villes avec leur position, niveau etc
  // FIXME : changer cette URL quand le service sera implementé.
  // retrait du '/' au début pour passer en relatif
  var SERVICE_URL = "communes", YEAR = "2014";

  var map, markers = [];
  
  // On utilise la localisation de la ville de Bourges pour centrer la carte de France.
  var bourgesLoc = {lat:47.081012, lng:  2.398782}; // Bourges

  /**
   * Initialisation de la carte.
   */
  function initMap() {

    // Initialisation de la Map
    map = new google.maps.Map(document.getElementById("map"), {
      zoom: 7,
      center: bourgesLoc,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      panControl: false, 
      mapTypeControl: false, 
      streetViewControl: true
    });
    addYearFilter(map);
    displayMarkers(YEAR);
  }

  function displayMarkers(year) {
    // Suppression des anciens markers
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
    // Appel du service pour recuperer les villes + creer les markers
    var url = SERVICE_URL + "?annee=" + year;
    $.ajax({
      url : url,
      type : "GET",
      dataType: "json",
      success: function(data, textStatus) {
        for (var i = 0; i < data.length; i++) {
          var commune = data[i], loc = commune.loc, score = commune.score;
          if (loc && score) {
            addMarker(map, loc, score < 100 ? LEVELS[0] : LEVELS[1])
          }
        }
      },
      error: function(qXHR, textStatus, errorThrown) {
        alert(errorThrown)
      }
    });
  }
  
  function addYearFilter(map) {
    var yearFilter = document.getElementById("year-filter"), 
        dropdown = yearFilter.childNodes[1], 
        items = dropdown.getElementsByTagName("div");
    
    function addListener(items, i) {
      var item = items[i];
      item.onclick = function() {
        var year = item.innerHTML;
        displayMarkers(year);
        for (var j = 0; j < items.length; j++) {
          var it = items[j];
          if (i == j) $(it).addClass("button-selected");
          else $(it).removeClass("button-selected");
        }
      }
    }
    
    for (var i = 0; i < items.length; i++) {
      addListener(items, i);
    }
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(yearFilter);
  }

  /**
   * Creation d'un marker sur la carte a la position donnée en utilisant l'icone donné.
   */
  function addMarker(map, position, icon) {
    var marker = new google.maps.Marker({
        position: position,
        map: map,
        icon: icon
    });
    markers.push(marker);
    google.maps.event.addListener(marker, 'click', function() {
      drawChart(this);
    }); 
  }

  /**
   * Creation d'un graphique.
   */
  function drawChart(marker) {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows([
      ['Crêches', 3],
      ['Ecoles', 1],
      ['Pharmacies de garde', 1],
      ['PAJE', 1],
      ['Pédiatres', 2]
    ]);

    // Set chart options
    var options = {'title':'Score ville idéale pour jeunes parents',
                 'width':400,
                 'height':150};
                 
    var node        = document.createElement('div'),
      infoWindow  = new google.maps.InfoWindow(),
      chart       = new google.visualization.PieChart(node);
      
      chart.draw(data, options);
      infoWindow.setContent(node);
      infoWindow.open(marker.getMap(),marker);
  }

  // Chargement de la librarie Charts de Google.
  google.load('visualization', '1.0', {'packages':['corechart']});
  google.maps.event.addDomListener(window, 'load', initMap);
}());