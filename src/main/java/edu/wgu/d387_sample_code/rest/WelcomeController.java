package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.D387SampleCodeApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class WelcomeController {

    @GetMapping("/messages")
    public String getMessages() {
        List<String> messageList = D387SampleCodeApplication.getMessages();
        return String.join(" ", messageList);
    }
}
