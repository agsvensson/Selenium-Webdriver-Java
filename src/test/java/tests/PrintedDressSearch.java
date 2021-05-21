package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PrintedDressSearch {
    @Test
    public void testPrintedDressSearchAutomation() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        WebDriver nav = new ChromeDriver();
        nav.manage().window().maximize();
        nav.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        nav.get("http://automationpractice.com");

        // Escreve a pesquisa desejada
        nav.findElement(By.id("search_query_top")).sendKeys("printed dress");

        // Clica no botão da caixa de pesquisa para efetuar a procura
        nav.findElement(By.name("submit_search")).click();

        // entra no vestido com o título especificado - Printed Dress
        nav.findElement(By.linkText("Printed Dress")).click();

        // adiciona mais um vestido, totalizando 2.
        nav.findElement(By.className("icon-plus")).click();

        // seleciona tamanho M
        nav.findElement(By.id("group_1")).sendKeys("M");

        // adiciona no carrinho
        nav.findElement(By.name("Submit")).click();
    }
}