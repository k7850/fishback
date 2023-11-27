package com.example.kakao.book;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;
    private final HttpSession session;




    // 북리스트
    @GetMapping("/books")
    public ResponseEntity<?> readAll() {

        List<Book> responseDTOList = bookService.readAll();

        return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    }




}
