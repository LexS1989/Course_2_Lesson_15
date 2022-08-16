package myArrayList.server;

import myArrayList.exception.ValueNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerListImplTest {

    private final IntegerList out = new IntegerListImpl();

    @Test
    public void addForItemPositiveTest() {
        IntegerList expected = new IntegerListImpl();
        expected.add(1);
        out.add(1);
        assertEquals(expected, out);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addWithArrayExpansionPositiveTest(IntegerList out) {
        out.add(100);
        int expected = 11;
        assertThat(out.size())
                .isEqualTo(expected);
    }

    @Test
    public void addNullForItemNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.add(null));
    }

    @Test
    public void addByIndexAndItemAndWithArrayExpansionPositiveTest() {
        IntegerList expected = new IntegerListImpl(3);
        expected.add(101);
        expected.add(103);
        expected.add(102);
        IntegerList out = new IntegerListImpl(2);
        out.add(0, 101);
        out.add(1, 102);
        out.add(1, 103);
        assertEquals(expected, out);
    }

    @Test
    public void addItemNullAndIndexMoreCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.add(0, null));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.add(1, 101));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.add(-1, 101));
    }

    @Test
    public void setPositiveTest() {
        IntegerList expected = new IntegerListImpl();
        expected.add(0, 101);
        expected.add(1, 103);
        IntegerList out = new IntegerListImpl();
        out.add(0, 101);
        out.add(1, 102);
        out.set(1, 103);
        assertEquals(expected, out);
    }

    @Test
    public void setItemNullAndIndexMoreCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.set(0, null));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.set(0, 101));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.set(-1, 101));
    }

    @Test
    public void removeByItemPositiveTest() {
        IntegerList expected = new IntegerListImpl(2);
        expected.add(101);
        expected.add(103);
        IntegerList out = new IntegerListImpl(3);
        out.add(101);
        out.add(102);
        out.add(103);
        Integer testRemove = 102;
        out.remove(testRemove);
        assertEquals(expected, out);
    }

    @Test
    public void removeByItemNullAndNotFoundValueNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.remove(null));

        Integer testRemove = 123;
        assertThatExceptionOfType(ValueNotFoundException.class)
                .isThrownBy(() -> out.remove(testRemove));
    }

    @Test
    public void removeByIndexPositiveTest() {
        IntegerList expected = new IntegerListImpl(2);
        expected.add(0, 101);
        expected.add(1, 103);
        IntegerList out = new IntegerListImpl(3);
        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        out.remove(1);
        assertEquals(expected, out);
    }

    @Test
    public void removeForIndexMoreOrEqualsCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.remove(0));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.remove(-1));
    }

    @Test
    public void containsTest() {
        boolean expected = true;
        boolean expected1 = false;
        out.add(101);
        out.add(103);
        out.add(102);
        out.add(106);
        out.add(105);
        out.add(107);
        out.add(108);
        assertThat(out.contains(106))
                .isEqualTo(expected);

        assertThat(out.contains(123))
                .isEqualTo(expected1);
    }

    @Test
    public void containsItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.contains(null));
    }

    @Test
    public void indexOfPositiveTest() {
        int expected = 1;
        int expected1 = -1;
        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        assertThat(out.indexOf(102))
                .isEqualTo(expected);

        assertThat(out.indexOf(123))
                .isEqualTo(expected1);
    }

    @Test
    public void indexOfItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.indexOf(null));
    }

    @Test
    public void lastIndexOfPositiveTest() {
        int expected = 0;
        int expected1 = -1;
        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        assertThat(out.lastIndexOf(101))
                .isEqualTo(expected);

        assertThat(out.lastIndexOf(123))
                .isEqualTo(expected1);
    }

    @Test
    public void lastIndexOfItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.lastIndexOf(null));
    }

    @Test
    public void getIndexPositiveTest() {
        Integer expected = 103;
        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        assertThat(out.get(2))
                .isEqualTo(expected);
    }

    @Test
    public void getForIndexMoreOrEqualsCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.get(0));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.get(-1));
    }

    @Test
    public void equalsReturnTruePositiveTest() {
        IntegerList exp = new IntegerListImpl(2);
        exp.add(0, 101);
        exp.add(1, 102);
        boolean expected = true;
        out.add(0, 101);
        out.add(1, 102);
        assertThat(out.equals(exp))
                .isEqualTo(expected);
    }

    @Test
    public void equalsReturnFalsePositiveTest() {
        IntegerList exp = new IntegerListImpl(2);
        exp.add(0, 101);
        exp.add(1, 102);
        exp.add(2, 103);
        boolean expected = false;
        out.add(0, 101);
        out.add(1, 102);
        assertThat(out.equals(exp))
                .isEqualTo(expected);

        out.add(2, 105);
        assertThat(out.equals(exp))
                .isEqualTo(expected);
    }

    @Test
    public void equalsNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.equals(null));
    }

    @Test
    public void sizeTest() {
        int expected = 3;

        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        assertThat(out.size())
                .isEqualTo(expected);
    }

    @Test
    public void isEmptyTruePositiveTest() {
        boolean expected = true;
        assertThat(out.isEmpty())
                .isEqualTo(expected);
    }

    @Test
    public void isEmptyFalsePositiveTest() {
        boolean expected = false;
        out.add(101);
        assertThat(out.isEmpty())
                .isEqualTo(expected);
    }

    @Test
    public void clearPositiveTest() {
        Integer expected = null;
        out.add(101);
        out.add(102);
        out.add(103);
        assertThat(out.clear())
                .isEqualTo(expected);
    }

    @Test
    public void toArrayPositiveTest() {
        Integer[] expected = {101, 102, 103};
        out.add(0, 101);
        out.add(1, 102);
        out.add(2, 103);
        Integer[] array = out.toArray();
        assertEquals(Arrays.toString(expected), Arrays.toString(array));
    }

    public static Stream<Arguments> params() {
        IntegerList arrayParams = new IntegerListImpl();
        arrayParams.add(1);
        arrayParams.add(2);
        arrayParams.add(3);
        arrayParams.add(4);
        arrayParams.add(5);
        arrayParams.add(6);
        arrayParams.add(7);
        arrayParams.add(8);
        arrayParams.add(9);
        arrayParams.add(10);
     
        return Stream.of(
                Arguments.of(arrayParams
                )
        );
    }
}