package hello.api;

import com.fasterxml.jackson.annotation.JsonView;
import hello.config.MailConfiguration;
import hello.config.MailYamlConfiguration;
import hello.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api_MailController")
@RequestMapping("api/mail")
public class MailController {

    @Autowired
    private MailConfiguration mailConfiguration;

    @Autowired
    private MailYamlConfiguration mailYamlConfiguration;

    @Value("${mail.host}")
    private String host;

    @JsonView(MailConfiguration.Show.class)
    @RequestMapping()
    public ResponseResult<MailConfiguration> findAll() {
        return ResponseResult.<MailConfiguration>newBuilder()
                .build(mailConfiguration);
    }

    @RequestMapping("host")
    public ResponseResult<String> host() {
        return ResponseResult.<String>newBuilder()
                .build(host);
    }

    @RequestMapping("yamlhost")
    public ResponseResult<String> yamlhost() {
        return ResponseResult.<String>newBuilder()
                .build(mailYamlConfiguration.getHost());
    }
}
