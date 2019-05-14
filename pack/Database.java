package pack;
import java.util.*;
import java.util.Iterator;

public class Database{


	ArrayList<Custom> data = new ArrayList<Custom>(); 
	public void insert (String Path,Product P) throws InsertException{
		Custom C = new Custom(Path,P);
		C.Path = Path;
		C.P = P;

		int flag = 1;
		for(int i=0;i<data.size();i++)
		{
			Custom V = data.get(i);
			if(V.Path.equalsIgnoreCase(Path) && V.P.Type.equalsIgnoreCase(P.Type))
			{
				flag = 0;
				break;
			}
		}

		if(flag == 0)
		{
			throw new InsertException("Product already Present at Given Path");
		}

		if(flag == 1)
			data.add(C);
	} 

	public Product search(String T) throws SearchException{
		int in = 0;
		int flag = 0;
		for(int i=0;i<data.size();i++)
		{
			Custom V = data.get(i);
			if(V.P.Type.equalsIgnoreCase(T))
			{	
				System.out.println("Details of Product : ");
				System.out.println("Name " + V.P.Type);
				System.out.println("Price " + V.P.price);
				System.out.println("Quantity " + V.P.quantity);
				System.out.println("Path " + V.Path);
				flag = 1;
				in = i;
				break;
			}
		}

		if(flag == 0){
			throw new SearchException("Product Doesnot Exist");
		}
		return data.get(in).P;
	}

	public void delete(String Query) throws DeleteException{
		int count = 0;
		int flag = 0;
		for(int i=0;i<Query.length();i++)
		{
			char c = Query.charAt(i);
			if(c == '>')
			{
				count++;
			}
		}
		if(count == 1){
			Iterator it = data.iterator();
			while(it.hasNext())
			{
				Custom V = (Custom)it.next();
				if(V.Path.equalsIgnoreCase(Query))
				{
					flag = 1;
					it.remove();
				}
			}
		}

		else{
			String Path_t = "";
			String Type_t = "";
			int t = 0;
			for(int i=0;i<Query.length();i++)
			{
				char c = Query.charAt(i);
				if(c == '>') t++;
				if(t<=1)
				{
					Path_t += c;
				}
				else
				{
					if(c!='>')
					{
						Type_t += c;
					}
				}
			}
			
			Iterator it = data.iterator();
			while(it.hasNext())
			{
				Custom V = (Custom)it.next();
				if(V.Path.equalsIgnoreCase(Path_t) && V.P.Type.equalsIgnoreCase(Type_t))
				{
					flag = 1;
					it.remove();
				}
			}

		}

		if(flag == 0)
		{
			throw new DeleteException("No Product At Given Path");
		}
	}

	public void modify(Product Query,int p,int unit)
	{
		Query.price = p;
		Query.quantity = unit;
	}

	public int value(String pro)
	{
		int resp = 0;
		for(int i = 0;i<data.size();i++)
		{
			Custom V = data.get(i);
			if(V.P.Type.equalsIgnoreCase(pro))
			{
				resp = V.P.price;
				break;
			}
		}
		return resp;
	}
	public int sale(Product pro,int unit,int fund)
	{
		int resp = 0;
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Custom V = (Custom)it.next();
			if(V.P.Type.equalsIgnoreCase(pro.Type))
			{
				if(V.P.quantity>=unit && V.P.price*unit<=fund)
				{
					resp = V.P.price*unit;
					V.P.quantity-=unit;
					if(V.P.quantity == 0)
					{
						it.remove();
					}
					break;
				}
			}
		}
		return resp;
	}


}
