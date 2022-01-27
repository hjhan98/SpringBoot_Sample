package syscon.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
//         웹 어플리케이션에서 /hello 라는게 입력되면 요거 호출됨
        model.addAttribute("data", "hello!!");
        return "hello";
//        http://localhost:8080/hello
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);   //name으로 받은게 모델에 담겨서 템플릿으로 전송됨
        return "hello-template";
//        http://localhost:8080/hello-mvc?name=spring!
    }

    @GetMapping("hello-string")
    @ResponseBody   //Http 응답 Body부에 이거를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;        //데이터만 그대로 보여줌
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();  //객체생성
        hello.setName(name);        //파라미터로 받은 이름 넣어주고  (ctrl + shift + enter)
        return hello;               //객체를 리턴

        //input : http://localhost:8080/hello-api?name=hajeong!!!!!
        //output : {"name":"하정!!!!!"} ->json형태로 반환
    }

    static class Hello{
        private String name;

        public String getName(){    //꺼낼때
            return name;
        }
        public void setName(String name){   //넣을때
            this.name = name;
        }

    }
}
