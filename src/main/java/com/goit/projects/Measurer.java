package com.goit.projects;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class Measurer {
    protected int inputCollectionDataVolume;
    protected Random numberGenerator = new Random();
    protected long startOfMeasurement;
    protected long endOfMeasurement;
    protected long durationOfMeasurement;
    protected double totalDurationOfMeasurements;
    protected int sizeOfUniformSegmentOfCollection;
    protected int iterationsCounter;
    protected int updateCollectionCounter;
    protected static Map<String, String> measurementResults = new TreeMap<>();
    public final int QUANTITY_OF_REPETITIONS = 100;

    {
        measurementResults.put("add", null);
        measurementResults.put("get", null);
        measurementResults.put("remove", null);
        measurementResults.put("contains", null);
        measurementResults.put("populate", null);
        measurementResults.put("iterator.add", null);
        measurementResults.put("iterator.remove", null);
    }

    public abstract Map<String, String> getResults(int inputCollectionDataVolume);

    public abstract void fillResultingMap();

    public double measurePopulateOperationEfficiency(Collection collection) {
        totalDurationOfMeasurements = 0;
        for (int i = 0; i < QUANTITY_OF_REPETITIONS; i++) {
            if (collection.size() > 0) {
                collection.clear();
            }
            startOfMeasurement = System.currentTimeMillis();
            for (int j = 0; j < inputCollectionDataVolume; j++) {
                int value = getIntegerNumber();
                collection.add(value);
            }
            endOfMeasurement = System.currentTimeMillis();
            updateTotalDuration();
        }
        return totalDurationOfMeasurements / QUANTITY_OF_REPETITIONS;
    }

    public int getIntegerNumber() {
        return numberGenerator.nextInt();
    }

    public abstract String getCollectionType();

    public void updateTotalDuration() {
        durationOfMeasurement = endOfMeasurement - startOfMeasurement;
        totalDurationOfMeasurements += durationOfMeasurement;
    }

    public double measureContainsOperationEfficiency(Collection collection) {
        totalDurationOfMeasurements = 0;
        boolean isContainedValue = false;
        for (int i = 0; i < QUANTITY_OF_REPETITIONS; i++) {
            int value = getIntegerNumber();
            startOfMeasurement = System.currentTimeMillis();
            isContainedValue = collection.contains(value);
            endOfMeasurement = System.currentTimeMillis();
            updateTotalDuration();
        }
        return totalDurationOfMeasurements / QUANTITY_OF_REPETITIONS;
    }

    public abstract int getSizeOfUniformSegmentOfCollection();

    public boolean reachNextUniformSegment() {
        return iterationsCounter % sizeOfUniformSegmentOfCollection == 0;
    }
}