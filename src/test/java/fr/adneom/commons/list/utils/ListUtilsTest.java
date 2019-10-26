package fr.adneom.commons.list.utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ListUtilsTest {

    @Test
    public void partition_shouldSucceed() {
        final List<Integer> initialList = Arrays.asList(1, 2, 3, 4, 5);

        List<List<Integer>> partitionedList = ListUtils.partition(initialList, 2);
        Assertions.assertThat(partitionedList.size()).isEqualTo(3);

        List<Integer> firstSubList = partitionedList.get(0);
        Assertions.assertThat(firstSubList.size()).isEqualTo(2);
        Assertions.assertThat(firstSubList.get(0)).isEqualTo(1);
        Assertions.assertThat(firstSubList.get(1)).isEqualTo(2);

        List<Integer> secondSubList = partitionedList.get(1);
        Assertions.assertThat(secondSubList.size()).isEqualTo(2);
        Assertions.assertThat(secondSubList.get(0)).isEqualTo(3);
        Assertions.assertThat(secondSubList.get(1)).isEqualTo(4);

        List<Integer> thirdSubList = partitionedList.get(2);
        Assertions.assertThat(thirdSubList.size()).isEqualTo(1);
        Assertions.assertThat(thirdSubList.get(0)).isEqualTo(5);
    }

    @Test
    public void partition_shouldHandleGenericType() {
        final List<String> initialListOfString = Arrays.asList("a", "b", "c", "d", "e", "f");
        List<List<String>> partitionedListOfString = ListUtils.partition(initialListOfString, 3);
        Assertions.assertThat(partitionedListOfString.size()).isEqualTo(2);

        final List<LocalDate> initialListOfDate = Arrays.asList(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 1, 3),
                LocalDate.of(2020, 1, 4));
        List<List<LocalDate>> partitionedListOfDate = ListUtils.partition(initialListOfDate, 1);
        Assertions.assertThat(partitionedListOfDate.size()).isEqualTo(4);

        final List<Object> initialListOfObject = Arrays.asList(1, "a", 2, LocalDate.of(2020, 1, 1));
        List<List<Object>> partitionedListOfObject = ListUtils.partition(initialListOfObject, 4);
        Assertions.assertThat(partitionedListOfObject.size()).isEqualTo(1);
    }

    @Test
    public void partition_shouldReturnEmptyList() {
        final List<Integer> initialList = Collections.emptyList();
        List<List<Integer>> partitionedList = ListUtils.partition(initialList, 2);
        Assertions.assertThat(partitionedList.isEmpty()).isTrue();
    }

    @Test
    public void partition_shouldThrowIllegalArgumentException() {
        assertThatThrownBy(() -> ListUtils.partition(null, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ListUtils.partition.list.couldNotBeNull");

        assertThatThrownBy(() -> ListUtils.partition(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ListUtils.partition.partitionSize.invalid");
    }

}