package br.com.devops.tasks.ui.tasks_test_ui;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest 
{
	private WebDriver acessarAplicacao()
	{
		System.setProperty("webdriver.chrome.driver",  "C:\\Proton\\ProtonClient\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso()
	{
		WebDriver driver = acessarAplicacao();
		
		try
		{
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");
			driver.findElement(By.id("saveButton")).click();
			
			String text = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", text);
			
		}
		finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao()
	{
		WebDriver driver = acessarAplicacao();
		
		try
		{
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");
			driver.findElement(By.id("saveButton")).click();
			
			String text = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", text);
			
		}
		finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaDataPassada()
	{
		WebDriver driver = acessarAplicacao();
		
		try
		{
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2000");
			driver.findElement(By.id("saveButton")).click();
			
			String text = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", text);
			
		}
		finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData()
	{
		WebDriver driver = acessarAplicacao();
		
		try
		{
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("saveButton")).click();
			
			String text = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", text);
			
		}
		finally {
			driver.quit();
		}
	}
}
