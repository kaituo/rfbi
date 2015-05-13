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

package edu.umass.cs.rfbi.xml;

/**
 * @author Christoph Reichenbach
 */

import edu.umd.cs.findbugs.SortedBugCollection;
import edu.umd.cs.findbugs.BugInstance;
import java.util.Iterator;


public abstract class BugFilterIterator implements Iterator<BugInstance>
{
	private Iterator <BugInstance> body;
	private BugInstance next_bug;

	public BugFilterIterator(Iterator <BugInstance> body) {
		this.body = body;
		this.next_bug = null;
		prepareNext();
	};

	private void
	prepareNext()
	{
		do {
			if (!body.hasNext()) {
				this.next_bug = null;
				return;
			}
			this.next_bug = body.next();
		} while (!this.test(this.next_bug));
	}

	/**
	 * Check whether the given bug instance is viable
	 */
	protected abstract boolean test(BugInstance instance);

	public void remove()
	{
		throw new UnsupportedOperationException();
	}

	public boolean
	hasNext()
	{
		return this.next_bug != null;
	}

	public BugInstance
	next()
	{
		final BugInstance bi = this.next_bug;
		prepareNext();
		return bi;
	}

	public static Iterable<BugInstance>
	byType(final SortedBugCollection collection, final String type)
	{
		return new Iterable() {
			public BugFilterIterator
			iterator()
			{
				return new BugFilterIterator(collection.getCollection().iterator()) {
					protected boolean
					test(BugInstance instance)
					{
						return instance.getType().startsWith(type);
					}
				};
			}
		};
	}
}

