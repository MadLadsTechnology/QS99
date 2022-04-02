package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CommonTestService {

    String tokenAdmin;
    String tokenStudent;
    String tokenProfessor;

    public CommonTestService(){
        tokenAdmin = UserService.generateToken("admin@admin.no", "ROLE_ADMIN");
        tokenStudent = UserService.generateToken("student@student.no", "ROLE_USER");
        tokenProfessor = UserService.generateToken("professor@professor.no", "ROLE_PROFESSOR");
    }

    public String getTokenAdmin() {
        return tokenAdmin;
    }

    public void setTokenAdmin(String tokenAdmin) {
        this.tokenAdmin = tokenAdmin;
    }

    public String getTokenStudent() {
        return tokenStudent;
    }

    public void setTokenStudent(String tokenStudent) {
        this.tokenStudent = tokenStudent;
    }

    public String getTokenProfessor() {
        return tokenProfessor;
    }

    public void setTokenProfessor(String tokenProfessor) {
        this.tokenProfessor = tokenProfessor;
    }
}
