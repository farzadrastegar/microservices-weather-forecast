package com.myspringproject.microservices.weatherservice.resource;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping("/rest/weather")
public class weatherServiceResource {

    @CrossOrigin
    @GetMapping("/{location}")
    public String getWeather(@PathVariable("location") final String location) {
        String weatherForInputLocation;

        try {
            YahooWeatherService service = new YahooWeatherService();
            Channel channel = service.getForecastForLocation( location, DegreeUnit.CELSIUS )
                    .first( 1 ).get( 0 );

            int temperature = channel.getItem().getCondition().getTemp();
            String conditions = channel.getItem().getCondition().getText();

            weatherForInputLocation = temperature + "C, " + conditions;
        }
        catch (JAXBException e){
            weatherForInputLocation = "NOT available!";
        }
        catch (IOException e) {
            weatherForInputLocation = "NOT available!";
        }

        return weatherForInputLocation;
    }
}
