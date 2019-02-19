package cn.mydreamy.lib.utils;

import jankovicsandras.imagetracer.ImageTracer;

import java.util.HashMap;

public class PngSvgConverter {

    public static String convertToSvg(String pngFile, String outputFile) {
        // Options
        HashMap<String, Float> options = new HashMap<String, Float>();
        // Tracing
        options.put("ltres", 1f);//Error treshold for straight lines.
        options.put("qtres", 1f);
        options.put("pathomit", 8f);
        // Color quantization
        options.put("colorsampling", 1f); // starting with generated palette
        options.put("numberofcolors", 16f);
        options.put("mincolorratio", 0.02f);
        options.put("colorquantcycles", 3f);
        // SVG rendering
        options.put("scale", 1f);
        options.put("roundcoords", 1f);
        options.put("lcpr", 0f);
        options.put("qcpr", 0f);
        options.put("desc", 1f);
        options.put("viewbox", 1f);
        // Selective Gauss Blur
        options.put("blurradius", 0f); // blur with this radius
        options.put("blurdelta", 20f);
        // Palette
        // This is an example of a grayscale palette这是灰度调色板的示例
        // please note that signed byte values [ -128 .. 127 ] will be converted to [ 0 .. 255 ] in the getsvgstring function
        byte[][] palette = new byte[8][4];
        for (int colorcnt = 0; colorcnt < 8; colorcnt++) {
            palette[colorcnt][0] = (byte) (-128 + colorcnt * 32); // R
            palette[colorcnt][1] = (byte) (-128 + colorcnt * 32); // G
            palette[colorcnt][2] = (byte) (-128 + colorcnt * 32); // B
            palette[colorcnt][3] = (byte) 127;              // A
        }
        try {
            ImageTracer.saveString(outputFile,
                    ImageTracer.imageToSVG(pngFile, options));
        } catch (Exception e) {
            return "Exception in parsing " + pngFile + ":\n" + e.getMessage();
        }
        return null;
    }
}
