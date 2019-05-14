package pack;
import java.util.*;

public class Customer{
	int fund = 0;
	public class ob{
		Product Prod;
		int quantity;

		public ob(Product P,int q)
		{
			Prod = P;
			quantity = q;
		}
	}
	ArrayList <ob> cart = new ArrayList<ob>();
	
	public void add_balance(int balance)
	{
		fund += balance;	
	}

	public void add_cart(Database D,Product T, int quantity) throws CartException{
		int flag = 0;
		for(int i=0;i<D.data.size();i++)
		{
			Custom V = D.data.get(i);
			if(V.P.Type.equalsIgnoreCase(T.Type) && V.P.quantity>=quantity)
			{
				flag = 1;
				break;
			}
		}

		if(flag == 1){
			ob A = new ob(T,quantity);
			cart.add(A);
		}
		else
			throw new CartException("Quantity Mentioned is More ");
	}
	
	public int check_out(Database D) throws SaleException
	{
		int total_val = -1;
		for(int i=0;i<cart.size();i++)
		{
			if(cart.get(i).Prod.quantity  < cart.get(i).quantity)
			{
				total_val = -1;
				break;
			}
			total_val += cart.get(i).quantity*cart.get(i).Prod.price;
		}

		if(total_val!=-1 && total_val<=fund)
		{
			for(int i = 0;i<cart.size();i++){
				ob ans = cart.get(i);
				int response = D.sale(ans.Prod,ans.quantity,fund);
				if(response != 0) 
					fund -= response; 
			}
		}
		else{
			total_val = 0;
			throw new SaleException("Not Sufficient Balance/Units");
		}
		return total_val;
	}
}
