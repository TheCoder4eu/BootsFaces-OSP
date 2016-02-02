package net.bootsfaces.component.dataTable;

import net.bootsfaces.component.dataTable.DataTable.DataTablePropertyType;
import org.junit.Test;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;
import static net.bootsfaces.component.dataTable.DataTable.DataTablePropertyType.currentPage;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by XaeroDegreaz on 1/29/2016.
 */
public class DataTableTest
{
    @Test
    public void testDecode() throws Exception
    {
        DataTable dataTable = mock( DataTable.class );
        when( dataTable.getDataTableProperties() ).thenReturn( new HashMap<DataTablePropertyType, Object>() );
        Map<String, String> parameterMap = new HashMap<String, String>();
        FacesContext facesContext = mock( FacesContext.class );
        ExternalContext externalContext = mock( ExternalContext.class );
        when( facesContext.getExternalContext() ).thenReturn( externalContext );
        when( externalContext.getRequestParameterMap() ).thenReturn( parameterMap );
        parameterMap.put( "params", "BsFEvent=currentPage:2" );
        doCallRealMethod().when( dataTable ).decode( facesContext );
        dataTable.decode( facesContext );
        assertEquals( 2, dataTable.getDataTableProperties().get( currentPage ) );
    }
}