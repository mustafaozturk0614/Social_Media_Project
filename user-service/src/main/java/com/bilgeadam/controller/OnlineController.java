package com.bilgeadam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bilgeadam.constant.ApiUrls.ONLINE;

@RestController
@RequestMapping(ONLINE)
@RequiredArgsConstructor
public class OnlineController {
}
