package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> _patients;

    public SimpleEmergencyRoom() {
        _patients = new ArrayList<>();
    }

    public Patient dequeue() {
        if (_patients.isEmpty()) {
            return null;
        }

        int idx = 0;
        Patient high = _patients.get(0);

        for (int i = 1; i < _patients.size(); i++) {
            Patient curr = _patients.get(i);
            if (curr.compareTo(high) > 0) {
                high = curr;
                idx = i;
            }
        }

        // remove from the list and return it
        _patients.remove(idx);
        return high;
    }




    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        _patients.add(patient);
    }

    public List getPatients() {
        return _patients;
    }

    public int size() {
        return _patients.size();
    }

}
