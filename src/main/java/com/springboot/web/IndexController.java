package com.springboot.web;

import com.springboot.config.auth.LoginUser;
import com.springboot.config.auth.dto.SessionUser;
import com.springboot.service.PostsService;
import com.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    // mustache starter을 적용했기 떄문에 문자열을 반환할 때 / 앞의 경로는 src/main/resources/templates로 자동 지정되고
    // 뒤의 경로는 return하는 문자열.mustache가 붙게 된다.
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다. index.mustache에게 posts를 전달함
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser을 저장하므로
        // 로그인 성공 시 httpSession.getAttribute("user")을 통해 user에 저장된 세션의 정보를 넣어줄 수 있다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); -> @LoginUser 어노테이션으로 교체

        if (user != null) { // 세선에 저장된 값이 있을 때 model에 등록
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        // 게시글을 클릭하면 findById(id)를 통해 게시물을 dto에 담고
        PostsResponseDto dto = postsService.findById(id);
        // 템플릿 엔진이 이를 표시할 수 있도록 model 객체에 추가해준 뒤
        model.addAttribute("post", dto);
        // 게시글 수정 페이지를 띄운다
        return "posts-update";
    }
}
