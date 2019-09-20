package com.isa.zajavieni.servlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@WebServlet(name = "login", value = "/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

    private static final Collection<String> SCOPES = Arrays.asList("email", "profile");
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private GoogleAuthorizationCodeFlow flow;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String state = new BigInteger(130, new SecureRandom()).toString(32);  // prevent request forgery
        req.getSession().setAttribute("state", state);

        if (req.getAttribute("loginDestination") != null) {
            req
                    .getSession()
                    .setAttribute("loginDestination", (String) req.getAttribute("loginDestination"));
        } else {
            req.getSession().setAttribute("loginDestination", "/books");
        }

        flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                getServletContext().getInitParameter("bookshelf.clientID"),
                getServletContext().getInitParameter("bookshelf.clientSecret"),
                SCOPES)
                .build();

        // Callback url should be the one registered in Google Developers Console
        String url =
                flow.newAuthorizationUrl()
                        .setRedirectUri(getServletContext().getInitParameter("bookshelf.callback"))
                        .setState(state)            // Prevent request forgery
                        .build();
        resp.sendRedirect(url);
    }
}}