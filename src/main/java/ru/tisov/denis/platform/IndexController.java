package ru.tisov.denis.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dinyat
 *         30/03/2017
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/*"})
    public String getEnvironmentPage() {
        return "app/index.html";
    }

}
