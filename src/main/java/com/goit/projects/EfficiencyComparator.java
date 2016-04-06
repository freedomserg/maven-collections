package com.goit.projects;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EfficiencyComparator {
    public static final int TEN_THOUSAND_DATA_VOLUME = 10_000;
    public static final int ONE_HUNDRED_THOUSAND_DATA_VOLUME = 100_000;
    public static final int ONE_MILLION_DATA_VOLUME = 1_000_000;

    public void addCollectionToCompare(List<Collection> collections) throws IOException {
        compare(collections);
    }

    private void compare(List<Collection> collections)throws IOException {
        measureForTenThousandVolume(collections);
        measureForHundredThousandVolume(collections);
        measureForOneMillionVolume(collections);
    }

    private void measureForTenThousandVolume(List<Collection> collections) throws IOException {
        OutputUtil.printHeader(TEN_THOUSAND_DATA_VOLUME);
        for (Collection collection : collections) {
            Measurer measurer = new MeasurerFactoryImpl().makeMeasurer(collection);
            Map<String, String> measureResults = measurer.getResults(TEN_THOUSAND_DATA_VOLUME);
            OutputUtil.printAndSaveResults(measurer.getCollectionType(), measureResults);
        }
    }

    private void measureForHundredThousandVolume(List<Collection> collections) throws IOException {
        OutputUtil.printHeader(ONE_HUNDRED_THOUSAND_DATA_VOLUME);
        for (Collection collection : collections) {
            Measurer measurer = new MeasurerFactoryImpl().makeMeasurer(collection);
            Map<String, String> measureResults = measurer.getResults(ONE_HUNDRED_THOUSAND_DATA_VOLUME);
            OutputUtil.printAndSaveResults(measurer.getCollectionType(), measureResults);
        }
    }

    private void measureForOneMillionVolume(List<Collection> collections) throws IOException {
        OutputUtil.printHeader(ONE_MILLION_DATA_VOLUME);
        for (Collection collection : collections) {
            Measurer measurer = new MeasurerFactoryImpl().makeMeasurer(collection);
            Map<String, String> measureResults = measurer.getResults(ONE_MILLION_DATA_VOLUME);
            OutputUtil.printAndSaveResults(measurer.getCollectionType(), measureResults);
        }
    }
}
