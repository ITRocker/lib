package cn.mydreamy.lib.utils;

import jankovicsandras.imagetracer.ImageTracer;

import java.util.HashMap;

public class PngSvgConverter {

    public static String convertToSvg(String pngFile, String outputFile) {
        // Options
        HashMap<String, Float> options = new HashMap<String, Float>();
        // Tracing
        options.put("ltres", 1f);//Error treshold for straight lines.直线的误差阈值。
        options.put("qtres", 1f);//二次样条的误差阈值。
        options.put("pathomit", 8f);//比这更短的边缘节点路径将被丢弃以降低噪声。
        // Color quantization
        options.put("colorsampling", 1f); // 启用或禁用颜色采样。1f打开，0f关闭。 starting with generated palette
        options.put("numberofcolors", 16f);//如果未定义pal对象，则在palette上使用的颜色数。
        options.put("mincolorratio", 0.02f);//如果像素（总像素* mincolorratio）的像素少，则颜色量化将随机化颜色。
        options.put("colorquantcycles", 3f);//颜色量化将重复多次。
        // SVG rendering
        options.put("scale", 1f);//每个坐标将与此相乘，以缩放SVG。
        options.put("roundcoords", 1f); // 将坐标舍入到给定的小数位。1f表示舍入到小数点后1位，如7.3; 3f表示舍入到3个位置，如7.356
        options.put("lcpr", 0f);//直线控制点半径，如果大于零，则在SVG中绘制小圆圈。不要将此用于大/复杂图像。
        options.put("qcpr", 0f);//二次样条控制点半径，如果大于零，则在SVG中绘制小圆和直线。不要将此用于大/复杂图像。
        options.put("desc", 1f); // 启用或禁用SVG描述。1f打开，0f关闭。
        options.put("viewbox", 1f); // 启用或禁用SVG viewBox。1f打开，0f关闭。: fixed width and height
        // Selective Gauss Blur
        options.put("blurradius", 0f); // 将其设置为1f..5f以进行选择性高斯模糊预处理。blur with this radius
        options.put("blurdelta", 20f); // 用于选择性高斯模糊预处理的RGBA delta阈值。小于此RGB差异将模糊
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
