package ru.job4j.weather_reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather_reactive.entity.Weather;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 33));
        weathers.put(4, new Weather(4, "Smolensk", 11));
        weathers.put(5, new Weather(5, "Kiev", 18));
        weathers.put(6, new Weather(6, "Minsk", 25));
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    public Flux<Weather> all() {
        return Flux.fromIterable(weathers.values());
    }

    public Mono<Weather> findMaxTemp() {
        Weather weatherMaxTemp = weathers.values()
                .stream()
                .max(Comparator.comparingInt(Weather::getTemperature))
                .orElse(new Weather());
        return Mono.justOrEmpty(weatherMaxTemp);
    }

    public Flux<Weather> findTempGT(int temp) {
        List<Weather> weathersTempGT = weathers.values()
                .stream()
                .filter(w1 -> w1.getTemperature() > temp)
                .collect(Collectors.toList());
        return Flux.fromIterable(weathersTempGT);
    }
}