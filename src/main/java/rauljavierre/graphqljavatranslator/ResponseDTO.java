package rauljavierre.graphqljavatranslator;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private int resultCode;
    private String translation;
    private String errorMsg;

    public ResponseDTO(int resultCode, String translation, String errorMsg) {
        this.resultCode = resultCode;
        this.translation = translation;
        this.errorMsg = errorMsg;
    }
}
