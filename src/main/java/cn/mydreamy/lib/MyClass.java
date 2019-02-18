package cn.mydreamy.lib;

import cn.mydreamy.lib.utils.PngSvgConverter;
import cn.mydreamy.lib.utils.SvgPngConverter;
import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        try {
            SvgPngConverter.convertToPng1("F:\\github\\YYSvgView\\lib\\ic_1.svg", "F:\\github\\YYSvgView\\lib\\ic_1.png");
            PngSvgConverter.convertToSvg("F:\\github\\YYSvgView\\lib\\9.png", "F:\\github\\YYSvgView\\lib\\9.svg");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
