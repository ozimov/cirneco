package it.ozimov.cirneco.hamcrest.java7.filetype;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Is the value of {@linkplain T} a YAML text?
 * <p>
 * Checks if the given object has a valid Yaml representation. The matcher
 * works for {@linkplain String}, {@linkplain File} and {@linkplain InputStream} objects.
 * <p>
 * Any other object will be transformed to a {@code String} using the {@linkplain String#toString()}
 * method.
 *
 * @since version 0.5.0 for JDK7
 */
public class IsYaml<T> extends TypeSafeMatcher<T> {

    private static final Logger LOG = getLogger(IsYaml.class);

    /**
     * Creates a matcher for {@code T}s that matches when the {@code toString()} method of the given object returns a
     * valid yaml address.
     * <p>
     * <p>For example:
     * <p>
     * <pre>assertThat("this.is.valid.yaml: YES", yaml())</pre>
     */
    public static <T> Matcher<T> yaml() {
        return new IsYaml<>();
    }

    @Override
    protected boolean matchesSafely(final T object) {
        final Yaml yaml = new Yaml();
        try {
            final Map<String, Map<String, String>> values;
            if (object instanceof File) {
                final File file = (File) object;
                values = (Map<String, Map<String, String>>) yaml.load(new FileInputStream(file));
            } else if (object instanceof InputStream) {
                final InputStream inputStream = (InputStream) object;
                values = (Map<String, Map<String, String>>) yaml.load(inputStream);
            } else if (object instanceof String) {
                final String string = (String) object;
                values = (Map<String, Map<String, String>>) yaml.load(string);
            } else {
                LOG.info("The given object is not an instance of a String, File or " +
                        "InputStream. Attempting to use the 'toString()' value.");
                final String string = object.toString();
                values = (Map<String, Map<String, String>>) yaml.load(string);
            }
            return values != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
        mismatchDescription.appendText(String.format("<%s>", item.toString())).appendText(
                " is not a valid yaml");
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("a value equals to a valid yaml");
    }

}
