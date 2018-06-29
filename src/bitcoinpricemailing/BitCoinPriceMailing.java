/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitcoinpricemailing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Arpitagarwal
 */
public class BitCoinPriceMailing {

    /**
     * @param args the command line arguments
     */
    WebDriver driver_gecko, driver_zebpay;
    String textMsg = "";
    public BitCoinPriceMailing(WebDriver driver_zebpay) {
//        this.driver_gecko = driver_gecko;
        this.driver_zebpay = driver_zebpay;
        // TODO code application logic here
    }
    public void OpenSites(){
//        driver_gecko.get("https://www.coingecko.com/en");
        driver_zebpay.get("https://www.zebpay.com/");
       
    }
    
    public void FetchCoinListFromZebPay() throws InterruptedException{
        Thread.sleep(2000);
        List<WebElement> list = driver_zebpay.findElements(By.className("multicoin-price"));
//        int i = 1;
        
        for(WebElement coin: list){
            String text = coin.getText();
            textMsg+=("( "+coin.getAttribute("id").substring(3).toUpperCase()+" "+text+" ) ");
//            System.out.print(String.valueOf(i)+" "+coin.getText()+" ");
//            System.out.println(coin.getAttribute("id").substring(3).toUpperCase());
//            
//            i++;
        }
        System.out.println(textMsg);
    }
    public void MailMessage() throws AddressException, MessagingException{
        String host = "smtp.gmail.com";
        String from = "arpitagarwal1308@gmail.com";
        String password = "";
        String to = "Arpitagarwal@qainfotech.com";
        
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {@Override
 protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(from, password);
}});
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("karannahata@qainfotech.com"));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("anmolbagga@qainfotech.com"));
     message.setSubject("ZebPay Coin Prices");  
     message.setText(textMsg);
     
            Transport.send(message);
        }
        catch(MessagingException e){
        throw new RuntimeException(e);}
            
        }
        
    }
