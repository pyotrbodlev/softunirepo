package org.softuni.usersystem.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity(name = "users")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private byte[] profilePicture;
    private Date registeredOn;
    private Date lastTimeLoggedIn;
    private int age;
    private boolean isDeleted;
    private String firstName;
    private String lastName;
    private Set<User> friends;
    private Set<User> friendsOf;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password too short");
        } else if (password.length() > 40) {
            throw new IllegalArgumentException("Password too long");
        } else if (password.chars().noneMatch(Character::isDigit) ||
                password.chars().noneMatch(Character::isLowerCase) ||
                password.chars().noneMatch(Character::isUpperCase)) {
            throw new IllegalArgumentException("Invalid password");
        }

        this.password = password;
    }

    @Column(nullable = false)
    @Email(regexp = "[A-Za-z0-9]+@\\w+\\.\\w+")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "profile_picture")
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column
    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient()
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @ManyToMany(mappedBy = "friends", cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.EAGER)
    public Set<User> getFriendsOf() {
        return friendsOf;
    }

    public void setFriendsOf(Set<User> friendsOf) {
        this.friendsOf = friendsOf;
    }
}
