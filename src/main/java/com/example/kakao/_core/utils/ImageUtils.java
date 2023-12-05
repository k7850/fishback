package com.example.kakao._core.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.web.multipart.MultipartFile;

import com.example.kakao._core.errors.exception.Exception400;


public class ImageUtils {




    public static String updateImage(MultipartFile photo, String imageFolder) {

        // 사진 파일이 업로드되지 않아도 MultipartFile 객체 자체는 생성된다. null로는 체크 못함
        if (photo == null || photo.getSize() == 0) { 
            System.out.println("!!!테스트 사진파일없음");
            // return null;
            throw new Exception400("!!!사진이없는데");
        }

        UUID uuid = UUID.randomUUID(); // 랜덤한 해시값을 만들어줌
        String fileName = uuid + "_" + photo.getOriginalFilename();
        System.out.println("테스트fileName "+fileName);

        Path filePath = Paths.get("./images/" + imageFolder + fileName);

        try {
            Files.write(filePath, photo.getBytes());
        } catch (Exception e) {
            throw new Exception400("사진등록중오류");
        }
        System.out.println("테스트 사진등록성공");

        return fileName;
    }




    public static String updateImageBase64(byte[] decodedBytes, String imageFolder, String email) {

        // 사진 파일이 업로드되지 않아도 MultipartFile 객체 자체는 생성된다. null로는 체크 못함
        if (decodedBytes == null) { 
            System.out.println("!!!테스트 사진파일없음");
            // return null;
            throw new Exception400("!!!사진이없는데");
        }

        UUID uuid = UUID.randomUUID(); // 랜덤한 해시값을 만들어줌
        

        String fileName = uuid + "_" + email + ".png";
        System.out.println("테스트fileName "+fileName);

        Path filePath = Paths.get("./images/" + imageFolder + fileName);

        try {
            Files.write(filePath, decodedBytes);
        } catch (Exception e) {
            throw new Exception400("사진등록중오류");
        }
        System.out.println("테스트 사진등록성공");

        return fileName;
    }




    public static String updateVideo(MultipartFile file) {

        // 사진 파일이 업로드되지 않아도 MultipartFile 객체 자체는 생성된다. null로는 체크 못함
        if (file == null || file.getSize() == 0) { 
            System.out.println("!!!테스트 동영상파일없음");
            // return null;
            throw new Exception400("!!!동영상이없는데");
        }

        UUID uuid = UUID.randomUUID(); // 랜덤한 해시값을 만들어줌
        String fileName = uuid + "_" + file.getOriginalFilename();
        System.out.println("테스트fileName "+fileName);

        Path filePath = Paths.get("./images/videos/" + fileName);

        try {

            Files.write(filePath, file.getBytes());






            // File convFile = new File("./images/videos/" + file.getOriginalFilename());
            // convFile.createNewFile();
            
            File convFile = filePath.toFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(convFile));
            double startSec = 0; // 영상에서 얻고자 하는 시간대 설정
            
            grab.seekToSecondPrecise(startSec);
            Picture picture = grab.getNativeFrame();
            BufferedImage bfImg = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bfImg, "png", new File("./images/videos/" + convFile.getName() + ".png"));


        } catch (Exception e) {
            throw new Exception400("동영상등록중오류");
        }

        System.out.println("테스트 동영상등록성공 " +fileName);
        return fileName;
    }




    

}
