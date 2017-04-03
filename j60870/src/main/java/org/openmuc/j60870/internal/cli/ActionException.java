/*
 * Copyright 2011-16 Fraunhofer ISE
 *
 * This file is part of OpenMUC.
 * For more information visit http://www.openmuc.org
 *
 * OpenMUC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenMUC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenMUC.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.openmuc.j60870.internal.cli;

public final class ActionException extends Exception {

    private static final long serialVersionUID = 4806947065917148946L;

    public ActionException() {
        super();
    }

    public ActionException(String s) {
        super(s);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }

    public ActionException(String s, Throwable cause) {
        super(s, cause);
    }

}
