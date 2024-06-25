package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.rest.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String getTest() {
        return "Test";
    }

    @GetMapping("/{numberStr}")
    public String getTest2( @PathVariable("numberStr") String number) {
        if(StringUtils.isEmpty(number)) {
            throw new NotFoundException();
        }
        return "Test " + number;
    }


}
