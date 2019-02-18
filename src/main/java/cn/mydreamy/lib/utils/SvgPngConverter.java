package cn.mydreamy.lib.utils;

import java.io.*;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * 将svg转换为png格式的图片
 * 
 * 
 */
public class SvgPngConverter {

    /**
     * 将svg字符串转换为png
     * 
     * @param svgCode svg代码
     * @param pngFilePath 保存的路径
     * @throws TranscoderException svg代码异常
     * @throws IOException io错误
     */
    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {

        File file = new File(pngFilePath);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将svgCode转换成png文件，直接输出到流中
     * 
     * @param svgCode svg代码
     * @param outputStream 输出流
     * @throws TranscoderException 异常
     * @throws IOException io异常
     */
    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            org.apache.batik.transcoder.image.PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将svg文件转换成png文件
     *
     * @param svgFilePath
     * @param pngFilePath
     * @throws TranscoderException 异常
     * @throws IOException io异常
     */
    public static void convertToPng1(String svgFilePath, String pngFilePath)
            throws TranscoderException, IOException {
        File file = new File(pngFilePath);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            org.apache.batik.transcoder.image.PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new FileInputStream(svgFilePath));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } catch (FileNotFoundException e) {

        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}