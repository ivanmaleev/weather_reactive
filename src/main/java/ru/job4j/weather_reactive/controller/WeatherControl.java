package ru.job4j.weather_reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather_reactive.entity.Weather;
import ru.job4j.weather_reactive.service.WeatherService;

import java.time.Duration;

@RestController
public class WeatherControl {

    @Autowired
    private final WeatherService weathers;

    public WeatherControl(WeatherService weathers) {
        this.weathers = weathers;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = weathers.all();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return weathers.findById(id);
    }

    @GetMapping(value = "/getmax")
    public Mono<Weather> getMaxTemp() {
        return weathers.findMaxTemp();
    }

    @GetMapping(value = "/cityGreatThen/{temp}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findTempGT(@PathVariable Integer temp) {
        Flux<Weather> data = weathers.findTempGT(temp);
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
}