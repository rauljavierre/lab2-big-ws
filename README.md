# Web Engineering 2020-2021 / Big Web Services
<br />

## Examples at localhost:8080/graphql

### Query (Spanish to English):
```
{
    getTranslationRequest(langFrom: SPANISH, langTo: ENGLISH, text: "Hola, me llamo Raúl y tengo 21 años")
}
```


```
{
    "data": {
        "getTranslationRequest": "Hello, my name is Raúl and I'm 21 years old"
    }
}
```

<br />

### Query (English to Spanish):
```
{
    getTranslationRequest(langFrom: ENGLISH, langTo: SPANISH, text: "Hello, my name is Raúl and I'm 21 years old")
}
```

```
{
    "data": {
        "getTranslationRequest": "Hola, me llamo Raúl y tengo 21 años"
    }
}

```
### Query (non-existing to Spanish):
```
{
    getTranslationRequest(langFrom: NON_EXISTING_LANGUAGE, langTo: SPANISH, text: "Hafli, faklsk Raúl y ten 21 an")
}
```


```
{
    "errors": [
        {
            "message": "Validation error of type WrongType: argument 'langFrom' with value 'EnumValue{name='NON_EXISTING_LANGUAGE'}' is not a valid 'Language' @ 'getTranslationRequest'",
            "locations": [
                {
                    "line": 2,
                    "column": 27
                }
            ]
        }
    ]
}
```
<br />

### Query (non-implemented to Spanish):
```
{
    getTranslationRequest(langFrom: AFRIKAANS, langTo: SPANISH, text: "Hallo, my naam is Raúl en ek is 21 jaar oud")
}
```


```
{
    "data": {
        "getTranslationRequest": "Sorry, but we can't translate 'Hallo, my naam is Raúl en ek is 21 jaar oud' from AFRIKAANS to SPANISH"
    }
}
```

<br />

### Introspection query:
```
query LearnAboutSchema {
  __schema {
    queryType {
      fields {
          name
          description
          args {
            name
            description
            type {
                kind
            }
          }
      }
    }
  }
  __type(name: "Language") {
      name
      enumValues {
          name
      }
  }
}
```

```
{
    "data": {
        "__schema": {
            "queryType": {
                "fields": [
                    {
                        "name": "getTranslationRequest",
                        "description": "Translates a string from a given language into a different language.",
                        "args": [
                            {
                                "name": "langFrom",
                                "description": "The original language that `text` is provided in.",
                                "type": {
                                    "kind": "ENUM"
                                }
                            },
                            {
                                "name": "langTo",
                                "description": "The translated language to be returned.",
                                "type": {
                                    "kind": "ENUM"
                                }
                            },
                            {
                                "name": "text",
                                "description": "The text to be translated.",
                                "type": {
                                    "kind": "SCALAR"
                                }
                            }
                        ]
                    }
                ]
            }
        },
        "__type": {
            "name": "Language",
            "enumValues": [
                {
                    "name": "AFRIKAANS"
                },
                {
                    "name": "ALBANIAN"
                },
                {
                    "name": "AMHARIC"
                },
                {
                    "name": "ARABIC"
                },
                {
                    "name": "ARMENIAN"
                },
                {
                    "name": "AZERBAIJANI"
                },
                {
                    "name": "BASQUE"
                },
                {
                    "name": "BELARUSIAN"
                },
                {
                    "name": "BENGALI"
                },
                {
                    "name": "BIHARI"
                },
                {
                    "name": "BULGARIAN"
                },
                {
                    "name": "BURMESE"
                },
                {
                    "name": "CATALAN"
                },
                {
                    "name": "CHEROKEE"
                },
                {
                    "name": "CHINESE"
                },
                {
                    "name": "CHINESE_SIMPLIFIED"
                },
                {
                    "name": "CHINESE_TRADITIONAL"
                },
                {
                    "name": "CROATIAN"
                },
                {
                    "name": "CZECH"
                },
                {
                    "name": "DANISH"
                },
                {
                    "name": "DHIVEHI"
                },
                {
                    "name": "DUTCH"
                },
                {
                    "name": "ENGLISH"
                },
                {
                    "name": "ESPERANTO"
                },
                {
                    "name": "ESTONIAN"
                },
                {
                    "name": "FILIPINO"
                },
                {
                    "name": "FINNISH"
                },
                {
                    "name": "FRENCH"
                },
                {
                    "name": "GALICIAN"
                },
                {
                    "name": "GEORGIAN"
                },
                {
                    "name": "GERMAN"
                },
                {
                    "name": "GREEK"
                },
                {
                    "name": "GUARANI"
                },
                {
                    "name": "GUJARATI"
                },
                {
                    "name": "HEBREW"
                },
                {
                    "name": "HINDI"
                },
                {
                    "name": "HUNGARIAN"
                },
                {
                    "name": "ICELANDIC"
                },
                {
                    "name": "INDONESIAN"
                },
                {
                    "name": "INUKTITUT"
                },
                {
                    "name": "IRISH"
                },
                {
                    "name": "ITALIAN"
                },
                {
                    "name": "JAPANESE"
                },
                {
                    "name": "KANNADA"
                },
                {
                    "name": "KAZAKH"
                },
                {
                    "name": "KHMER"
                },
                {
                    "name": "KOREAN"
                },
                {
                    "name": "KURDISH"
                },
                {
                    "name": "KYRGYZ"
                },
                {
                    "name": "LAOTHIAN"
                },
                {
                    "name": "LATVIAN"
                },
                {
                    "name": "LITHUANIAN"
                },
                {
                    "name": "MACEDONIAN"
                },
                {
                    "name": "MALAY"
                },
                {
                    "name": "MALAYALAM"
                },
                {
                    "name": "MALTESE"
                },
                {
                    "name": "MARATHI"
                },
                {
                    "name": "MONGOLIAN"
                },
                {
                    "name": "NEPALI"
                },
                {
                    "name": "NORWEGIAN"
                },
                {
                    "name": "ORIYA"
                },
                {
                    "name": "PASHTO"
                },
                {
                    "name": "PERSIAN"
                },
                {
                    "name": "POLISH"
                },
                {
                    "name": "PORTUGUESE"
                },
                {
                    "name": "PUNJABI"
                },
                {
                    "name": "ROMANIAN"
                },
                {
                    "name": "RUSSIAN"
                },
                {
                    "name": "SANSKRIT"
                },
                {
                    "name": "SERBIAN"
                },
                {
                    "name": "SINDHI"
                },
                {
                    "name": "SINHALESE"
                },
                {
                    "name": "SLOVAK"
                },
                {
                    "name": "SLOVENIAN"
                },
                {
                    "name": "SPANISH"
                },
                {
                    "name": "SWAHILI"
                },
                {
                    "name": "SWEDISH"
                },
                {
                    "name": "TAJIK"
                },
                {
                    "name": "TAMIL"
                },
                {
                    "name": "TAGALOG"
                },
                {
                    "name": "TELUGU"
                },
                {
                    "name": "THAI"
                },
                {
                    "name": "TIBETAN"
                },
                {
                    "name": "TURKISH"
                },
                {
                    "name": "UKRANIAN"
                },
                {
                    "name": "URDU"
                },
                {
                    "name": "UZBEK"
                },
                {
                    "name": "UIGHUR"
                },
                {
                    "name": "VIETNAMESE"
                },
                {
                    "name": "WELSH"
                },
                {
                    "name": "YIDDISH"
                }
            ]
        }
    }
}
```

<br />


### Inspired by:

- https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/
- https://graphql.org/learn/introspection/
- https://spec.graphql.org/June2018/
