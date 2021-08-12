package de.embl.cba.n5.ome.zarr.util;

import org.janelia.saalfeldlab.n5.N5Reader;

public class OmeZarrMultiscales {

    // key in json for multiscales
    public static final String MULTI_SCALE_KEY = "multiscales";

    public ZarrAxes axes;
    public Dataset[] datasets;
    public String name;
    public String type;
    public N5Reader.Version version;

    public OmeZarrMultiscales() {
    }

    public OmeZarrMultiscales(ZarrAxes axes, String name, String type, N5Reader.Version version, int nDatasets) {
        this.version = version;
        this.name = name;
        this.type = type;
        this.axes = axes;
        generateDatasets(nDatasets);
    }

    private void generateDatasets(int nDatasets) {
        Dataset[] datasets = new Dataset[nDatasets];
        for (int i = 0; i < nDatasets; i++) {
            Dataset dataset = new Dataset();
            dataset.path = "s" + i;
            datasets[i] = dataset;
        }
        this.datasets = datasets;
    }

    public static class Dataset {
        public String path;
    }
}
