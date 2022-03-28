package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hei")
    public String halla(){
        return "Hei";
    }
}
