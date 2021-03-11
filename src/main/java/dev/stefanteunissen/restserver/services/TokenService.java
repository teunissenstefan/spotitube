package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TokenDAO;

import javax.inject.Inject;

public class TokenService {
    @Inject
    public TokenDAO tokenDAO;

    /**
     * Set the token after logging in
     *
     * @param id
     * @return
     */
    public String setToken(int id) {
        return tokenDAO.setToken(id);
    }
}
