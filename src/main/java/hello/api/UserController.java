package hello.api;

import hello.model.UserInfo;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api_UserController")
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public List<UserInfo> findAll() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody UserInfo userInfo) {
        userService.save(userInfo);
        return "success";
    }

    @RequestMapping("{id}")
    public UserInfo findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String updateById(@PathVariable Long id, @RequestBody UserInfo userInfo) {
        userService.updateById(id, userInfo);
        return "success";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "success";
    }
}
