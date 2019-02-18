package cn.mydreamy.lib.utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

import javax.imageio.ImageIO;

public class TestSVGGen {

    /**
     * 绘制Html5使用的svg,android不能使用
     * @param g2d
     */
    public void paint(Graphics2D g2d) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("F:\\github\\YYSvgView\\lib\\ic_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        g2d.drawImage(image, width, height, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
                return false;
            }
        });
    }

    public static void main(String[] args) throws IOException {

        // Get a DOMImplementation.
        DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        Set set = new HashSet();
        set.add("image");
        svgGenerator.setUnsupportedAttributes(set);

        // Ask the test to render into the SVG Graphics2D implementation.
        TestSVGGen test = new TestSVGGen();
        test.paint(svgGenerator);

        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(System.out, "UTF-8");
        svgGenerator.stream(out, useCSS);
    }
}