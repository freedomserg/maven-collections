package com.goit.projects;

import java.util.Collection;

public interface MeasurerFactory {
    Measurer makeMeasurer(Collection collection);
}
