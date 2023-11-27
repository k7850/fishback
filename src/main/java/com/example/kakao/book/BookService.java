package com.example.kakao.book;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.book.Book;
import com.example.kakao.book.BookRepository;
import com.example.kakao.comment.Comment;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;





    // 북리스트
    public List<Book> readAll() {
        
        List<Book> bookList = bookRepository.findAll();

        return bookList;
    }





}