package edu.umass.cs.rfbi.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Kaituo
 */
public class HashSetUtil {
    /**
     * Returns the difference of two sets. The returned set contains all elements that are contained by {@code set1} and
     * not contained by {@code set2}.
     * @param set1
     * @param set2
     * @return
     */
    public Set<String> difference(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();

        for(String a: set1) {
            if(!set2.contains(a)) {
                res.add(a);
            }
        }

        return res;
    }

    public void printAndGetSize(Set<String> union) {
        Iterator<String> iterator = union.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        // Get size of a collection
        int size = union.size();
        if (union.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("size: " + size);
        }
        System.out.println();
    }

}
