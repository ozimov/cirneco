package it.ozimov.cirneco.hamcrest.filetype;

import com.google.common.io.Files;
import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static java.lang.String.format;

public class IsYamlTest extends BaseMatcherTest {

    private static final String VALID_YAML = "this:\n" +
            "  is:\n" +
            "    a:\n" +
            "      valid: yaml";

    private static final String NON_VALID_YAML = "this.is.not.a.valid=yaml";

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    public File validFile;
    public File nonValidFile;

    public Matcher<Object> isYamlMatcher;

    @Before
    public void setUp() throws Exception {
        // Arrange
        isYamlMatcher = IsYaml.yaml();

        validFile = testFolder.newFile("valid_file.yml");
        Files.write(VALID_YAML.getBytes(), validFile);

        nonValidFile = testFolder.newFile("non_valid_file.yml");
        Files.write(NON_VALID_YAML.getBytes(), nonValidFile);
    }

    @Test
    public void testGivenValidYamlStringWhenMatchesIsCalledThenReturnTrue() throws Exception {
        // Act
        final boolean matches = isYamlMatcher.matches(VALID_YAML);

        // Arrange
        assertMatches("Not given a valid yaml", matches);
    }

    @Test
    public void testGivenNonValidYamlStringWhenMatchesIsCalledThenReturnFalse() throws Exception {
        // Act
        final boolean matches = isYamlMatcher.matches(NON_VALID_YAML);

        // Arrange
        assertDoesNotMatch("Given a valid yaml", matches);
    }

    @Test
    public void testGivenValidYamlFileWhenMatchesIsCalledThenReturnTrue() throws Exception {
        // Act
        final boolean matches = isYamlMatcher.matches(validFile);

        // Arrange
        assertMatches("Not given a valid yaml file", matches);
    }

    @Test
    public void testGivenNonValidYamlFileWhenMatchesIsCalledThenReturnFalse() throws Exception {
        // Act
        final boolean matches = isYamlMatcher.matches(nonValidFile);

        // Arrange
        assertDoesNotMatch("Given a valid yaml file", matches);
    }

    @Test
    public void testGivenValidYamlInputStreamWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Arrange
        final InputStream inputStream = new FileInputStream(validFile);

        // Act
        final boolean matches = isYamlMatcher.matches(inputStream);

        // Arrange
        assertMatches("Not given an input stream with valid yaml content", matches);
    }

    @Test
    public void testGivenNonValidYamlInputStreamWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Arrange
        final InputStream inputStream = new FileInputStream(nonValidFile);

        // Act
        final boolean matches = isYamlMatcher.matches(inputStream);

        // Arrange
        assertDoesNotMatch("Given an input stream with valid yaml content", matches);
    }

    @Test
    public void testGivenValidYamlToStringObjectWhenMatchesIsCalledThenReturnTrue() throws Exception {
        //Arrange
        final YamlContainer yamlContainer = new YamlContainer(VALID_YAML);

        // Act
        final boolean matches = isYamlMatcher.matches(yamlContainer);

        // Arrange
        assertMatches("Not given an input stream with valid yaml content", matches);
    }

    @Test
    public void testGivenNonValidYamlToStringObjectWhenMatchesIsCalledThenReturnFalse() throws Exception {
        //Arrange
        final YamlContainer yamlContainer = new YamlContainer(NON_VALID_YAML);

        // Act
        final boolean matches = isYamlMatcher.matches(yamlContainer);

        // Arrange
        assertDoesNotMatch("Given an input stream with valid yaml content", matches);
    }

    @Test
    public void testDescribeMismatchSafely() throws Exception {
        assertHasMismatchDescription(format("<%s> is not a valid yaml", NON_VALID_YAML), isYamlMatcher, NON_VALID_YAML);
    }

    @Test
    public void testDescribeTo() throws Exception {
        assertIsDescribedTo("a value equals to a valid yaml", isYamlMatcher);

    }

    @AllArgsConstructor
    private static class YamlContainer {
        private final String yaml;


        @Override
        public String toString() {
            return yaml;
        }
    }

}
