package com.codestates.legacy_example.spring_jdbc_example;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T14:43:14+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MessageSpringJdbcMapperImpl implements MessageSpringJdbcMapper {

    @Override
    public MessageSpringJdbc messageDtoToMessage(MessageSpringJdbcPostDto messagePostDto) {
        if ( messagePostDto == null ) {
            return null;
        }

        String message = null;

        message = messagePostDto.getMessage();

        Long messageId = null;

        MessageSpringJdbc messageSpringJdbc = new MessageSpringJdbc( messageId, message );

        return messageSpringJdbc;
    }

    @Override
    public MessageSpringJdbcResponseDto messageToMessageResponseDto(MessageSpringJdbc message) {
        if ( message == null ) {
            return null;
        }

        MessageSpringJdbcResponseDto messageSpringJdbcResponseDto = new MessageSpringJdbcResponseDto();

        messageSpringJdbcResponseDto.setMessage( message.getMessage() );

        return messageSpringJdbcResponseDto;
    }
}
