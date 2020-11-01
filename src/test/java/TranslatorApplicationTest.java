import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import rauljavierre.graphqljavatranslator.TranslatorApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TranslatorApplication.class)
public class TranslatorApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIfSpanishToEnglishWorks() throws Exception {
        JSONObject request = new JSONObject();
        request.put("query",
                "{getTranslationRequest(langFrom: SPANISH, langTo: ENGLISH, text: \"Hola, me llamo Raúl " +
                        "y tengo 21 años\") {resultCode errorMsg translation}}");

        MvcResult result = mvc.perform(post("/graphql")
                .content(request.toJSONString())
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.getTranslationRequest.resultCode", is(200)))
                .andExpect(jsonPath("$.data.getTranslationRequest.errorMsg", is("")))
                .andExpect(jsonPath("$.data.getTranslationRequest.translation",
                        is("Hello, my name is Raúl and I'm 21 years old")));
    }

    @Test
    public void testIfEnglishToSpanishWorks() throws Exception {
        JSONObject request = new JSONObject();
        request.put("query",
                "{getTranslationRequest(langFrom: ENGLISH, langTo: SPANISH, text: \"Hello, my name is Raúl and " +
                        "I'm 21 years old\") {resultCode errorMsg translation}}");

        MvcResult result = mvc.perform(post("/graphql")
                .content(request.toJSONString())
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.getTranslationRequest.resultCode", is(200)))
                .andExpect(jsonPath("$.data.getTranslationRequest.errorMsg", is("")))
                .andExpect(jsonPath("$.data.getTranslationRequest.translation",
                        is("Hola, me llamo Raúl y tengo 21 años")));
    }

    @Test
    public void testIfNonExistingToSpanishWorks() throws Exception {
        JSONObject request = new JSONObject();
        request.put("query",
                "{getTranslationRequest(langFrom: NON_EXISTING_LANGUAGE, langTo: SPANISH, text: \"Hafli, faklsk " +
                        "Raúl y ten 21 an\") {resultCode errorMsg translation}}");

        MvcResult result = mvc.perform(post("/graphql")
                .content(request.toJSONString())
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors[0].message", is("Validation error of type " +
                        "WrongType: argument 'langFrom' with value 'EnumValue{name='NON_EXISTING_LANGUAGE'}' is not " +
                        "a valid 'Language' @ 'getTranslationRequest'")));
    }

    @Test
    public void testIfNonImplementedToSpanishWorks() throws Exception {
        JSONObject request = new JSONObject();
        request.put("query",
                "{getTranslationRequest(langFrom: AFRIKAANS, langTo: SPANISH, text: \"Hallo, my naam is Raúl " +
                        "en ek is 21 jaar oud\") {resultCode errorMsg translation}}");

        MvcResult result = mvc.perform(post("/graphql")
                .content(request.toJSONString())
                .contentType("application/json;UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.getTranslationRequest.resultCode", is(404)))
                .andExpect(jsonPath("$.data.getTranslationRequest.errorMsg", is("Sorry, but we can't " +
                        "translate 'Hallo, my naam is Raúl en ek is 21 jaar oud' from AFRIKAANS to SPANISH")))
                .andExpect(jsonPath("$.data.getTranslationRequest.translation",
                        is("")));
    }
}
