package com.ll.basic1.boundedContext.home.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeController {
    private int count;
    ArrayList<Person> list;
    private int id = 0;

    public HomeController() {
        count = -1;
        list = new ArrayList<>();
    }

    @GetMapping("/home/main")
    //만약에  /home/main 이런 요청이 오면 아래 메서드를 실행해줘

    @ResponseBody
    // 아래 메서드를 실행한 후 그 리턴 값을 응답으로 삼아줘
    public String showMain() {
        return "안녕하세요!!";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease() { // 리턴되는 int 값은 String화 되어서 출력
        count++;
        return count;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    //int a는 쿼리스트링에서 a파라미터의 값을 정수화한 값이어야 한다.
    //@RequestParam 생략가능
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String PersonAdd(String name, int age) {
        Person p = new Person(name, age);
        list.add(p);
        return p.getId() + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String PersonRemove(int id) {
        boolean removed = list.removeIf(person -> person.getId() == id);
        if (removed == false) {
            return "%d번 사람이 존재하지 않습니다!".formatted(id);
        }
        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String PersonModify(int id, String name, int age) {
        Person find = list
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (find == null) {
            return "%d번 사람이 존재하지 않습니다!".formatted(id);
        }
        find.setName(name);
        find.setAge(age);
        return "%d번 사람이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/home/people")
    @ResponseBody
    public String showPerson() {
        return list.toString();
    }

    @GetMapping("/home/reqAndResp")
    @ResponseBody
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age"));
        resp.getWriter().append("Hello, you are %d years old.".formatted(age));
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int countInCookie = 0;

        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }


    @AllArgsConstructor //모든 생성자
    @Getter // 따로 선언하지 않아도 Get 생성
    @Setter //set도 마찬가지
    class Person {
        private static int lastid;
        private int id;
        private String name;
        private int age;

        static {
            lastid = 0;
        }

        Person(String name, int age) {
            this(++lastid, name, age);
        }

        Person(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "{" +
                    "id=" + getId() +
                    ", name='" + getName() + '\'' +
                    ", age=" + getAge() +
                    '}' + "\n";
        }
    }
}
