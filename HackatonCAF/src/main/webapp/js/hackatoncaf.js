(function() {

  // icon pour les markers en fonction du niveau (score de la ville)
  var LEVELS = ["data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABUElEQVQoU4XSPUjVURjH8c9fNHTQS+ILIoi4aHhDcBGEwMGmoJCInARXQVwUchAHh0QKR5eLY1A4OdhYQ46WYNzNKSH1olxfQC96K47cv1z+5PUM5+Xh+/x+53nOiZRGPUu1jLdRU6Qqx2WB1RPeBCQKUw9rL3i2SG2cGNYZLjdYz/I6CkoTTCahOGGaQoblqIXcAU3lSsl9E8dRHyfbNFQC05xFvZz+pL4S+IjzqJXDfZrvsT6KUixOMrWQqDhOnKWwwrub9nSxucFgd0I2i+d82+XJDYinaTI7dJSzvexlGcOXGPSAzx/of0lLgD+SG2UTI7cvU1LpTLGVpzGcG8if8Rh7SVCKt/NMFbHA+1Pm4qvcWpcCwwN8KlDc5hW+3gV2PuTHX6rypPHrLjDEg/Mf1JR3IGmtjt8BvKC9IljN92uuMFARLCtg6D7wv//jH7qoTXVpcJWlAAAAAElFTkSuQmCC", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABfElEQVQoU4WSO0hCURjHf+eq0cvEsCQjkZaKjKBFCIKGmoIiImoKWoNwKaghGhqSKBpbpDEomhpsrKHGSijcWnzQwzIre5heb1zxilrZGc7j4/f/f+f7zhHkhhHWKiWmmyQMMkhRmc+kYPs5w4KKCHVq17E/Us2Qp55KTaiu8zE+fe8cBGQmhOo0Y2S2FNIEczGS3jc2RaMgemfHUuhUureEiIluA89+G3XlQGeEV9Gp5+WqGWM5sCNCQlgl7m9baCibOsijMEl4Zmtxr5iLK9aEi08ktxKsZ9vTquPUZ6W3zVDsG/iC4Sgn12n6siAw6DTgvbRhL0Q7I4QDaaaAIw2kAg53LPSM1dCowrtvRCcfOAVG8y+Tc3GYBGdxO/XquS5I/FWhCwiXgphgddmMW1ZgJc7GCyxpV8mnzgUGXBXsJRVkf4px4Pgv0GEWXCggxRWcQOgvUI3LQAYoalZpaqrgRgU/oLmwVT9APZynIQW4yoIFBfT/B/76P74BMVJohM05iScAAAAASUVORK5CYII=", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABf0lEQVQoU4WSTyjDYRjHP+/PjE2zyAjFuKBNCqWtlAMnRZI4KVe1dqE4yMGBRI4uclTk5MCRg+Xg34pWDqZY2Swas7Zlv00/7adZtr2H98/T5/t93ud5X0F6GCRWSyWmarUUyymk4BexOGy/J5hTEKFMrSXsD5sYXGmiVBUq66yX2OEbB54o40Jxmq7DkQ2pghkv8a0AG6JaQzBgoyrTKXtfdcab6Cjj3d1JeT7Qek5YWHR83HZjyAe2nfMpajS8+G2YCqR+FUYNK45anEvmvxWrwvkH4pt+1n7a06zFddiOvUX/19cTgSEPp/cxen9AYMCqZ+umi4ZM1HKBzxNlEjhWQbSCo50WOkdNVCvw7gvBiTtcwMjvy6RdzMYiLkN2KpVzuYtQOEk74MsGMUosLzbilFOw9Mj6R5IF9Sq/qdOB/h4De/EksjvCGHCSCzRXFHGdAikkYwWecoFKXAaSQHFmB7JToxM8K2A0RX1eUANXCfgCevKCGQX0FQL//R/fS+VrlatLQvcAAAAASUVORK5CYII=", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABgUlEQVQoU4WSTyiDYRzHP8/a9Crb6+8k+ZODP20iFyWK4mRIpjkpVyUXioMcHEjk6CJHsZyoOXLgOBbayUqRf4vejbKx16tXezWLeQ7Pn1+f7/f3/H7PI0gOq5lFycxIiYRF1TCFY8TiGuuRN6Z0ROhTrZXtvjK6F5qQDKG+TvqJ+W7YCUbwCN1ptJqxdMgQTPiJr4VYEXaJ8L2bwlSn9H2hlyfRkEsk4MKWCXTu8iwcMtHzHqyZwLodXkSxxMOdm6J/Uj8KOYuFsWrG5xp/VmwIp4+Jr16w9NWeqhyOfB201Mg/fYMK9B5wGHqh7QsEupy5rJ25KE9FHbtcByMMA/sGSJZgb6OVpoEK7Dq8dUl46JAjoP/7ZZIulbIFv+IhXz/bNlGeE9QD1+kgspn52QbGVQ3mTlmOJpgxrvKdOhnobC7AG1dRAwqDwMFfYGWehRMNTMo7TuDqL1CPq8AHYEntQHpqsk3c6uDrB6UZQbPgOKHxDjRnBFMKaP8P/PV/fAIaTW2qgnPiWwAAAABJRU5ErkJggg==", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABhklEQVQoU4WSTSjDYRzHP88wGzEvM6EkBy9tUnNQIhInRZI4KSUHtVAUBznsYIl21EqOystBDnMhDnb0EtrNycrL0LzE/uNv+mt/zbLtOTwvvz7f7+/5/Z5HEBlZWuZ1qQwWZZImh9H43whKX6w8BZlSEKFMVXlsdlXQ4WhFpwqVdXKPoPuSbe89fUJxGrFii4VUwcQu0vIZTmHS478dxxjtFLs3OnkUtSaeTofITgRaXLwIs5Hni2GyEoHVLl5FYQZ3N2MUJEn9IAw6HLY6Ru3NfytWhdP7SEsnLPy0pzwHj7uPhsr8v75eP3RucHgZoOkHBNotRpbPhymNRs0ufN4HBoB9FUSbws5qF9aeKkwKvObF37+FB+j+fZmIS5lBy1FggjzlnL1A4CVEDeCLBTFomZttZFQOg93D4nOIGfUqv6kjgbb6YtYlGfn0ll7gIB5YlpvOSRg0AQkLcBUPVOIy8AWkRXcgNjX6FK4V8F2mJCGYKjj+DPMB1CcEowpoSQb++z++AWddbb0nVKc+AAAAAElFTkSuQmCC", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABhklEQVQoU4WSTSjDYRzHP/+1scm2vL+m5YI2KRelhOIkxBInJTeF8lIcsHKwRJy4LEdFK+UwRxInealpNyfLxqINsT/7j/5r07b4ew7Py6/P7/t9fr/nEYgPfSbLWg1DJUY0UhRV4JWwGGEr9M6MjAjyVF2Ms7uODnsv2kSivE47Cbvc7Hv89Auy0kgzo3ZrKpRImHIiOk5YEwqzCdyvkp+slL7Pn+BJqCsndDWHQQm02HgRzCU8X9vQK4E187wKRXoe/CsUKFpP8igYddhHWxlf7P69mNk9xM1jVmLtqczj1DVGY1Vxqq7nDro2OLkJ0BQDgXZLKQ73AhXJqNmG1+NjEDhMgGSoOdgept5aT6EM75wRGHBwCvT8vExcxWTUcR5cJ1c+G8YIvojUAt50EKOWpYVOxiUJFl2sPoeZS1zlxzoeaGswsStGkK689AFHf4GmnCwuv0AVfMMC3P4FynEJiAKa5A6kW6NT40NF9P2DMkVQreIiEuUTaFAEkwpo+Q/89X98A7Lhbc8UsJlRAAAAAElFTkSuQmCC", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABiElEQVQoU4WSSyhEURjHf3cawzWNN+U1RlaKDQtFolDKAlNSFp7FQrLQSGGnvEY20tDEgoWkCLEhFigLZG8hBkXkOYwxd3Q1V2PiOovz+Pr9/9/5vnMEfEMUGdJpaYiOJEjyoLl/5M39wdTLK10yIsiTMZ6FvGzKmmsIUYTyapvlbf+Y5dMLqgXZqaKItkBIEYzP4lrbYlQIN3CzNEGMv1PgvryZOyEthQd7P2FqYIOFJ8GUyOP0MAY1sLaDZyEijOtFG7GqqVu4FfQiA+ZS2hurflasCCfncK1sYP1qT3wcu4Od5CYn/PQ9dUC3lZ3La/K/QKAkNQn71BBGf7TOguPsglpgSwHRalnvaSWrIIc4Gd7c46ZvjF2g8vtlfC4mfSgHq3ai5HNZE/fOVzIBRyCIKNJfb6Zd8sLMEiNOJ73KVb5T+wLF6WnMu914Ts6oArb/Ak0GPUdeL5pnJxnA+V+gHPcAEhDk34HA1ATruPKC9P5Ooiqo0XAoSbiBHFXQr4DC/8Bf/8cnnEVvrjzEY7AAAAAASUVORK5CYII=", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABhUlEQVQoU4WSTSjDYRzHP89sazMvYeNA7EbholCkljhIKUpTK3H0loNoF7kIeUkxN1OilJxc1FIINy8XN+Vgq8m8v+7F9tdf+2sW8xyel1+f7/f3/H7PI4gOrZ5JjYZOQyYaKYLq7RF/OMiS/x27jAh5yspjo6iCxvoOdIpQXl1O/OenbPousQrZqbyBvnhIEbicBE5czIrkNHxDqxhjneL3kzbuRI6Zx6450hKBC708C1M+Tz0OUhOBji5ehCGd68EVTP+kvhU6PRMVTfTX2n5WrAi3lwkcbTH91Z6MHA5tI1QZ8376XrthbZSDey81XyBQn13AYvc8+bGooxvPjYd2YEcBSVKz1TJAWXE12TJ8to9vY4pDoPn7ZaIuZp2BY/samfJ5zMpD8J1SwBMPotUzbmmjX5Jgb52Z4BvDylW+U0cDdbmFrIdDhK8uaAV2/wLN+hROIxKqwCslgPsvUI6HgQigie1AfGrUWrwy+BEkNyEoVJxIEUJAZUIwpgDLf+Cv/+MTkjtukd3k3jgAAAAASUVORK5CYII=", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABgElEQVQoU4WSSyhEURjHf+fODMYY5FWaaGRFWMijPGLBysKjZIexsNNsFXbKI7KTJBZ2UoRYicKCwsLCxmhC5NlgZgxj7tXVXMbEOIvz+Pp9///5vnMEwSEZGdZFYYtKxoCM9ObCp/iZfvfQrSJCnYyZzKeUU5fdSYyWqK6OCXx3uyy9OGkRqpKlga5wSEtwjPN6tcqY0CdwW7FISqhS+H67ngcRl81j0RTxkcBdG8/CZOWpeAZzRLAVtzAkclO+QOo/1vdCb2LQ0oQ9q+NnxVri6SSvl8uMfLYnJp2dgiHKYjN+6nqccNTDtu+Syk8QqI3NYqpkmsxQdK+NC+8ZrcCGBiL0rOX2UphaRZoKX69ze9zPDtD49TJBFavOxH7lCknqeasOV8BLPnARDiIZGbC2Y0cG5yyjspc+7Spf1sFAjTmHOcVPwH1CM7D5F2jVmzlUFKSAmzzg/C9QjQdANccQ2oFwa0Q0VyjIyhuWiCASB8j4gdLI4HcB1f+Bv/6PD+LHbHBXYT1vAAAAAElFTkSuQmCC", 
               "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAUCAYAAAC07qxWAAABTUlEQVQoU4XSPUhcURCG4edKlggmkohaGIhisNPGRhAkgqZKJaKxEmwECyN2IlhKRBQFLYOtRISAjZAIWmRLtU8RCw2CYuLPCq7BRe5dNqsXdz3VmeGd7zszZwK5U2ZawoBKCRkl/rpybcmlsRAJIq7WqnbvDSv9Xxhe5l1JWrPnQyBU6jbsYwzKVcxL+2ou8MKxDZX3lOJBhz+BBmeWlRcFe10E6p1b8bwo2C0VeOnId1WPWJ+EzUzpM2KoQDOL0lbNZMdTI2lBq9qY7i+M+uG3tizIO2989sXre2iPA3v6sZkDeWLdpGYdqiP4m2PjkujK/0xWps4z27ZURNFbpy414SAOUuaTQSNusGRWykTuKXnrbKZToxXXbvzUg61CYJ1yu9H2pDRivxAY5kPjDBJ3JxC35qnDCEx7VRwssSPjH1qKg/kG2h8DH9yPWxORTVRHy5mwAAAAAElFTkSuQmCC"];
  
  // URL du service utilisé pour retrouver la list des villes avec leur position, niveau etc
  var SERVICE_URL = "communes";
  var year = "2014", map, markers = [];
  
  // On utilise la localisation de la ville de Bourges pour centrer la carte de France.
  var bourgesLoc = {lat:47.081012, lng:  2.398782}; // Bourges

  // Popup de detail d'une commune partagée pour toutes les communes.
  var infoWindow  = new google.maps.InfoWindow();
  
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
    // Ajout des controles annee + slider score
    addYearControl(map);
    addSliderScoreControl(map);
    addSearchBoxControl(map);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(document.getElementById("map-controls"));
    // Affichage des markers
    displayMarkers();
  }

  function displayMarkers() {
    // Suppression des anciens markers
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
    // recuperation du range de score
    var scores = $( "#score-control" ).slider("values");
    
    // Appel du service pour recuperer les villes + creer les markers
    var url = SERVICE_URL + "?annee=" + year + "&from=" + scores[0] + "&to=" + scores[1];
    $.ajax({
      url : url,
      type : "GET",
      dataType: "json",
      success: function(data, textStatus) {
        for (var i = 0; i < data.length; i++) {
          var commune = data[i], loc = commune.loc, score = commune.score;
          addMarker(map, loc, score);
        }
      },
      error: function(qXHR, textStatus, errorThrown) {
        alert(errorThrown)
      }
    });
  }
  
  function getLevel(score) {
    if (score <= 10) return 9;
    if (score <= 20) return 8;
    if (score <= 30) return 7;
    if (score <= 40) return 6;
    if (score <= 50) return 5;
    if (score <= 60) return 4;
    if (score <= 70) return 3;
    if (score <= 80) return 2;
    if (score <= 90) return 1;
    return 0;
  }
  
  /**
   * Ajout du controle de recherche de commune.
   * @see https://developers.google.com/maps/documentation/javascript/examples/places-searchbox
   */
  function addSearchBoxControl(map) {
    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    
    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });
    
    // [START region_getplaces]
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();
  
      if (places.length == 0) {
          return;
      }
      
      // For each place, get the icon, name and location.
      var bounds = new google.maps.LatLngBounds();
      places.forEach(function(place) {      
        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
    });
    // [END region_getplaces]
  }
  
  /**
   * Ajout du controle Annee (2013, 2014).
   */
  function addYearControl(map) {
    var yearControl = $("#year-control"); 
    yearControl.menu({
      select: function( event, ui ) {
        year = ui.item.html();
        // selection du menu
        $(this).find("li").removeClass("selected");
        ui.item.addClass("selected");
        displayMarkers();
      }
    });  
  }

  /**
   * Ajout du slider de score.
   */
  function addSliderScoreControl(map) {
    var defaultValues = [ 10, 100 ];
    $("#score-control").slider({
      range: true,
      min: 0,
      max: 100,
      values: defaultValues,
      orientation: "vertical",
      stop: function( event, ui ) {
        displayMarkers();
      }, 
      slide: function(event, ui ) {
        $("#score-range").html( ui.values[0] + " - " + ui.values[1] );
      }
   });
   $("#score-range").html( defaultValues[0] + " - " + defaultValues[1] );
  }  

  /**
   * Creation d'un marker sur la carte a la position donnée en utilisant l'icone donné.
   */
  function addMarker(map, position, score) {
    var icon = LEVELS[getLevel(score)];
    var marker = new google.maps.Marker({
        position: position,
        map: map,
        icon: icon,
        title: "Score: " + score
    });
    markers.push(marker);
    google.maps.event.addListener(marker, 'click', function() {
      openInfo(this, position, score);
    }); 
  }

  
  function openInfo(marker, position, score) {    
    // Appel du service pour recuperer le detail de la commune
    var url = "commune" + "?annee=" + year + "&lat=" + position.lat + "&lng=" + position.lng;
    $.ajax({
      url : url,
      type : "GET",
      dataType: "json",
      success: function(data, textStatus) {
        var s = "<p>Score de la commune de <b>";
        s += data.name;
        s += "</b>: <b>";
        s += score;
        s += "</b> / 100.<ul>";
        s += "<li>Nombre allocataires: <b>";
        s += data.nbAllocs;
        s += "<li>Nombre de pharmacie: <b>";
        s += data.nbPharmacie;
        s += "</b></li>";
        s += "</ul>";
        s += "</p>";
     
        infoWindow.setContent(s);
        infoWindow.open(marker.getMap(), marker);
      },
      error: function(qXHR, textStatus, errorThrown) {
        alert(errorThrown)
      }
      });      
  }

  /**
   * Creation d'un graphique.
   */
  /*function drawChart(marker) {

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
  }*/

  // Chargement de la librarie Charts de Google.
  //google.load('visualization', '1.0', {'packages':['corechart']});
  google.maps.event.addDomListener(window, 'load', initMap);
}());