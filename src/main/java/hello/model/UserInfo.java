package hello.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserInfo {
    public interface Summary extends ResponseResult.View {}

    @JsonView(Summary.class)
    private Long id;

    @JsonView(Summary.class)
    @NotBlank(message = "姓名不能为空")
    private String name;

//    @JsonIgnore  //所有的输出中都不会有这个字段
    @NotNull(message = "年龄不能为空")
    @Max(value = 150, message = "年龄不能大于150")
    @Min(value = 1, message = "年龄不能小于1")
    private Integer age;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
