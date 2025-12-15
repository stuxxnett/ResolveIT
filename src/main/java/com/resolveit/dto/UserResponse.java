package com.resolveit.dto;

public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private String role;
    private String token;

    // 🔥 REQUIRED BY SPRING/JACKSON
    public UserResponse() {}

    // MAIN CONSTRUCTOR
    public UserResponse(Long id, String name, String username, String role, String token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
