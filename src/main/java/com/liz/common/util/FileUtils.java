package com.liz.common.util;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA.
 * User: liujianyu
 * Date: 12-12-25
 * Time: P.M 5:28
 */
public class FileUtils {

    private static Log log = LogFactory.getLog(FileUtils.class);

    private static final String IMAGE_DIR = "files";

    private static final String VIDEO_DIR = "videos";

    private static final String RESOURCE_DIR = "resources";

    private static final String[] images = {"jpg", "jpeg", "gif", "png", "bmp"};

    public static String copyAndCompressImage(File file, String fileName, String rootDir, String[] sizes) {
        String _fileName = file.getName();
        if (_fileName.lastIndexOf(".") == -1 || !Arrays.asList(images).contains(_fileName.substring(_fileName.lastIndexOf(".") + 1).toLowerCase()))
            throw new RuntimeException();
        return copyAndCompressImage(file, fileName, rootDir, IMAGE_DIR, sizes);
    }

    public static String copyImage(File file, String fileName, String rootDir) {
        return copyAndCompressImage(file, fileName, rootDir, IMAGE_DIR, new String[]{});
    }

    public static String copyVideo(File file, String fileName, String rootDir) {
        return copyAndCompressImage(file, fileName, rootDir, VIDEO_DIR, new String[]{});
    }

    public static String copyResource(File file,String fileName,String rootDir){
        return  copyResource(file,fileName,rootDir,RESOURCE_DIR);
    }

    /**
     * copy and compress image<br/>
     * for Example
     * <pre>
     * File file = struts2's file Object;  //this file maybe a temp file uploaded by struts2 : "*.tmp"
     * String fileName = struts2's fileName; //the name of the tmpFile's name : "aaa.jpg"
     * String rootDir = "/data/realshop/upload";    //from conf.properties
     * String objDir = "goods"; //the Object Name
     * </pre>
     * @param file     the image file uploaded
     * @param fileName fileName of image uploaded
     * @param rootDir  the root directory to save
     * @param objDir   the object name
     * @param sizes    X or S or M or L
     * @return the image url to saved in db
     */
    private static String copyAndCompressImage(File file, String fileName, String rootDir, String objDir, String[] sizes) {
        String destFilePath = null;
        if (file != null) {
            String savedFilePath = genericDestFilePath(fileName, objDir);
            String diskFilePath = format("%s/%s", rootDir, savedFilePath);
            try {
                org.apache.commons.io.FileUtils.copyFile(file, new File(diskFilePath));
                compressImages(diskFilePath, sizes);
                destFilePath = rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + savedFilePath;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return destFilePath;
    }

    private static List<String> readFile(String classPathFileName) {
        URL url = FileUtils.class.getResource(classPathFileName);
        if (url == null) {
            return null;
        }
        File file = new File(url.getPath());
        if (file.exists()) {
            try {
                return IOUtils.readLines(new FileInputStream(file));
            } catch (Exception e) {
                throw new RuntimeException("no file " + classPathFileName + " find in classpath!", e);
            }
        }
        return null;
    }

    private static String copyResource(File file, String fileName, String rootDir, String objDir){
        if(file != null){
            String saveFilePath = genericDestFilePath(fileName,objDir);
            String diskFilePath = format("%s/%s",rootDir,saveFilePath);
            try{
                org.apache.commons.io.FileUtils.copyFile(file, new File(diskFilePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + saveFilePath;
        }
        return null;
    }


    public static void compressImages(String diskFilePath, String[] sizes) {
        String compressImagePre = diskFilePath.substring(0, diskFilePath.lastIndexOf("."));
        String compressImageBack = diskFilePath.substring(diskFilePath.lastIndexOf("."));
        for (String size : sizes) {
            String[] wh = size.split("X");
            String imgFilePath = String.format("%s_%s%s", compressImagePre, size, compressImageBack);
            // 得到图片
            File input = new File(diskFilePath);
            Image imageOriginal = null;
            try {
                imageOriginal = ImageIO.read(input);
            } catch (IOException e) {
                throw new IllegalStateException("read image failed!", e);
            }
            int width = imageOriginal.getHeight(null);
//            BufferedImage src = ImageUtil.InputImage(diskFilePath);
            if(width >= 300){
                ImageUtil.resizeFixedWidth(diskFilePath, imgFilePath, Integer.parseInt(wh[0]),1f);
            } else{
                ImageUtil.resize(diskFilePath, imgFilePath, Integer.parseInt(wh[0]), Integer.parseInt(wh[1]));
            }
            //compress(diskFilePath, imgFilePath, Integer.parseInt(wh[0]), Integer.parseInt(wh[1]));
        }
    }


    /**
     * it depended jmagick, so u must make the jmagick.dll in java.libaray path
     * @param srcFilePath the source image
     * @param destFilePath the target image
     * @param width width of target image
     * @param height height of target image
     */
    private static void compress(String srcFilePath, String destFilePath, int width, int height) {
        File input = new File(srcFilePath);
        if (!input.exists()) {
            throw new IllegalArgumentException(String.format("image %s not found!", srcFilePath));
        }
        Image imageOriginal;
        try {
            imageOriginal = ImageIO.read(input);
        } catch (IOException e) {
            throw new IllegalStateException("read image failed!", e);
        }
        int w = imageOriginal.getWidth(null);
        int h = imageOriginal.getHeight(null);
        if (width > w) width = w;
        if (height > h) height = h;
        if (width > height) {
            if (height == h || h < w) height = (h * width) / w;
            else width = (w * height) / h;
        } else {
            if (width == w || h > w) width = (w * height) / h;
            else height = (h * width) / w;
        }
        try {
            imageMagick(new File(srcFilePath).getAbsolutePath(), new File(destFilePath).getAbsolutePath(), width, height);
            //compressToSquarePic(new File(destFilePath).getAbsolutePath(), new File(destFilePath).getAbsolutePath(), 1f);
        } catch (MagickException e) {
            throw new IllegalStateException("jmagick exception!", e);
        }
    }

    private static void imageMagick(String srcFilePath, String destFilePath, int width, int height) throws MagickException {
        try {
            log.info(String.format("compress source image : %s", srcFilePath));
            System.setProperty("jmagick.systemclassloader", "no");
            ImageInfo info = new ImageInfo(srcFilePath);
            MagickImage image = new MagickImage(info);
            MagickImage scaleImg = image.scaleImage(width, height);
            scaleImg.setFileName(destFilePath);
            scaleImg.writeImage(info);
            image = null;
            scaleImg = null;
            log.info(String.format("compress destination image %s success!", destFilePath));
        } catch (Throwable t) {
            log.error("call imageMagick error!", t);
            throw new RuntimeException("call imageMagick error!", t);
        }

    }

    /**
     * generic dest file path<br/>
     * for example:
     * 2012/0606/20120606
     *
     * @param fileName originFileName : "aaa.jpg"
     * @param objDir   the object' name : "goods" or other
     */
    private static String genericDestFilePath(String fileName, String objDir) {
        Calendar cal = Calendar.getInstance();
        String month;
        int monthNumber = cal.get(Calendar.MONTH) + 1;
        if (monthNumber < 10) {
            month = "0" + monthNumber;
        } else {
            month = String.valueOf(monthNumber);
        }
        String calDate;
        if (cal.get(Calendar.DATE) < 10) {
            calDate = "0" + cal.get(Calendar.DATE);
        } else {
            calDate = String.valueOf(cal.get(Calendar.DATE));
        }
        return String.format("%s/%s/%s%s/%s",objDir,
                cal.get(Calendar.YEAR), month, calDate,System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));
    }

    public static String replaceImgTag(String content, String rootDir, String staticDomain) {
        String s = content;
        String patternStr = "<img\\s*[^>]*\\s*src=[\\\"|'](.*?)[\\\"|']\\s*[^>]*>";
        Pattern patternForTag = Pattern.compile (patternStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(content);
        while (matcherForTag.find()){
            String src = matcherForTag.group(1);
            if(src.contains("dajike")){
                continue;
            }
            String destFilePath = genericDestFilePath(src, "ext");
            File dir = new File(rootDir + "/" + destFilePath.substring(0, destFilePath.lastIndexOf("/")));
            if (!dir.exists()) {
                if (!dir.mkdirs()) throw new UnsupportedOperationException(String.format("create dir %s failed!", dir.getAbsolutePath()));
            }
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                log.info("the image src is " + src);
                inputStream = new URL(src).openStream();
                fileOutputStream = new FileOutputStream(new File(rootDir + "/" + destFilePath));
                IOUtils.copy(inputStream, fileOutputStream);
                log.info("存入数据库的路径为" + staticDomain + "/" + rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + destFilePath);
                s = s.replace(src, staticDomain + "/" + rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + destFilePath);
            } catch (Exception e) {
                log.error(String.format("替换图片%s失败!", src), e);
            }finally {
                try {
                    if(inputStream != null)
                    inputStream.close();
                    if(fileOutputStream != null)
                    fileOutputStream.close();
                    log.info("资源关闭成功！");
                } catch (IOException e) {
                    log.error(String.format("替换图片%s失败!关闭资源失败!", src), e);
                }

            }
        }
        return s;
    }

    public static List<String> acquireImgTag(String content) {
        String patternStr = "<img\\s*[^>]*\\s*src=[\\\"|'](.*?)[\\\"|']\\s*[^>]*>";
        Pattern patternForTag = Pattern.compile (patternStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(content);
        List<String> list = new ArrayList<String>();
        while (matcherForTag.find()){
            String src = matcherForTag.group(1);
            System.out.println(src);
            list.add(src);
        }
        return list;
    }

    public static String saveNetworkImage(String src, String rootDir){
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String destFilePath = genericDestFilePath(".jpg", IMAGE_DIR);
        String databaseUrl = "";
        try {
            inputStream = new URL(src).openStream();
            fileOutputStream = new FileOutputStream(new File(rootDir + "/" + destFilePath));
            IOUtils.copy(inputStream, fileOutputStream);
            databaseUrl = rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + destFilePath;
            log.info("saveNetworkImage database image url : /" + databaseUrl);
        } catch (IOException e) {
           log.error("save Network Image error",e);
        } finally {
            try {
                if(inputStream != null)
                    inputStream.close();
                if(fileOutputStream != null)
                    fileOutputStream.close();
                log.info("资源关闭成功！");
            } catch (IOException e) {
                log.error(String.format("save Network Image error"), e);
            }
        }
        return databaseUrl;
    }
}
