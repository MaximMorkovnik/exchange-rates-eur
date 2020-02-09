package ua.kiev.prog.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.dto.ExchangeRateDTO;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.json.Rate;
import ua.kiev.prog.retrievers.GeoRetriever;
import ua.kiev.prog.retrievers.RateRetriever;
import ua.kiev.prog.services.LocationService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RateController {
    private final RateRetriever rateRetriever;
    private final GeoRetriever geoRetriever;
    private final LocationService locationService;

    public RateController(RateRetriever rateRetriever, GeoRetriever geoRetriever,
                          LocationService locationService) {
        this.rateRetriever = rateRetriever;
        this.geoRetriever = geoRetriever;
        this.locationService = locationService;
    }

    @GetMapping("/rate={rate}")
    public ExchangeRateDTO rate(@PathVariable String rate , HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        LocationDTO location = geoRetriever.getLocation(ip);
        locationService.save(location);
        String url =   request.getHeader("referer");
        rate=url.substring(url.length()-3);
        return rateRetriever.getExchangeRate(rate);
    }
}
