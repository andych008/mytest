package hello.api;

import hello.model.UserInfo;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api_UserController")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<UserInfo> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/users/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@RequestBody UserInfo userInfo) {
        userService.save(userInfo);
        return "success";
    }

    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, @RequestBody UserInfo userInfo) {
        userService.update(id, userInfo);
        return "success";
    }

    @RequestMapping(value = "/users/{id}/del", method = RequestMethod.DELETE)
    public String update(@PathVariable Long id) {
        userService.deleteById(id);
        return "success";
    }
}
