package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TokenDAO;

public class TokenService {
    public TokenDAO tokenDAO = new TokenDAO();

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
