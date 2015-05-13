/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component;

import net.bootsfaces.render.Paginable;

/**
 * This class Extends UIData to support Paginator component
 * @author thecoder4.eu
 */
public class PaginableData extends javax.faces.component.UIData implements Paginable {

    public int getCurrentPage() {
        int rows = this.getRowsPerPage();
        
        if(rows > 0) {
            int first = this.getFirst();
         
            return (int) (first / rows)+1;
        } else {
            return 1;
        }
    }
    
    public int getPages() {
        return (int) Math.ceil(this.getRowCount() * 1d / this.getRowsPerPage());
    }
    
    public int getRowsPerPage() {
        int rows = this.getRows();
        return rows == 0 ? this.getRowCount() : rows;
    }
    
}
