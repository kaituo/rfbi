package edu.umass.cs.rfbi.util;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Kaituo
 */
public class HashSetUtil {

    public Set<String> difference(Set<String> ha, Set<String> hb) {
        Iterator<String> ita = ha.iterator();
        Iterator<String> itb;

        while (ita.hasNext()) {
            String val = ita.next();
            itb = hb.iterator();
            while (itb.hasNext()) {
                if (val.equals(itb.next())) {
                    ita.remove();
                    break;
                }
            }

        }
        return ha;
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
