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

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * @author kaituo
 */
public class RelativeReference {
    public static void main(String[] args) {
        Cd bj = new Cd("basement_jaxx_singles");

        List order = new ArrayList();
        // adds the same cd twice (two references to the same object)
        order.add(bj);
        order.add(bj);

        // adds itself (cycle)
        order.add(order);

        XStream xstream = new XStream();
        xstream.alias("cd", Cd.class);
        System.out.println(xstream.toXML(order));
    }

}
