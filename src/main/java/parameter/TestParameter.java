package parameter;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameter {
	private ParseProperties pp=null;
	@Parameters({"TestData"})
	@Test
	public void test(@Optional("aaa") String testdata){
		String projectpath=System.getProperty("user.dir");
		System.out.println("path="+projectpath+testdata);
		pp=new ParseProperties(projectpath+testdata);
		System.out.println("====="+pp.getValue("url"));
	}
}
