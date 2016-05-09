package com.globant.bootcamp.api;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceShoppingCartTest {

    private ServiceShoppingCart cart;
    private final int PROD_NUM = 5;
    private final int QUANTITY = 5;
    private Product[] products;

    @Before
    public void setup(){
        cart = ServiceShoppingCartFactory.getShoppingCartImp();
        products = new Product[PROD_NUM];
        for(int i=0;i<PROD_NUM;i++)
            products[i]=new Product("prod"+i, i+1);
    }

    @Test
    public void PaymentMethodIsSetCorrectly(){
        cart.setPaymentMethod("Credit Card");
        Assert.assertEquals("Credit Card", cart.getPaymentMethod());
    }

    @Test
    public void WhenProdIsNullThenAddReturnsFalse(){
        Assert.assertFalse(cart.addProduct(null));
    }

    @Test
    public void WhenZeroProdsAreIntendedToAddThenAddMultipleProdReturnsFalse(){
        Assert.assertFalse(cart.addMultipleProducts(products[0], 0));
    }

    @Test
    public void WhenProdIsNullThenAddMultipleProdReturnsFalse(){
        Assert.assertFalse(cart.addMultipleProducts(null, 1));
    }

    @Test
    public void WhenProdIsNullThenRmReturnsFalse(){
        Assert.assertFalse(cart.rmProduct(null));
    }

    @Test
    public void WhenZeroProdsAreIntendedToRemoveThenAddMultipleProdReturnsFalse(){
        Assert.assertFalse(cart.rmMultipleProducts(products[0], 0));
    }

    @Test
    public void WhenProdIsNullThenRmMultipleProdReturnsFalse(){
        Assert.assertFalse(cart.rmMultipleProducts(null, 1));
    }

    @Test
    public void RemoveProdAfterAddingCorrectlyItReturnsTrue(){
        if(cart.addProduct(products[0]))
            Assert.assertTrue(cart.rmProduct(products[0]));
    }

    @Test
    public void WhenAddManyTimesTheSameProdThenItIsCorrect(){
        Assert.assertTrue(cart.addProduct(products[0]));
        Assert.assertTrue(cart.addProduct(products[0]));
        Assert.assertTrue(cart.addProduct(products[0]));
        Assert.assertTrue(cart.addProduct(products[0]));
    }


    @Test
    public void WhenAddManyTimesTheSameProdThenTheyAllAreAdded(){
        cart.addProduct(products[0]);
        cart.addProduct(products[0]);
        cart.addProduct(products[0]);
        cart.addProduct(products[0]);
        Assert.assertTrue(cart.rmProduct(products[0]));
        Assert.assertTrue(cart.rmProduct(products[0]));
        Assert.assertTrue(cart.rmProduct(products[0]));
        Assert.assertTrue(cart.rmProduct(products[0]));
    }

    @Test
    public void WhenAddMultipleReturnsTrueThenAllOfTheProdsAreInserted(){
        if(cart.addMultipleProducts(products[0], QUANTITY)){
            for(int i=0;i<QUANTITY;i++)
                Assert.assertTrue(cart.rmProduct(products[0]));
        }
    }

    @Test
    public void AfterClearingCartItIsEmpty(){
        cart.clearProducts();
        Assert.assertTrue(cart.isEmpty());
    }

    @Test
    public void GetTotalSumsAllPrices(){
        double res=0.0;
        for(Product p : cart.getProductList())
            res+= p.getPrice();

        Assert.assertTrue(res == cart.getTotal());
    }

    @Test
    public void TotalFromSaleIsEqualToGetTotal(){
        cart.addMultipleProducts(products[0],QUANTITY);
        Sale sale = cart.buy();
        Assert.assertTrue(sale.getTotal() == cart.getTotal());
    }

    @After
    public void teardown(){
        cart = null;
        products = null;
    }
}
