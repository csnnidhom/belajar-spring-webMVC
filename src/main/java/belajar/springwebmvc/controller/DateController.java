package belajar.springwebmvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");

    @GetMapping(path = "/date")
    @ResponseBody
    public String getData(@RequestParam(name = "date") Date date) throws IOException {
        return "Date : " + dateFormat.format(date);
    }

}
