package com.local.events.app.controllers;

import com.local.events.app.models.Response;
import com.local.events.app.services.CommentService;
import com.local.events.app.services.EventService;
import com.local.events.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private EventService eventService;


    @RequestMapping("")
    private Response events(){
        return new Response( true, "Testing", null );
    }


}
