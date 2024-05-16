package exercise;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


// BEGIN
public final class Validator {

    public static List<String> validate(Object object) {
        var fields = object.getClass().getDeclaredFields();
        var nullFields = new ArrayList<String>();

        for (var field : fields) {
            field.setAccessible(true);

            var notNullErrorMessage = checkNotNull(field, object);

            if (notNullErrorMessage != null) {
                nullFields.add(field.getName());
            }
        }

        return nullFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        var fields = object.getClass().getDeclaredFields();
        var errors = new HashMap<String, List<String>>();

        for (var field : fields) {
            field.setAccessible(true);

            var fieldName = field.getName();
            var fieldErrors = errors.getOrDefault(fieldName, new ArrayList<>());

            var notNullErrorMessage = checkNotNull(field, object);

            if (notNullErrorMessage != null) {
                fieldErrors.add(notNullErrorMessage);
                errors.put(fieldName, fieldErrors);
            }

            var minLengthErrorMessage = checkMinLength(field, object);

            if (minLengthErrorMessage != null) {
                fieldErrors.add(minLengthErrorMessage);
                errors.put(fieldName, fieldErrors);
            }
        }

        return errors;
    }

    private static String checkNotNull(Field field, Object object) {
        var annotation = field.getAnnotation(NotNull.class);

        if (annotation == null) {
            return null;
        }

        Object value;

        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }

        if (value == null) {
            return "can not be null";
        }

        return null;
    }

    private static String checkMinLength(Field field, Object object) {
        var annotation = field.getAnnotation(MinLength.class);

        if (annotation == null) {
            return null;
        }

        Object value;

        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }

        if (value == null) {
            return null;
        }

        double fieldNumber;

        try {
            fieldNumber = Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return "invalid number format";
        }

        var minLength = annotation.value();

        if (fieldNumber < minLength) {
            return "length less than " + minLength;
        }

        return null;
    }

}
// END
