package com.springboot.web;

import com.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class IndexController {
    // mustache starter을 적용했기 떄문에 문자열을 반환할 때 / 앞의 경로는 src/main/resources/templates로 자동 지정되고
    // 뒤의 경로는 return하는 문자열.mustache가 붙게 된다.
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model) { // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다. index.mustache에게 posts를 전달함
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("posts/save")
    public String postSave() {
        return "posts-save";
    }
}
