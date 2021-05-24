package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RemoverContatos {
    private WebDriver nav;  // expõe a todos os métodos da classe

    @Before
    public void setUp() {  // o que eu preciso para que o meu teste funcione
        // abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        nav = new ChromeDriver();
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

        // clicar em um link que possui a class "me"
        nav.findElement(By.className("me")).click();

        // clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        nav.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

        @Test
        public void removerTodosContatosDeTelefone() {
            int delete=0;
            while (delete < 8) {
                try {
                    // procura pelo xpath
                    nav.findElement(By.xpath("//a[@class=\"secondary-content button\"]")).click();
                } catch (Exception ex) {
                    nav.findElement(By.cssSelector("a.secondary-content.button")).click();
                }

                // confirmar janela javascript
                nav.switchTo().alert().accept();

                delete = delete + 1;
            }
        }

    @After
    public void tearDown() {
        // fechar o navegador e encerrar a sessão
        //nav.quit();
    }
}