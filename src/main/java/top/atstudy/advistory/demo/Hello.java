package top.atstudy.advistory.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 21:51
 */
@RestController
@RequestMapping("/hello")
public class Hello {

    @ResponseBody
    @GetMapping("/say")
    public Person saye(@RequestParam(value = "param", required = false) String param){

        Person person = new Person();
        person.setId(123L);
        person.setAge(25);
        person.setPhone("18789836785");
        person.setGender(true);

        if(StringUtils.isBlank(param))
            person.setName(param);
        person.setName("张三丰");

        return person;
    }

}
