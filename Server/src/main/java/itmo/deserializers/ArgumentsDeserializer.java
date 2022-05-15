package itmo.deserializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import itmo.utils.CommandArguments;

import java.io.IOException;
import java.util.Iterator;

public class ArgumentsDeserializer extends StdDeserializer<CommandArguments> {

    public ArgumentsDeserializer(){
        this(null);
    }

    public ArgumentsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CommandArguments deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = deserializationContext.readTree(jsonParser);
        Iterator<JsonNode> iterator = jsonNode.elements();
        String typesString = iterator.next().toString();
        Class[] types = new ObjectMapper().readValue(typesString, Class[].class);

        if (types == null){
            CommandArguments commandArguments = new CommandArguments();
            commandArguments.arguments = new Object[0];
            commandArguments.types = new Class[0];
            return commandArguments;
        }
        Iterator<JsonNode> argsIterator = iterator.next().elements();
        Object[] args = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            String jsonArg = argsIterator.next().toString();
            //System.out.println(jsonArg);
            args[i] = new ObjectMapper().readValue(jsonArg, types[i]);
        }
        CommandArguments commandArguments = new CommandArguments();
        commandArguments.arguments = args;
        commandArguments.types = types;
        return commandArguments;
    }
}
