package io.github.koxx12_dev.skyclient_installer_java;

import org.apache.commons.lang.SystemUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Utils {

    public static String request(String URL) throws IOException {

        java.net.URL url = new URL(URL);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";

    }

    public static void Download(String URL, String Loc) throws IOException {
        java.net.URL url = new URL(URL);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "NING/1.0");
        BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        FileOutputStream fileOS = new FileOutputStream(Loc);
        byte[] data = new byte[1024];
        int byteContent;
        while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
            fileOS.write(data, 0, byteContent);
        }
    }

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static String replaceWithOsPath(String path){
        String plc = "";
        if (SystemUtils.IS_OS_WINDOWS) {
            plc = "\\\\";
        } else if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_LINUX) {
            plc = "/";
        } else {
            System.out.println("HOW TF DID YOU GOT HERE WITH "+System.getProperty("os.name")+"\nIT SHOULDN'T BE POSSIBLE");
            System.exit(-1);
        }

        return path.replaceAll("\\|\\|",plc);
    }
    public static String urlEncode(String toEncode){
        //if null, keep null (no gain or loss of safety)
        if (toEncode==null)
            return null;

        StringBuilder sb=new StringBuilder();
        for (char character: toEncode.toCharArray())//for every character in the string
            switch (character){//if the character needs to be escaped, add its escaped value to the StringBuilder
                case '!': sb.append("%21"); continue;
                case '#': sb.append("%23"); continue;
                case '$': sb.append("%24"); continue;
                case '&': sb.append("%26"); continue;
                case '\'': sb.append("%27"); continue;
                case '(': sb.append("%28"); continue;
                case ')': sb.append("%29"); continue;
                case '*': sb.append("%2A"); continue;
                case '+': sb.append("%2B"); continue;
                case ',': sb.append("%2C"); continue;
                case '/': sb.append("%2F"); continue;
                case ':': sb.append("%3A"); continue;
                case ';': sb.append("%3B"); continue;
                case '=': sb.append("%3D"); continue;
                case '?': sb.append("%3F"); continue;
                case '@': sb.append("%40"); continue;
                case '[': sb.append("%5B"); continue;
                case ']': sb.append("%5D"); continue;
                case ' ': sb.append("%20"); continue;
                case '"': sb.append("%22"); continue;
                case '%': sb.append("%25"); continue;
                case '-': sb.append("%2D"); continue;
                case '<': sb.append("%3C"); continue;
                case '>': sb.append("%3E"); continue;
                case '\\': sb.append("%5C"); continue;
                case '^': sb.append("%5E"); continue;
                case '_': sb.append("%5F"); continue;
                case '`': sb.append("%60"); continue;
                case '{': sb.append("%7B"); continue;
                case '|': sb.append("%7C"); continue;
                case '}': sb.append("%7D"); continue;
                case '~': sb.append("%7E"); continue;
                default: sb.append(character);//if it does not need to be escaped, add the character itself to the StringBuilder
            }
        return sb.toString();//build the string, and return
    }

}

