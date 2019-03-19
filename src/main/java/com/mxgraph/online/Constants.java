package com.mxgraph.online;

import java.awt.image.BufferedImage;

public class Constants {

    /**
     * Maximum size (in bytes) for request payloads. Default is 52428800 (50MB).
     */
    public static final int MAX_REQUEST_SIZE = 52428800;

    /**
     * Maximum are for exports. Default assumes the area taken by a
     * 10000px by 10000px image.
     */
    public static final int MAX_AREA = 10000 * 10000;

    /**
     * The domain where legacy images are stored.
     */
    public static final String IMAGE_DOMAIN = "http://img.diagramly.com/";

    /**
     * Contains an empty image.
     */
    public static BufferedImage EMPTY_IMAGE;

    /**
     * Initializes the empty image.
     */
    static
    {
        try
        {
            EMPTY_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
        catch (Exception e)
        {
            // ignore
        }
    }


}
