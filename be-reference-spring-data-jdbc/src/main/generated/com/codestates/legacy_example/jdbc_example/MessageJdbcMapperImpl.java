package com.codestates.legacy_example.jdbc_example;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T14:43:13+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MessageJdbcMapperImpl implements MessageJdbcMapper {

    @Override
    public MessageJdbc messageDtoToMessage(MessageJdbcPostDto messagePostDto) {
        if ( messagePostDto == null ) {
            return null;
        }

        String message = null;

        message = messagePostDto.getMessage();

        Long messageId = null;

        MessageJdbc messageJdbc = new MessageJdbc( messageId, message );

        return messageJdbc;
    }

    @Override
    public MessageJdbcResponseDto messageToMessageResponseDto(MessageJdbc message) {
        if ( message == null ) {
            return null;
        }

        MessageJdbcResponseDto messageJdbcResponseDto = new MessageJdbcResponseDto();

        messageJdbcResponseDto.setMessage( message.getMessage() );

        return messageJdbcResponseDto;
    }
}
