import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rauljavierre.graphqljavatranslator.TranslatorApplication;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TranslatorApplication.class)
public class TranslatorApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIfSpanishToEnglishWorks() throws Exception {
        String query = "{\"query\":\"{getTranslationRequest(langFrom: SPANISH, langTo: ENGLISH, " +
                "text: \\\"Hola, me llamo Raúl y tengo 21 años\\\") " +
                "{\\\\resultCode\\\\errorMsg\\\\translation}}\"}";

        String response = mvc.perform(post("/graphql")
                .content(query)
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getAsyncResult().toString();

        assertEquals("{data={getTranslationRequest={resultCode=200, errorMsg=, " +
                "translation=Hello, my name is Raúl and I'm 21 years old}}}", response);
    }

    @Test
    public void testIfEnglishToSpanishWorks() throws Exception {
        String query = "{\"query\":\"{getTranslationRequest(langFrom: ENGLISH, langTo: SPANISH, " +
                "text: \\\"Hello, my name is Raúl and I'm 21 years old\\\") " +
                "{\\\\resultCode\\\\errorMsg\\\\translation}}\"}";

        String response = mvc.perform(post("/graphql")
                .content(query)
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getAsyncResult().toString();

        assertEquals("{data={getTranslationRequest={resultCode=200, errorMsg=, " +
                "translation=Hola, me llamo Raúl y tengo 21 años}}}", response);
    }

    @Test
    public void testIfNonExistingToSpanishWorks() throws Exception {
        String query = "{\"query\":\"{getTranslationRequest(langFrom: NON_EXISTING_LANGUAGE, langTo: SPANISH, " +
                "text: \\\"Hafli, faklsk Raúl y ten 21 an\\\") " +
                "{\\\\resultCode\\\\errorMsg\\\\translation}}\"}";

        String response = mvc.perform(post("/graphql")
                .content(query)
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getAsyncResult().toString();

        assertEquals("{errors=[{message=Validation error of type WrongType: argument 'langFrom' with value " +
                "'EnumValue{name='NON_EXISTING_LANGUAGE'}' is not a valid 'Language' @ 'getTranslationRequest', " +
                "locations=[{line=1, column=24}]}]}", response);
    }

    @Test
    public void testIfNonImplementedToSpanishWorks() throws Exception {
        String query = "{\"query\":\"{getTranslationRequest(langFrom: AFRIKAANS, langTo: SPANISH, " +
                "text: \\\"Hallo, my naam is Raúl en ek is 21 jaar oud\\\") " +
                "{\\\\resultCode\\\\errorMsg\\\\translation}}\"}";

        String response = mvc.perform(post("/graphql")
                .content(query)
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getAsyncResult().toString();

        assertEquals("{data={getTranslationRequest={resultCode=404, errorMsg=Sorry, but we can't translate " +
                "'Hallo, my naam is Raúl en ek is 21 jaar oud' from AFRIKAANS to SPANISH, translation=}}}", response);
    }
}
