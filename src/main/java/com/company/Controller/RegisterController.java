package com.company.Controller;

import com.company.Helper.RegisterHelper;
import com.company.Model.RegisteredURL;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

/**
 * Created by lzecevic on 6/21/17.
 */

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterHelper registerHelper;

    // Basic auth vrati npr. Basic QnViYmxlczpSYk9jQmpxSA==
    // ovaj string se mora skratiti (Odbaciti dio "Basic ") prije poziva metode decode
    @RequestMapping(method = RequestMethod.POST)
    public String register(@RequestHeader(value = "Authorization") String auth,
                           @RequestBody RegisteredURL registeredURL){

        return registerHelper.checkRequestParameters(auth, registeredURL);
    }
}
