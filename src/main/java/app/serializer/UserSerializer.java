package app.serializer;

import app.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class UserSerializer extends StdSerializer<User> {

    public UserSerializer(){
        super(User.class);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("surname", user.getSurname());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeStringField("patronymic", user.getPatronymic());
//        jsonGenerator.writeObjectField("birthDate", user.getBirthDate());
        jsonGenerator.writeStringField("birthDate", date.format(user.getBirthDate()));
        jsonGenerator.writeObjectField("sex", user.getSex());
        jsonGenerator.writeNumberField("organization", user.getOrganization()); // внешний ключ для таблицы users
        jsonGenerator.writeStringField("name of organization", user.getOrganizationname());
        jsonGenerator.writeEndObject();
    }
}
