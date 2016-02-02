package net.bootsfaces.component.dataTable;

import net.bootsfaces.component.dataTable.DataTable.DataTablePropertyType;
import org.junit.Test;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;
import static net.bootsfaces.component.dataTable.DataTable.DataTablePropertyType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by XaeroDegreaz on 1/29/2016.
 */
public class DataTableTest
{
    @Test
    public void testDecodeCurrentPage() throws Exception
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

    @Test
    public void testDecodePageLength() throws Exception
    {
        DataTable dataTable = mock( DataTable.class );
        HashMap<DataTablePropertyType, Object> dataTableProperties = new HashMap<DataTablePropertyType, Object>();
        dataTableProperties.put( currentPage, 5 );
        when( dataTable.getDataTableProperties() ).thenReturn( dataTableProperties );
        Map<String, String> parameterMap = new HashMap<String, String>();
        FacesContext facesContext = mock( FacesContext.class );
        ExternalContext externalContext = mock( ExternalContext.class );
        when( facesContext.getExternalContext() ).thenReturn( externalContext );
        when( externalContext.getRequestParameterMap() ).thenReturn( parameterMap );
        parameterMap.put( "params", "BsFEvent=pageLength:100" );
        doCallRealMethod().when( dataTable ).decode( facesContext );
        dataTable.decode( facesContext );
        assertEquals( 100, dataTable.getDataTableProperties().get( pageLength ) );
        assertEquals( 0, dataTable.getDataTableProperties().get( currentPage ) );
    }

    @Test
    public void testDecodeSearchTerm() throws Exception
    {
        DataTable dataTable = mock( DataTable.class );
        HashMap<DataTablePropertyType, Object> dataTableProperties = new HashMap<DataTablePropertyType, Object>();
        dataTableProperties.put( currentPage, 5 );
        when( dataTable.getDataTableProperties() ).thenReturn( dataTableProperties );
        Map<String, String> parameterMap = new HashMap<String, String>();
        FacesContext facesContext = mock( FacesContext.class );
        ExternalContext externalContext = mock( ExternalContext.class );
        when( facesContext.getExternalContext() ).thenReturn( externalContext );
        when( externalContext.getRequestParameterMap() ).thenReturn( parameterMap );
        parameterMap.put( "params", "BsFEvent=searchTerm:SomeSearchTerm" );
        doCallRealMethod().when( dataTable ).decode( facesContext );
        dataTable.decode( facesContext );
        assertEquals( "SomeSearchTerm", dataTable.getDataTableProperties().get( searchTerm ) );
        assertEquals( 0, dataTable.getDataTableProperties().get( currentPage ) );
    }

    @Test
    public void testDecodeSearchTerm2() throws Exception
    {
        DataTable dataTable = mock( DataTable.class );
        HashMap<DataTablePropertyType, Object> dataTableProperties = new HashMap<DataTablePropertyType, Object>();
        dataTableProperties.put( currentPage, 5 );
        when( dataTable.getDataTableProperties() ).thenReturn( dataTableProperties );
        Map<String, String> parameterMap = new HashMap<String, String>();
        FacesContext facesContext = mock( FacesContext.class );
        ExternalContext externalContext = mock( ExternalContext.class );
        when( facesContext.getExternalContext() ).thenReturn( externalContext );
        when( externalContext.getRequestParameterMap() ).thenReturn( parameterMap );
        parameterMap.put( "params", "BsFEvent=searchTerm:Some:Search: Term" );
        doCallRealMethod().when( dataTable ).decode( facesContext );
        dataTable.decode( facesContext );
        assertEquals( "Some:Search: Term", dataTable.getDataTableProperties().get( searchTerm ) );
        assertEquals( 0, dataTable.getDataTableProperties().get( currentPage ) );
    }
}