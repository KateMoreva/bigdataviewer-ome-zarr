package de.embl.cba.n5.ome.zarr;

import de.embl.cba.n5.ome.zarr.loaders.N5OMEZarrImageLoader;
import mpicbg.spim.data.generic.sequence.AbstractSequenceDescription;
import mpicbg.spim.data.generic.sequence.ImgLoaderIo;
import mpicbg.spim.data.generic.sequence.XmlIoBasicImgLoader;
import org.jdom2.Element;

import java.io.File;

import static mpicbg.spim.data.XmlKeys.IMGLOADER_FORMAT_ATTRIBUTE_NAME;

@ImgLoaderIo(format = "bdv.ome.zarr", type = N5OMEZarrImageLoader.class)
public class XmlN5OmeZarrImageLoader implements XmlIoBasicImgLoader<N5OMEZarrImageLoader> {
    public static final String OmeZarr = "ome.zarr";

    public static String getDatasetsPathFromXml(final Element parent, final String basePath) {
        final Element elem = parent.getChild(OmeZarr);
        if (elem == null)
            return null;
        final String path = elem.getText();
        final String pathType = elem.getAttributeValue("type");
        final boolean isRelative = null != pathType && pathType.equals("relative");
        if (isRelative) {
            if (basePath == null)
                return null;
            else {
                String xmlPath = basePath.toString();
                return xmlPath.substring(0, xmlPath.lastIndexOf('/')) + "/" + path;
            }
        } else
            return path;
    }

    @Override
    public Element toXml(final N5OMEZarrImageLoader imgLoader, final File basePath) {
        final Element elem = new Element("ImageLoader");
        elem.setAttribute(IMGLOADER_FORMAT_ATTRIBUTE_NAME, "bdv.ome.zarr");
        elem.setAttribute("version", "0.2");
        return elem;
    }

    @Override
    public N5OMEZarrImageLoader fromXml(Element elem, File basePath, AbstractSequenceDescription<?, ?, ?> sequenceDescription) {
        return null;
    }
}