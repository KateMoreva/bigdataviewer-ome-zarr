package org.embl.mobie.io.util.openers;

import ij.IJ;

public abstract class BDVOpener {
    protected static boolean logChunkLoading;

    public static void setLogChunkLoading(final boolean logChunkLoading) {
        S3Opener.logChunkLoading = logChunkLoading;
        if (logChunkLoading) IJ.run("Console");
    }

}