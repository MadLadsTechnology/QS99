package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.service.UserService;

import java.io.IOException;


public class CommonTestService {

    String tokenAdmin;
    String tokenStudent;
    String tokenProfessor;

    public CommonTestService() {
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

    public void deleteDatabase() throws IOException {
        String os = System.getProperty("os.name");
        String shell = "";
        if (os.contains("Windows")) {
            shell = "powershell.exe";
        } else if (os.contains("Linux")) {
            shell = "/bin/bash";
        }

        ProcessBuilder builder = new ProcessBuilder(shell, "rm ~/data/*");
        builder.redirectErrorStream(true);
        Process p = builder.start();
    }
}