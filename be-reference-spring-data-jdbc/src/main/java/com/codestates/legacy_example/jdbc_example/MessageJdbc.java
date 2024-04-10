package com.codestates.legacy_example.jdbc_example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class MessageJdbc {
    @Id
    private Long messageId;
    private String message;
}
