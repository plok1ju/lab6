package itmo.deserializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.utils.CommandArguments;

public class ArgumentsDeserializer {
    public static CommandArguments deserialize(String jsonTypes, String jsonArguments) throws JsonProcessingException {
        Class[] types = new ObjectMapper().readValue(jsonTypes, Class[].class);
        String[] argsString = new ObjectMapper().readValue(jsonArguments, String[].class);
        Object[] args = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            args[i] = new ObjectMapper().readValue(argsString[i], types[i]);
        }
        CommandArguments commandArguments = new CommandArguments();
        commandArguments.arguments = args;
        commandArguments.types = types;
        return commandArguments;
    }
}
