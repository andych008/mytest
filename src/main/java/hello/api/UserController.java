package hello.api;

import hello.model.ResponseResult;
import hello.model.UserInfo;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("api_UserController")
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public ResponseResult<List<UserInfo>> findAll() {
        return ResponseResult.<List<UserInfo>>newBuilder()
                .build(userService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseResult add(@Valid @RequestBody UserInfo userInfo, Errors errors) {

        return ResponseResult.newBuilder()
                .successMsg("添加成功!")
                .failMsg(errors, "添加失败!")
                .build(userService.save(userInfo));
    }

    @RequestMapping("{id}")
    public ResponseResult<UserInfo> findById(@PathVariable Long id) {

        return ResponseResult.<UserInfo>newBuilder()
                .failMsg("未找到id为" + id + "的用户信息")
                .build(userService.findById(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseResult updateById(@PathVariable Long id, @Valid @RequestBody UserInfo userInfo, Errors errors) {

        return ResponseResult.newBuilder()
                .successMsg("修改成功!")
                .failMsg(errors, "修改失败!")
                .build(userService.updateById(id, userInfo));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseResult deleteById(@PathVariable Long id) {

        return ResponseResult.newBuilder()
                .successMsg("删除成功!")
                .failMsg("删除失败!")
                .build(userService.deleteById(id));
    }
}
