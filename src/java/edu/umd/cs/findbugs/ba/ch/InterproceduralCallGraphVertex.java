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

package edu.umd.cs.findbugs.ba.ch;

import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.graph.AbstractVertex;

/**
 * Class representing a vertex in the interprocedural call graph; i.e., a
 * method.
 *
 * @author David Hovemeyer
 */
public class InterproceduralCallGraphVertex extends AbstractVertex<InterproceduralCallGraphEdge, InterproceduralCallGraphVertex> {
    private XMethod xmethod;

    /**
     * @param xmethod
     *            The xmethod to set.
     */
    public void setXmethod(XMethod xmethod) {
        this.xmethod = xmethod;
    }

    /**
     * @return Returns the xmethod.
     */
    public XMethod getXmethod() {
        return xmethod;
    }

    /**
     * hashCode method used in rfbi's ApplicationCallGraph
     * @author kaituo
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((xmethod == null) ? 0 : xmethod.getMethodDescriptor().hashCode());
        return result;
    }

    /**
     * equals method used in rfbi's ApplicationCallGraph
     * @author kaituo
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InterproceduralCallGraphVertex other = (InterproceduralCallGraphVertex) obj;
        if (xmethod == null) {
            if (other.xmethod != null) {
                return false;
            }
        } else if (!xmethod.getMethodDescriptor().equals(other.xmethod.getMethodDescriptor())) {
            return false;
        }

        return true;
    }


}
