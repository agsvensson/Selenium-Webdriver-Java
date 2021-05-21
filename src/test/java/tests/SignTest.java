package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignTest {
    @Test
    public void testFazerLoginNoTaskit() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        WebDriver nav = new ChromeDriver();
        nav.manage().window().maximize();
        nav.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        nav.get("http://www.juliodelima.com.br/taskit");

        nav.findElement(By.linkText("Sign in")).click();

        nav.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys("julio0001");
        nav.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys("123456");

        nav.findElement(By.linkText("SIGN IN")).click();

        String saudacao = nav.findElement(By.className("me")).getText();

        Assert.assertEquals("Hi, Julio", saudacao);

        nav.quit();
    }

}
