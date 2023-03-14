package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 컴퓨터가 이해할 수 있는 주석과 같음 (개발자가 스프링부트에게 말함)
// 아래 있는 HomeController는 컨트롤러다

public class HomeController {
    int count=-1;
    @GetMapping("/home/main")
    //만약에  /home/main 이런 요청이 오면 아래 메서드를 실행해줘

    @ResponseBody
    // 아래 메서드를 실행한 후 그 리턴 값을 응답으로 삼아줘
    public String showMain(){
        return "안녕하세요!!";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }
    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다.";
    }
    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease(){ // 리턴되는 int 값은 String화 되어서 출력
        count++;
        return count;
    }
}
