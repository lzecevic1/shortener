package com.company.Controller;

import com.company.Helper.RegisterHelper;
import com.company.Model.RegisteredURL;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        RegisteredURL newURL = registerHelper.getShortURLIfAuthenticated(auth, registeredURL);


        return "http://localhost:8080/" + newURL.getShortURL();
    }
}
