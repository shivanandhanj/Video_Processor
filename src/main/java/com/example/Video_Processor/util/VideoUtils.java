package com.example.Video_Processor.util;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoUtils {

    public static void extractFrame(String videoFilePath, String outputImagePath) throws IOException {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(videoFilePath)));
            Picture picture = grab.getNativeFrame(); // grab first frame
            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);

            ImageIO.write(bufferedImage, "png", new File(outputImagePath));
        } catch (JCodecException e) {
            throw new IOException("Error extracting frame: " + e.getMessage(), e);
        }
    }
}
