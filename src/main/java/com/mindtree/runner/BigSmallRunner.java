package com.mindtree.runner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.pageObjects.ChristmasGiftsPage;
import com.mindtree.pageObjects.CorporateGiftsPage;
import com.mindtree.pageObjects.LandingPage;
import com.mindtree.pageObjects.MarvelPage;
import com.mindtree.pageObjects.MinionsPage;
import com.mindtree.pageObjects.PersonalizedGiftsPage;
import com.mindtree.pageObjects.ProductPage;
import com.mindtree.pageObjects.SignInPage;
import com.mindtree.reusableComponents.WebDriverHelper;


public class BigSmallRunner extends WebDriverHelper
{
	private static Logger log=LogManager.getLogger(BigSmallRunner.class.getName());
	@BeforeMethod
	public void browserInvoke() throws IOException
	{
		driver=baseFile();
		driver.get(prop.getProperty("url"));
		log.debug("browser is invoked");
	}
	
	@Test
	public void signinValidation() throws IOException
	{
		LandingPage lp=new LandingPage(driver);
		lp.getSignin().click();
		log.info("clicked on signin icon");
		SignInPage sp=new SignInPage(driver);
		sp.getEmail().sendKeys("madhu4641999@gmail.com");
		sp.getPassword().sendKeys("madhu");
		sp.getSubmit().click();
		log.info("email and password were entered in the respective spaces");
		
	}
	@Test
	public void addToCartValidation()
	{
		LandingPage lp=new LandingPage(driver);
		lp.getSearchBox().sendKeys("key chains");
		log.debug("entered the keyword into the search box");
		lp.getSearchBox().sendKeys(Keys.ENTER);
		log.debug("text is entered into the searchbox");
		ProductPage p=new ProductPage(driver);
		p.getProduct().click();
		log.debug("clicked on the product");
		p.getAddToCart().click();
		log.debug("added to the cart");
		p.getCheckout().click();
		log.debug("clicked on checkout");
		Assert.assertTrue(p.getTextmessage().isDisplayed());;
		log.debug("textmsg is validated");
	}
	
	@Test
	public void corporateGiftsValidation()
	{
		LandingPage l=new LandingPage(driver);
		l.getCorporateGifts().click();
		log.debug("clicked on corporate gifts icon");
		CorporateGiftsPage cgp=new CorporateGiftsPage(driver);
		cgp.getName().sendKeys("Madhu Palla");
		cgp.getEmail().sendKeys("madhu@gmail.com");
		cgp.getPhone().sendKeys("56165161");
		cgp.getEnquiry().sendKeys("vijayawada, andhra, quantity-500");
		cgp.getSubmit().click();
		log.debug("details were entered successfully");
	}
	
	@Test
	public void personalizedGiftsValidation()
	{
		LandingPage l=new LandingPage(driver);
		l.getPersonalizedGifts().click();
		log.debug("clicked on personalized gifts icon");
		PersonalizedGiftsPage pg=new PersonalizedGiftsPage(driver);
		pg.getProduct().click();
		log.debug("clicked on product");
		pg.getName().sendKeys("madhu");
		pg.getName().sendKeys(Keys.ENTER);
		pg.getAddToCart().click();
		log.debug("Added to the cart");
	}
	
	@Test
	public void countingLinks()
	{
		LandingPage lp=new LandingPage(driver);
		System.out.println("Total links in the landing page "+lp.getLinks());
	}
	
	@Test
	public void textBoxProductValidation()
	{
		LandingPage lp=new LandingPage(driver);
		lp.getSearchBox().sendKeys("key chains");
		log.debug("entered the keyword into the search box");
		lp.getSearchBox().sendKeys(Keys.ENTER);
		log.debug("text is entered into the searchbox");
		ProductPage p=new ProductPage(driver);
		p.getProduct().click();
		log.debug("clicked on the product");
		p.getWishList().click();
		log.debug("clicked on wishlist icon");
		p.getViewWishList().click();
		p.getAccess().click();
		log.debug("clicked on access to wishlist");
		Assert.assertTrue(p.getValidate().isDisplayed());
		log.debug("product is successfully added to wishlist");
		
	}
	@Test
	public void wishlistProductRemovalValidation()
	{
		LandingPage lp=new LandingPage(driver);
		lp.getSearchBox().sendKeys("key chains");
		log.debug("entered the keyword into the search box");
		lp.getSearchBox().sendKeys(Keys.ENTER);
		log.debug("text is entered into the searchbox");
		ProductPage p=new ProductPage(driver);
		p.getProduct().click();
		log.debug("clicked on the product");
		p.getWishList().click();
		log.debug("clicked on wishlist icon");
		p.getViewWishList().click();
		p.getAccess().click();
		log.debug("clicked on access to wishlist");
		Assert.assertTrue(p.getValidate().isDisplayed());
		log.debug("product is successfully added to wishlist");
		p.getRemove().click();
		System.out.println("good");
		
	}
	
	@Test
	public void removeFromCartValidation()
	{
		LandingPage lp=new LandingPage(driver);
		lp.getChristmasGifts().click();
		log.debug("clicked on christmas gifts icon");
		ChristmasGiftsPage cp=new ChristmasGiftsPage(driver);
		cp.getProduct().click();
		log.debug("clicked on product");
		cp.getAddToCart().click();
		log.debug("clicked on add to cart");
		cp.getRemove().click();
		
  }
	
	@Test
	public void dropdown()
	{
		LandingPage l=new LandingPage(driver);
		Actions action = new Actions(driver);
		action.moveToElement(l.getShopByCategory()).perform();
		action.moveToElement(l.getShopByPerson()).perform();
		l.getMinion().click();
		MinionsPage mp=new MinionsPage(driver);
		mp.getMinionProduct().click();
		System.out.println("good");
		String result=mp.getSoldOut().getText();
		Assert.assertEquals(result,"SOLD OUT");
		log.info("the product is sold out");
	}
	
	@Test
	public void marvelPage() throws InterruptedException
	{
		LandingPage l= new LandingPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,4000)", "");
		l.getMarvel().click();
		log.info("clicked on marvel logo and  navigated to marvel page");
		MarvelPage m=new MarvelPage(driver);
		m.getSortBy().click();
		System.out.println("dropdown clicked");
		for(int i=0;i<3;i++)
		{
			m.getSortBy().sendKeys(Keys.ARROW_DOWN);
			
		}
		m.getSortBy().sendKeys(Keys.ENTER);
		Assert.assertTrue(m.getValidate().isDisplayed());
		System.out.println("good");
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
		log.info("browser is closed");
	}
}
