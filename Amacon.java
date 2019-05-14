/*
Author : Kritagya Agarwal
Course : CS - 202
*/



import pack.*;
import java.util.*;
import java.util.Scanner; 

public class Amacon{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in); 
        int i = 1;
        int total_revenue = 0;
        Database Data_Base = new Database();
        while(i == 1){
        System.out.println("Enter the type of User:");
        System.out.println("1. Administrator");
        System.out.println("2. Customer");
        System.out.println("3. To Exit");
        int choice = 0;
        choice = in.nextInt();
        if(choice == 1)
        {
            int j = 1;
            while(j==1)
            {

                int ch = 0;
                System.out.println("Select from given Menu:");
                System.out.println("1. To insert Product/Category");
                System.out.println("2. To delete Product/Category");
                System.out.println("3. To search Product");
                System.out.println("4. To modify Product");
                System.out.println("5. To exit as administrator");
                ch = in.nextInt();
                if(ch == 1){
                    System.out.println("Enter the Path to Subcategory");
                    String S = in.next();
                    System.out.println("Enter the Product");
                    String p = in.next();
                    System.out.println("Enter the Price and Quantity of Product");
                    int pri = 0;
                    int quan = 0;
                    pri = in.nextInt(); quan = in.nextInt();
                    Product P = new Product(p, pri, quan);
                    try{
                        Data_Base.insert(S, P);
                    } catch(InsertException ex)
                    {
                        System.err.println(ex);
                    }
                }
                else if(ch == 2){
                    System.out.println("Enter the Path to delete");
                    String S = in.next();
                    try{
                        Data_Base.delete(S);
                    } catch(DeleteException ex)
                    {
                        System.err.println(ex);
                    }
                }
                else if(ch == 3){
                    System.out.println(("Enter the product to Search"));
                    String S = in.next();
                    try{
                    Product P = Data_Base.search(S);
                    }catch(SearchException ex)
                    {
                        System.err.println(ex);
                    }
                }
                else if(ch == 4){
                    System.out.println("Enter the product to modify");
                    String S = in.next();
                    try{
                        Product P = Data_Base.search(S);   
                        if(P.Type == "/")
                        {
                            System.out.println("Product Doesnot Exist");
                        }
                        else
                        {
                            System.out.println("Enter the Price and Unit of Product");
                            int pri = 0;
                            int quan = 0;
                            pri = in.nextInt(); quan = in.nextInt();
                            Data_Base.modify(P, pri, quan);
                        }
                     }catch(SearchException ex)
                     {
                         System.err.println(ex);
                     }
                    }
                else {
                    j = 0;
                }
            }
        }
        else if(choice == 2)
        {
            Customer Client = new Customer();
            int j = 1;
            while(j==1)
            {
                int ch = 0;
                System.out.println("Select from given Menu:");
                System.out.println("1. Add funds to account");
                System.out.println("2. Add product to cart");
                System.out.println("3. Check Out Cart");
                System.out.println("4. To exit as Customer");
                ch = in.nextInt();
                if(ch == 1){
                    System.out.println("Enter the Amount to Add in Fund");
                    int fund = 0;
                    fund = in.nextInt();
                    Client.add_balance(fund);
                }
                else if(ch == 2){
                    System.out.println("Enter the Product to Add in Cart");
                    String TT = in.next();
                    System.out.println("Enter the unit to Add");
                    int unit = in.nextInt();
                    try{
                        try{
                        Product T = Data_Base.search(TT);
                        Client.add_cart(Data_Base,T, unit);
                        }catch(CartException ex)
                         {
                            System.err.println(ex);
                        }
                        }catch(SearchException ex)
                        {
                            System.err.println(ex);
                        }

                }
                else if(ch == 3){
                    try{
                    total_revenue = Client.check_out(Data_Base);}catch(SaleException ex) {
                        System.err.println(ex);
                    }
                }
                else {
                    j = 0;
                }
            }
        }
        else
        {
            i = 0;
        }

        }
        System.out.println("Total Revenue Generated :" + total_revenue);
    }
}
