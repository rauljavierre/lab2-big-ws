package rauljavierre.graphqljavatranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TranslatorServiceForGiftImpl implements TranslatorService{

    @Override
    public String translate(String langFrom, String langTo, String text) {
        // Here is where it should be an API or library with translations methods... But I didn't find any
        // which was free to use, so I will code only some examples
        if(langFrom.equals("SPANISH") && langTo.equals("ENGLISH") && text.equals("Hola, me llamo Raúl y tengo 21 años")) {
            return "Hello, my name is Raúl and I'm 21 years old";
        }
        else if(langFrom.equals("ENGLISH") && langTo.equals("SPANISH") && text.equals("Hello, my name is Raúl and I'm 21 years old")) {
            return "Hola, me llamo Raúl y tengo 21 años";
        }
        else {
            return "--- NO TRANSLATION AVAILABLE ---";
        }
    }

    @Override
    public Logger log() {
        return LoggerFactory.getLogger(getClass());
    }
}
