package com.mega.amps.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users_info")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @NotNull
    @Column(name = "role", length = 100)
    private String role;

    @Column(name = "active", length = 100)
    private String active;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "date_time_created", length = 100)
    private String date_time_created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        return "User{" +
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
