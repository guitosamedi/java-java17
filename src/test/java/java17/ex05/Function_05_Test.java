package java17.ex05;
import java17.data.Data;
import java17.data.Person;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.function.Consumer;

/**
 * Exercice 5 - java.util.function.Consumer
 */
public class Function_05_Test {

    //tag::functions[]
    // TODO compléter la fonction
    // TODO modifier le mot de passe en "secret"

    /* Faux !!!
    Consumer<Person> changePasswordToSecret = person -> {
        String secretPassword = DigestUtils.sha256Hex(person.getPassword());
        person.setPassword(secretPassword);
    };*/
    // CORRECTION
    Consumer<Person> changePasswordToSecret = (p) -> p.setPassword("secret");

    // TODO compléter la fonction
    // TODO vérifier que l'age > 4 avec une assertion JUnit
    /* Consumer<Person> verifyAge = person -> {
        Assertions.assertTrue(person.getAge() > 4);
    };*/
    //CORRECTION
    Consumer<Person> verifyAge = null; //(p) -> AssertTrue(p.getAge() > 4);


    // TODO compléter la fonction
    // TODO vérifier que le mot de passe est "secret" avec une assertion JUnit
    /*
    Consumer<Person> verifyPassword = person -> {
        String expectedHash = DigestUtils.sha512Hex("secret");
        String password = person.getPassword();

        String hashedPassword = DigestUtils.sha512Hex(password);

        Assertions.assertEquals(expectedHash, hashedPassword, "Le mot de passe n'est pas correct");
    };*/
    //end::functions[]

    // CORRECTION
    Consumer<Person> verifyPassword =  (p) -> Assert.assertEquals("secret", p.getPassword());

    @Test
    public void test_consumer() throws Exception {
        List<Person> personList = Data.buildPersonList();

        // TODO invoquer la méthode personList.forEach pour modifier les mots de passe en "secret"
        personList.forEach(changePasswordToSecret);
        //personList.forEach(person -> person.changePasswordToSecret("secret"));

        // TODO remplacer la boucle for par l'invocation de la méthode forEach
        // TODO Utiliser la méthode andThen pour chaîner les vérifications verifyAge et verifyPassword
        personList.forEach(verifyAge.andThen(verifyPassword));
        for(Person p : personList) {
            verifyAge.accept(p);
            verifyPassword.accept(p);
        }
    }
}
