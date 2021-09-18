package br.com.newtonpaiva.pi5sendMail.dto;


public class FeedbackDTO {

    private String email;

    private String path;


    public FeedbackDTO(String email , String path) {
        this.email = email;
        this.path = path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
