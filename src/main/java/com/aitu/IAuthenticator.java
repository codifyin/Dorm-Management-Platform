package com.aitu;

public interface IAuthenticator {

    boolean checkCredentials(String login, String password);
}
