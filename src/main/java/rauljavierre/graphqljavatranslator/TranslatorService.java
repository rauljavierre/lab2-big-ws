package rauljavierre.graphqljavatranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface TranslatorService {

    String translate(String langFrom, String langTo, String text);

    default Logger log() {
        return LoggerFactory.getLogger(getClass());
    }
}