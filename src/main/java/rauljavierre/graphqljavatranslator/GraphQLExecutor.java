package rauljavierre.graphqljavatranslator;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExecutor {

    @Autowired
    TranslatorService translatorService;

    public DataFetcher<ResponseDTO> getTranslationRequest() {
        return dataFetchingEnvironment -> {
            String langFrom = dataFetchingEnvironment.getArgument("langFrom");
            String langTo = dataFetchingEnvironment.getArgument("langTo");
            String text = dataFetchingEnvironment.getArgument("text");

            int resultCode = 200;
            String translation = translatorService.translate(langFrom, langTo, text);
            String errorMsg = "";

            if (translation.equals("--- NO TRANSLATION AVAILABLE ---")) {
                translation = "";
                errorMsg = "Sorry, but we can't translate '" + text + "' from " + langFrom + " to " + langTo;
                resultCode = 404;
            }

            return new ResponseDTO(resultCode, translation, errorMsg);
        };
    }
}
