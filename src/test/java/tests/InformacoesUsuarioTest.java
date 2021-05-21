package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    // o @test é necessário para que sejam interpretados como um teste válido ao clicar em executar
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        WebDriver nav = new ChromeDriver();
        nav.manage().window().maximize();
        nav.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // navegando para a página do Taskit
        nav.get("http://www.juliodelima.com.br/taskit");

        // clicar no link que possui o texto "Sign in"
        nav.findElement(By.linkText("Sign in")).click();

        // identificando o formulário de Login
        WebElement formularioSignInBox = nav.findElement(By.id("signinbox"));

        // digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // digitar no campo com name "password" que está dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // clicar no link com o texto "SIGN IN"
        nav.findElement(By.linkText("SIGN IN")).click();

        // validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        WebElement me = nav.findElement(By.className("me"));
        String textoNoElementoMe = me.getText(); // getText pega o que está entre o abrir e fechar da tag a
        Assert.assertEquals("Hi, Julio", textoNoElementoMe);

        // fechar o navegador e encerrar a sessão
        nav.quit();



    }
}
