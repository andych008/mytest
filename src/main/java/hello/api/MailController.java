package hello.api;

import com.fasterxml.jackson.annotation.JsonView;
import hello.config.MailConfiguration;
import hello.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api_MailController")
@RequestMapping("api/mail")
public class MailController {

    @Autowired
    private MailConfiguration mailConfiguration;


    @JsonView(MailConfiguration.Show.class)
    @RequestMapping()
    public ResponseResult<MailConfiguration> findAll() {
        return ResponseResult.<MailConfiguration>newBuilder()
                .build(mailConfiguration);
    }
}
