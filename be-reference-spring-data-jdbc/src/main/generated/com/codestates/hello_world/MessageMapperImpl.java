package com.codestates.hello_world;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T14:43:14+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message messageDtoToMessage(MessagePostDto messagePostDto) {
        if ( messagePostDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setMessage( messagePostDto.getMessage() );

        return message;
    }

    @Override
    public MessageResponseDto messageToMessageResponseDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageResponseDto messageResponseDto = new MessageResponseDto();

        messageResponseDto.setMessageId( message.getMessageId() );
        messageResponseDto.setMessage( message.getMessage() );

        return messageResponseDto;
    }
}
