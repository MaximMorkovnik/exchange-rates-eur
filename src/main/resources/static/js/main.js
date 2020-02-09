$(document).ready(function(){

    $.getJSON( "/rate={rate}" , function(data) {

        let url = window.location.href;

        let toRate = url.substr(url.length - 3);

        if(toRate=="usd" || toRate=="uan" || toRate=="btc"){
            $('#valute').text(toRate.toUpperCase());
        }else  $('#valute').text(" ");

        $('#rate').text(data.exchangeRate);
        $('#date').text(data.date);
    });
});