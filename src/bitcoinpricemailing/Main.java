/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitcoinpricemailing;

import javax.mail.MessagingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Arpitagarwal
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, MessagingException {
        System.setProperty("webdriver.chrome.driver", "E:\\My Practice\\chromedriver.exe");
//        WebDriver driver_gecko = new ChromeDriver();
        WebDriver driver_zebpay = new ChromeDriver();
        BitCoinPriceMailing obj = new BitCoinPriceMailing(driver_zebpay);
        obj.OpenSites();
        obj.FetchCoinListFromZebPay();
        obj.MailMessage();
    }    
}
