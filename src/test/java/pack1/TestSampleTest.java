package pack1;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;



public class TestSampleTest {

	@Test
	public static void dain() throws IOException {
		// TODO Auto-generated method stub

		DataDrivenTest d= new DataDrivenTest();
		
		
		
		ArrayList<String> data = d.getData("Login");
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
	}
}
