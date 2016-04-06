package com.goit.projects;

import com.goit.projects.exceptions.UnsupportedTypeOfCollectionMeasurerEfficiencyException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MeasurerFactoryImpl implements MeasurerFactory {
    @Override
    public Measurer makeMeasurer(Collection collection) {

        if (collection instanceof List) {
            return new ListMeasurer((List<Integer>)collection);
        } else if (collection instanceof Set) {
            return new SetMeasurer((Set<Integer>)collection);
        } else {
            throw new UnsupportedTypeOfCollectionMeasurerEfficiencyException();
        }
    }
}
