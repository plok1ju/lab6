package itmo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import itmo.commands.Command;

import java.io.IOException;

public class CommandInfoSerializer extends JsonSerializer<Command> {

    @Override
    public void serialize(Command command, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
