package top.iqqcode.hello

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author: iqqcode
 * @Date: 2023-09-19 01:55
 * @Description: 网页测试Hello-World
 */

@Controller
@RequestMapping("/hello") // 这是Controller路径
class HelloController {
    @ResponseBody // 让数据直接显示在页面上
    @RequestMapping("kotlin") // 这是方法路径
    fun hello(): String {
        return "Hello World!"
    }
}