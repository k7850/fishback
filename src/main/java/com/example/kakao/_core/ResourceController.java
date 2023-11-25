package com.example.kakao._core;

import com.example.kakao._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class ResourceController {

    private final HttpSession session;

    @GetMapping("/image")
    public ResponseEntity<?> image(String route) {

        try {

            // 사진파일 주기
            String imagePath = "images/" + route;
            Path path = Paths.get(imagePath);


            // 이미지 파일을 바이트 배열로 읽기
            byte[] imageBytes = Files.readAllBytes(path);

            // 이미지를 ByteArrayResource로 감싸서 반환
            ByteArrayResource resource = new ByteArrayResource(imageBytes);

            // 이미지 파일의 MIME 타입 설정
            MediaType mediaType = MediaType.IMAGE_JPEG; // 또는 MediaType.IMAGE_PNG, MediaType.IMAGE_GIF 등

            System.out.println("이미지 : " + path);

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .contentLength(imageBytes.length)
                    .body(resource);

        } catch (Exception e) {
            System.out.println(route + "사진없음");
            throw new Exception404(route + "사진없음");
        }


    }


    @GetMapping("/video")
    public ResponseEntity<?> video(@RequestParam String route) {
        try {
            // 동영상 파일 주기
            String videoPath = "images/" + route; // 동영상 파일이 저장된 경로
            Path path = Paths.get(videoPath);

            // 동영상 파일을 바이트 배열로 읽기
            byte[] videoBytes = Files.readAllBytes(path);

            // 동영상을 ByteArrayResource로 감싸서 반환
            ByteArrayResource resource = new ByteArrayResource(videoBytes);

            // 동영상 파일의 MIME 타입 설정
            MediaType mediaType = MediaType.parseMediaType("video/mp4"); // 동영상 형식에 따라 변경

            System.out.println("동영상: " + path);

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .contentLength(videoBytes.length)
                    .body(resource);
        } catch (Exception e) {
            System.out.println(route + " 동영상 없음");
            throw new Exception404(route + "동영상 없음");
        }
    }


}
