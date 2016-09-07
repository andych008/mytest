package hello.api;

import hello.model.UserInfo;
import hello.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "api/users", description = "对User的操作")
@RestController("api_UserController")
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserInfo> findAll() {
        return userService.findAll();
    }


    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "userInfo", value = "用户详细实体user", required = true,
            dataType = "User", examples = @Example(@ExampleProperty("{\"name\":\"aaaddd\", \"age\":12}")))
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(@RequestBody UserInfo userInfo) {
        userService.save(userInfo);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path", defaultValue="3")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserInfo findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path", defaultValue="3"),
            @ApiImplicitParam(name = "userInfo", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String updateById(@PathVariable Long id, @RequestBody UserInfo userInfo) {
        userService.updateById(id, userInfo);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "success";
    }
}
