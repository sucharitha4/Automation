package firstCry;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;


import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


//import com.gargoylesoftware.htmlunit.javascript.host.Set;

//import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

public class ExampleProgram {
	
	static WebDriver driver;

	
	public static void main(String[] args) throws InterruptedException {
		int count=0;
		
		/**
		 * use system property
		 */
		System.setProperty("webdriver.chrome.driver", "C:/Users/yashwanth.reddy/Downloads/chromedriver_win32/chromedriver.exe" );
		driver = new ChromeDriver();
		
		driver.get("http://www.firstcry.com/"); //Enter into Website
		driver.manage().window().maximize(); //Maximizing the window
		
		/**
		 * 
		 * Add explicit wait here, dont use sleep, applies for all sleep
		 */
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath("//*[@id=\"amt\"]/div[2]/div[1]/div[1]/div[3]/div")).click(); //Closing the pop-up
		driver.findElement(By.xpath("//div[@class='_pop_close _pop_reg_bg']")).click();
		Thread.sleep(2000); //Synchronization 
		driver.findElement(By.id("search_box")).click(); 
		driver.findElement(By.id("search_box")).sendKeys("Footwear"); //Entering text on search box
		Thread.sleep(2000);
		List<WebElement> list= driver.findElements(By.id("searchlist"));
		System.out.println("following are some footwears:");
		
		for(WebElement we:list)
		{
			System.out.println(we.getText());
			Thread.sleep(2000);
		}
        
		driver.findElement(By.xpath("//a[@href='//www.firstcry.com/contactus']")).click();
		String MainWindow = driver.getWindowHandle();
		
		/**
		 * import and use Set
		 */
		Set<String> s1 = driver.getWindowHandles();
		
		
        Iterator<String> itr= s1.iterator();
        while(itr.hasNext())
        {
        String childWindow=itr.next();
           // Compare whether the main windows is not equal to child window. If not equal, we will close.
        if(!MainWindow.equals(childWindow)){
        driver.switchTo().window(childWindow);
        Thread.sleep(2000);
        System.out.println("Title of the Child page:: " +driver.switchTo().window(childWindow).getTitle());
      //  System.out.println(driver.switchTo().window(childWindow).getTitle());
        String NextWindow = driver.getTitle();
      
        String Expected = "FirstCry.com: Contact Us";
     
        /**
         * Use assertions instead of syso
         */
        if(NextWindow.equalsIgnoreCase(Expected))
        {
            System.out.println("Same title");
        }
        else 
        {
            System.out.println("Error");
        }
        driver.close();
        }
        }
        // This is to switch to the main window
        driver.switchTo().window(MainWindow);
        
        Actions action=new Actions(driver);
        
        /**
         * provide meaningful namings
         */
        WebElement SomeBooks = driver.findElement(By.xpath("//a[@href='//www.firstcry.com/toys-learning-entertainment?ref2=menu_dd_catlanding']"));
        
        action.moveToElement(SomeBooks).perform();
        
        Thread.sleep(3000);
        /**
         * clean up unwanted code
         */
       // List<WebElement> li= driver.findElements(By.xpath("//li[@id='submenu1-2']//a[@title='All'][contains(text(),'All')]"));
       // List<WebElement> li= driver.findElements(By.xpath("//div[@class='options']//a"));
        List<WebElement> li= driver.findElements(By.xpath("//div[contains(@class,'option-container submenu1-4')]//a"));
      // List<WebElement> li= driver.findElements(By.xpath("html/body/div[5]/div[3]/div/div/div[2]/div[4]/div/div[1]/ul/li/a"));
		System.out.println("following are some Toys, Books and Schools:");
		for(WebElement w:li)
		{
			System.out.println(w.getText());
			count++;
		}
		System.out.println(count);
		
		for(WebElement check:li)
		{
			/**
			 * Assertions
			 */
			if(check.getText().equalsIgnoreCase("Kid Puzzle"));
			{
				System.out.println("Following text present in the list");
				//driver.findElement(By.xpath("//a[contains(text(),'Kids Puzzle')]")).click();
				driver.findElement(By.xpath("//a[@title='Kids Puzzle'][contains(text(),'Kids Puzzle')]")).click();
	            
	            String title2=driver.getTitle();
	            System.out.println(title2);
	            if(title2.contentEquals("Kids Puzzles & Puzzle Games Online India - Buy at FirstCry.com"))
	            {
	                System.out.println("yes title match");
	            }
	            break;
			}
			
			
		}
		
		
		Thread.sleep(3000);
		driver.close();
        
        }
		
	/**
	 * After entire execution shut down driver instance
	 */
}
		
