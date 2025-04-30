package org.nano.redstoneLink.infra.util.input;

import org.nano.redstoneLink.shared.exception.InputParseException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class InputUtil {
    private static final Map< Class<?>, Function<String, ?>> parsers = new HashMap<>();

    static {
        parsers.put(Integer.class, Integer::parseInt);
        parsers.put(Double.class, Double::parseDouble);
        parsers.put(Boolean.class, Boolean::parseBoolean);
        parsers.put(String.class, s -> s);
    }

    @SuppressWarnings("unchecked")
    public static <T> T input(Class<T> type, String value) {
        Function<String, ?> parser = parsers.get(type);

        if (parser == null) {
            throw new InputParseException("지원되지 않는 타입입니다. ");
        }

        while (true) {
            try {
                return (T) parser.apply(value);
            } catch (InputParseException e) {
                System.out.println("입력에 오류가 발생했습니다.");
            }
        }
    }
}
