package com.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // mustache starter을 적용했기 떄문에 문자열을 반환할 때 / 앞의 경로는 src/main/resources/templates로 자동 지정되고
    // 뒤의 경로는 return하는 문자열.mustache가 붙게 된다.
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("posts/save")
    public String postSave() {
        return "posts-save";
    }
}
