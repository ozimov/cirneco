package it.ozimov.cirneco.hamcrest.java7.base;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static it.ozimov.cirneco.hamcrest.java7.base.HasToStringContaining.hasToStringContainingInOrder;
import static it.ozimov.cirneco.hamcrest.java7.clazz.IsValidNoArgumentConstructor.hasNoArgumentConstructor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class HasToStringContainingTest {

    @Test
    public void testConstructor() throws Exception {
        // Arrange
        assertThat(HasToStringContaining.class, hasNoArgumentConstructor());
    }

    @Test
    public void testHasToStringContainingInOrderSuccessOnCorrectOrder() throws Exception {
        //Arrange
        final UUID id = UUID.randomUUID();
        final String field = "A value";
        final Date date = new Date();

        //Act
        final AClass aClass = AClass.builder().id(id).field(field).date(date).build();

        //Assert
        assertThat(aClass, hasToStringContainingInOrder(id, field, date));
    }

    @Test
    public void testHasToStringContainingInOrderFailsOnWrongOrder() throws Exception {
        //Arrange
        final UUID id = UUID.randomUUID();
        final String field = "A value";
        final Date date = new Date();

        //Act
        final AClass aClass = AClass.builder().id(id).field(field).date(date).build();

        //Assert
        assertThat(aClass, not(hasToStringContainingInOrder(field, id, date)));
    }

    @Builder
    static class AClass {

        UUID id;
        String field;
        Date date;

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("field", field)
                    .add("date", date)
                    .toString();
        }
    }

}