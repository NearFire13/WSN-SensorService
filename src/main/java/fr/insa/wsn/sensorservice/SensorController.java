package fr.insa.wsn.sensorservice;

import java.util.*;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
public class SensorController
{
    private static final HashMap<Integer, Sensor> sensors = new HashMap<Integer, Sensor>();

    @PostMapping("/data/add")
    public ResponseEntity<String> addData(
            @RequestParam("sensorId") int sensorId,
            @RequestParam("data") String data
    ) {
        if (!isValidSensorId(sensorId)) return new ResponseEntity<>("Sensor not found", HttpStatus.NOT_FOUND);

        System.out.println("Creating new data");
        Sensor sensor = sensors.get(sensorId);
        sensor.addData(data);

        return ResponseEntity.ok("Data sensor created successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSensor()
    {
        System.out.println("Creating new sensor");

        Sensor sensor = new Sensor();
        sensors.put(sensor.getSensorId(), sensor);

        return ResponseEntity.ok("Sensor created successfully");
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<String> deleteSensor(@PathVariable("sensorId") int sensorId) {
        if (!isValidSensorId(sensorId)) return new ResponseEntity<>("Sensor not found", HttpStatus.NOT_FOUND);

        sensors.remove(sensorId);

        return ResponseEntity.ok("Sensor deleted successfully");
    }

    @GetMapping(value="/{sensorId}")
    public ResponseEntity<Object> getSensor(@PathVariable("sensorId") int sensorId)
    {
        if (!isValidSensorId(sensorId)) return new ResponseEntity<>("Sensor not found", HttpStatus.NOT_FOUND);

        Sensor sensor = sensors.get(sensorId);

        return ResponseEntity.ok(sensor);
    }

    @GetMapping(value="/")
    public ResponseEntity<List<Sensor>> getAllSensors()
    {
        List<Sensor> allSensors = new ArrayList<>(sensors.values());

        return ResponseEntity.ok(allSensors);
    }

    private boolean isValidSensorId(int sensorId)
    {
        return sensors.containsKey(sensorId);
    }
}
