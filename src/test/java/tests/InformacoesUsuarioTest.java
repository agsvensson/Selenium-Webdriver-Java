package tests;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    private WebDriver nav;  // expõe a todos os métodos da classe

    @Rule
    public TestName test = new TestName();

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

//    @Test  // necessário para que sejam interpretados como um teste válido ao clicar em executar
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // clicar no botão através do seu xPath //button[@data-target="addmoredata"]
        nav.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // identificar a popup onde está o formulário de id addmoredata
        WebElement popUpAddMoreData = nav.findElement(By.id("addmoredata"));

        // na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popUpAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // no campo de name "contact" digitar "(51) 99999-9999"
        popUpAddMoreData.findElement(By.name("contact")).sendKeys("(51) 99999-9999");

        // clicar no link de text "SAVE" que está no popup
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = nav.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Your contact has been added!", mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario() {
        // clicar no elemento pelo seu xPath //span[text()="(51) 99999-9999"]/following-sibling::a
        nav.findElement(By.xpath("//span[text()=\"(51) 99999-9999\"]/following-sibling::a")).click();

        // confirmar janela javascript
        nav.switchTo().alert().accept();

        // validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = nav.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "\\Desktop\\workspace\\Testes\\webdriver-java\\test-report-screenshot\\taskit" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(nav, screenshotArquivo);

        // aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(nav, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // clicar no link com texto "Logout"
        nav.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        // fechar o navegador e encerrar a sessão
        //nav.quit();
    }
}
