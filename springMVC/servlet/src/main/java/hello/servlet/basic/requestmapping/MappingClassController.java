package hello.servlet.basic.requestmapping;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

/**
 * Make Study. ?강 요청 매핑 (API 예시)
 *
 * Test : Postman (urlPattern)
 */
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    // Get user list
    @GetMapping
    public String getUsers() {
        return "get users";
    }

    // Add a user
    @PostMapping
    public String addUser(){
        return "post users";
    }

    // Find a user
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId=" + userId;
    }

    // Update a user
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId=" +userId;
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }
}
