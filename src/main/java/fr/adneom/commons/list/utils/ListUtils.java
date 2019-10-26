package fr.adneom.commons.list.utils;

import java.util.AbstractList;
import java.util.List;

/**
 * An utility class which contains static utility methods pertaining to {@link List} instances.
 *
 * @author Bilel JALLOULI
 */
public final class ListUtils {

    private ListUtils() {
    }

    /**
     * split a list into sublists of the same partition size (the final list may be smaller).
     *
     * @param list the list to return consecutive sublists of
     * @param partitionSize the desired partition size of each sublist
     * @return a list of consecutive sublists, all in the original order
     * @throws IllegalArgumentException if {@code list} is null or {@code partitionSize} is not greater than zero
     */
    public static <T> List<List<T>> partition(final List<T> list, final int partitionSize) {
        if (list == null) {
            throw new IllegalArgumentException("ListUtils.partition.list.couldNotBeNull");
        }
        if (partitionSize <= 0) {
            throw new IllegalArgumentException("ListUtils.partition.partitionSize.invalid");
        }
        return new Partition(list, partitionSize);
    }

    /**
     * Partition class modify the behavior of the original list, by extending from AbstractList<List<T>>
     * and overriding get(int index) and size() methods.
     * So instead of returning single elements from the original list, it returns sublists of a given size.
     */
    private static class Partition<T> extends AbstractList<List<T>> {
        final List<T> list;
        final int size;

        Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        //return a sublist from the original list instead of returning a single element
        @Override
        public List<T> get(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            int start = index * this.size;
            int end = Math.min(start + this.size, this.list.size());
            return this.list.subList(start, end);
        }

        //calculate the number of sublists of a given size
        //instead of calculating the number of elements in the original list
        @Override
        public int size() {
            return (int) Math.ceil((double) list.size() / (double) this.size);
        }
    }

}
