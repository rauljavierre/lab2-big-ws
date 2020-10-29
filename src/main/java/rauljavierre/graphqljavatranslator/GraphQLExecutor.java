package rauljavierre.graphqljavatranslator;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExecutor {

    public DataFetcher getTranslationRequest() {
        return dataFetchingEnvironment -> {
            String langFrom = dataFetchingEnvironment.getArgument("langFrom");
            String langTo = dataFetchingEnvironment.getArgument("langTo");
            String text = dataFetchingEnvironment.getArgument("text");

            // Here is where it should be an API or library with translations methods... But I didn't find any
            // which was free to use, so I will code only some examples
            if(langFrom.equals("SPANISH") && langTo.equals("ENGLISH") && text.equals("Hola, me llamo Raúl y tengo 21 años")) {
                return "Hello, my name is Raúl and I'm 21 years old";
            }
            else if(langFrom.equals("ENGLISH") && langTo.equals("SPANISH") && text.equals("Hello, my name is Raúl and I'm 21 years old")) {
                return "Hola, me llamo Raúl y tengo 21 años";
            }
            else {
                return "Sorry, but we can't translate '" + text + "' from " + langFrom + " to " + langTo;
            }
        };
    }
}
