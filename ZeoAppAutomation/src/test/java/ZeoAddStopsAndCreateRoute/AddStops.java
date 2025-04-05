package ZeoAddStopsAndCreateRoute;

import org.testng.annotations.Test;

import ConfigFiles.BaseClass;
import POMClasses.AddStopsAndCreateRoute;

public class AddStops extends BaseClass{
	
//	@Test(priority=1)
//	public void searchAddress() throws Exception
//	{
//	   //AddStopsAndCreateRoute class
//		AddStopsAndCreateRoute ac=new AddStopsAndCreateRoute(driver);
//		
//	   //add stops by address
//		ac.addStopsSearch(driver, AddStopsSheet,"Add stop by Address");
//	}
	
	@Test()
	public void importStops() throws Exception
	{
	   //AddStopsAndCreateRoute class
		AddStopsAndCreateRoute ac=new AddStopsAndCreateRoute(driver);
		
	   //add stops by address
		ac.addStopsExcel(driver);
	}
	
	

}
