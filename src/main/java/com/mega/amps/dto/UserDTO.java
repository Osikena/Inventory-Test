package com.mega.amps.dto;

public class UserDTO {

    private long id;
    private String fullname;
    private String username;
    private String password;
    private String role;
    private String active;
    private String status;
    private String date_time_created;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(String date_time_created) {
        this.date_time_created = date_time_created;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active='" + active + '\'' +
                ", status='" + status + '\'' +
                ", date_time_created='" + date_time_created + '\'' +
                '}';
    }
}
