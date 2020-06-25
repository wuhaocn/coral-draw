/**
 * Copyright (c) 2011-2017, JGraph Ltd
 */
package com.mxgraph.server.utils;

import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.canvas.mxICanvas2D;
import com.mxgraph.reader.mxSaxOutputHandler;
import com.mxgraph.server.online.Constants;
import com.mxgraph.util.mxUtils;
import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.pdf.PdfWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;

/**
 * Servlet implementation class ImageServlet.
 */
public class ExportServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5040708166131034515L;

    /**
     *
     */
    private transient SAXParserFactory parserFactory = SAXParserFactory.newInstance();

    /**
     * Cache for all images.
     */
    protected transient Hashtable<String, Image> imageCache = new Hashtable<String, Image>();




    /**
     * Gets the parameters and logs the request.
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws DocumentException
     */
    public ByteArrayOutputStream handleRequest(String fileName, String xml) throws Exception {
        // Parses parameters
        String format = "png";
        String fname = fileName;
        int w = 100;
        int h = 100;

        Color bg = null;

		// Allows transparent backgrounds only for PNG
		if (bg == null && !format.equals("png")) {
			bg = Color.WHITE;
		}

		if (fname != null && fname.toLowerCase().endsWith(".xml")) {
			fname = fname.substring(0, fname.length() - 4) + format;
		}

		String url = "https://www.draw.io/?client=1&amp;lightbox=1&amp;edit=_blank";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// Writes response
		if (format.equals("pdf")) {
			writePdf(url, fname, w, h, bg, xml, outputStream);
		} else {
			 writeImage(url, format, fname, w, h, bg, xml, outputStream);
		}
		return outputStream;
	}



    /**
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    protected void writeImage(String url, String format, String fname, int w, int h, Color bg, String xml, ByteArrayOutputStream outputStream)
            throws IOException, SAXException, ParserConfigurationException {
        BufferedImage image = mxUtils.createBufferedImage(w, h, bg);

        if (image != null) {
            Graphics2D g2 = image.createGraphics();
            mxUtils.setAntiAlias(g2, true, true);
            renderXml(xml, createCanvas(url, g2));


            ImageIO.write(image, format, outputStream);
        }
    }

    /**
     * Creates and returns the canvas for rendering.
     *
     * @throws IOException
     * @throws DocumentException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    protected void writePdf(String url, String fname, int w, int h, Color bg, String xml, ByteArrayOutputStream outputStream)
            throws DocumentException, IOException, SAXException, ParserConfigurationException {

        // Fixes PDF offset
        w += 1;
        h += 1;

        Document document = new Document(new com.mxpdf.text.Rectangle(w, h));
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();

        mxGraphicsCanvas2D gc = createCanvas(url, writer.getDirectContent().createGraphics(w, h));

        // Fixes PDF offset
        gc.translate(1, 1);

        renderXml(xml, gc);
        gc.getGraphics().dispose();
        document.close();
        writer.flush();
        writer.close();
    }

    /**
     * Renders the XML to the given canvas.
     */
    protected void renderXml(String xml, mxICanvas2D canvas) throws SAXException, ParserConfigurationException, IOException {
        XMLReader reader = parserFactory.newSAXParser().getXMLReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        reader.setContentHandler(new mxSaxOutputHandler(canvas));
        reader.parse(new InputSource(new StringReader(xml)));
    }

    /**
     * Creates a graphics canvas with an image cache.
     */
    protected mxGraphicsCanvas2D createCanvas(String url, Graphics2D g2) {
        // Caches custom images for the time of the request
        final Hashtable<String, Image> shortCache = new Hashtable<String, Image>();
        final String domain = url.substring(0, url.lastIndexOf("/"));

        mxGraphicsCanvas2D g2c = new mxGraphicsCanvas2D(g2) {
            public Image loadImage(String src) {
                // Uses local image cache by default
                Hashtable<String, Image> cache = shortCache;

                // Uses global image cache for local images
                if (src.startsWith(domain)) {
                    cache = imageCache;
                }

                Image image = cache.get(src);

                if (image == null) {
                    image = super.loadImage(src);

                    if (image != null) {
                        cache.put(src, image);
                    } else {
                        cache.put(src, Constants.EMPTY_IMAGE);
                    }
                } else if (image == Constants.EMPTY_IMAGE) {
                    image = null;
                }

                return image;
            }
        };

        return g2c;
    }

}
