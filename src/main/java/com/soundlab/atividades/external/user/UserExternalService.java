package com.soundlab.atividades.external.user;

import com.soundlab.atividades.external.AbstractExternalHttpService;
import com.soundlab.atividades.external.user.models.UserResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExternalService extends AbstractExternalHttpService<IUserApi> {

    protected UserExternalService(@Value("${external.users.base-url}") String baseUrl) {
        super(baseUrl);
    }

    public List<UserResponse> fetchAll() {
        return call(api.getUsers());
    }

    public UserResponse fetchById(Long id) {
        return call(api.getUsersById(id));
    }
}
