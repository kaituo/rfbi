/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2003-2008 University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package tmp;

import java.lang.reflect.Method;

import edu.umass.cs.rfbi.util.Serializer;

/**
 * @author kaituo
 */
public class Test {
    static void testCase1() {
        Serializer s = new Serializer();
        System.out.println(s.getClass().getName());
        System.out.println(s.getClass().getSimpleName());
        System.out.println(System.getProperty("foo"));
        A b = (new B());
        System.out.println(b.hashCode());
        try {
            Class<?> c = Class.forName("tmp.A");
            Class[] argTypes = new Class[] { };
            Method hash = c.getDeclaredMethod("foo", argTypes);
            Object[] args1 = new Object[]{};
            System.out.println((hash.invoke(b, args1)));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Class<?> res = null;
        try {
            res = Class.forName("org.apache.bcel.util.SyntheticRepository", false, Thread.currentThread().getContextClassLoader());
        }
        catch (final ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

}

abstract class A {
    int foo() {
        return 13;
    }

}

class B extends A {

}
