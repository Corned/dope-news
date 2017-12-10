Feature: Käyttäjä voi luoda uutisen
    Scenario: Käyttäjä haluaa luoda uutisen
        Given käyttäjä on uutisenluonti sivulla
        When käyttäjä täyttää kentät valideilla syötteillä
        And käyttäjä painaa "submit!"
        Then käyttäjä ohjataan sivulle jossa näkyy syötetyt tiedot


Feature: Käyttäjä voi lukea uutisen
    Scenario: Käyttäjä haluaa lukea uutisen
        Given uutisia on olemassa
        When käyttäjä menee "ajankohtaista" sivulle
        Then käyttäjä näkee listan uutisia