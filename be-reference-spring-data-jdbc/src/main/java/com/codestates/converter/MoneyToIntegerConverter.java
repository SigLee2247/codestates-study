package com.codestates.converter;

import com.codestates.values.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class MoneyToIntegerConverter implements Converter<Money, Integer> {
    @Override
    public Integer convert(Money source) {
        return source.getValue();
    }
}
