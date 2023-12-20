package fr.insa.wsn.sensorservice;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Sensor
{
    private int sensorId;
    private final List<String> data;

    private static int m_id;

    public Sensor()
    {
        this.sensorId = m_id++;
        this.data = new ArrayList<>();
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public List<String> getData() {
        return data;
    }

    public void addData(String data) {
        this.data.add(data);
    }
}
