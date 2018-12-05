package com.local.events.app.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "*First Name must be at least 3 characters")
    private String first_name;

    @Size(min=2, message = "*Last Name must be at least 3 characters")
    private String last_name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min=5, message = "Password must be at least 5 character")
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(updatable = false)
    private Date createAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){this.createAt = new Date();}
    @PreUpdate void onUpdate(){this.updatedAt = new Date();}


    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private List<Event> eventHosts = new ArrayList<Event>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<Event>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventLikes = new ArrayList<Event>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    public User(){}
    public User(String first_name, String last_name, String email, String password, String passwordConfirmation) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Event> getEventHosts() {
        return eventHosts;
    }

    public void setEventHosts(List<Event> eventHosts) {
        this.eventHosts = eventHosts;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Event> getEventLikes() {
        return eventLikes;
    }

    public void setEventLikes(List<Event> eventLikes) {
        this.eventLikes = eventLikes;
    }
}