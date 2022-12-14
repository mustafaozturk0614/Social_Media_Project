package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.UpdateUsernameEmailModel;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "${rabbitmq.userUpdateQueue}")
    public void updateUser(UpdateUsernameEmailModel model) {
        log.info("User : {}", model.toString());
        authService.updateAuth(model);
    }


}
