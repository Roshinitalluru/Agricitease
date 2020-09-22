package com.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.rest.dto.Address;
import com.rest.dto.Blog;
import com.rest.dto.Farm;
import com.rest.dto.Orders;
import com.rest.dto.Product;
import com.rest.dto.Statistics;
import com.rest.dto.User;
import com.ts.dao.AddressDAO;
import com.ts.dao.BlogDAO;
import com.ts.dao.FarmDAO;
import com.ts.dao.OrdersDAO;
import com.ts.dao.ProductDAO;
import com.ts.dao.UserDAO;
@Path("myresource1")
public class MyResource1 {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    } 
	//@Path("registerfarmer")
	//@POST
	// @Consumes(MediaType.APPLICATION_JSON)
	//public User registerfarmer(Address address){
	//System.out.println("data recieved " + address);
	//AddressDAO addrdao = new AddressDAO();
		//addrdao.register(address);
		//return address.getUser();
		//}
	@Path("registerfarmer")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public User registerfarmer(Address address){
		System.out.println("data recieved " + address);
		String pwd = address.getUser().getPassword();
		address.getUser().setPassword(securepassword(pwd));
		AddressDAO addrdao = new AddressDAO();
		
		
		addrdao.register(address);
		return address.getUser();
	}
	public String securepassword(String pwd){
		char[] chars = pwd.toCharArray();
		for (int i=0; i<chars.length; i++){
			chars[i] += 5;}
			String str2 = String.valueOf(chars);
		return str2 ;
	}
	@Path("farmUpload")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void farmUpload(Farm farm){
		
		FarmDAO farmdao = new FarmDAO();
		farm.setRemainingQuantity(farm.getCropQuantity());
		System.out.println("farm updated with remaining quantity"+farm);
		farmdao.register(farm);
		System.out.println("data recieved " + farm);
	}
	@Path("viewProfile/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address viewProfile(@PathParam("userId") int userId){
		AddressDAO userdao = new AddressDAO();
		Address users = userdao.viewProfile(userId);
		return users;
	}
	@Path("getFarmsbyId/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Farm> farmsbyId(@PathParam("userId") int userId){
		FarmDAO userdao = new FarmDAO();
		List<Farm> users = userdao.getFarmsById(userId);
		for(Object farm:users){
			System.out.println(farm);
		}
		return users;
	}
	
	
    @Path("updateEmp")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmp(Farm farm){
    	FarmDAO farmdao = new FarmDAO();
    	farmdao.updateEmp(farm);
    	System.out.println("Data recieved in update"+farm);
    }
	//@Path("loginuser/{userName}/{password}/{role}")
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    //public User loginuser(@PathParam("userName") String userName, @PathParam("password") String password ,@PathParam("role") String role){
    //UserDAO userdao = new UserDAO();
    //System.out.println("data in path params"+" "+userName+" "+password+" "+role);
    //User user1 = userdao.login(userName,password,role);
    //System.out.println(user1);
    //return user1;
    //}
    @Path("loginuser/{userName}/{password}/{role}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public User loginuser(@PathParam("userName") String userName, @PathParam("password") String password ,@PathParam("role") String role){
		UserDAO userdao = new UserDAO();
		char[] chars = password.toCharArray();
		System.out.println(password);
		for (int i=0; i<chars.length; i++){
			chars[i] += 5;}
		String str2 = String.valueOf(chars);
		
			
		
		System.out.println("data in path params"+" "+userName+" "+str2+" "+role);
		User user1 = userdao.login(userName,str2,role);
		System.out.println(user1);
		return user1;
		}
	@POST
	@Path("/uploadImage")	
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadImage(@FormDataParam("Image") InputStream fileInputStream ,@FormDataParam("Image") FormDataContentDisposition 
			formDataContentDisposition,@FormDataParam("Video") InputStream fileInputStream1 ,@FormDataParam("Video") FormDataContentDisposition 
			formDataContentDisposition1,@FormDataParam("productName") String productName,@FormDataParam("Image") String description ) throws IOException{
   	int read=0;
   	byte[] bytes =new byte[1024];
   	 String path=this.getClass().getClassLoader().getResource("").getPath();
   	 String pathArr[]=path.split("WEB-INF/classes/");
   	 
   	 FileOutputStream out= new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));
   	 FileOutputStream out1= new FileOutputStream(new File(pathArr[0]+"/video/",formDataContentDisposition1.getFileName()));
   	 
   	 while((read=fileInputStream.read(bytes)) != -1){
   		 out.write(bytes, 0, read);
   	 }
   	 out.flush();
   	 out.close();
   	while((read=fileInputStream1.read(bytes)) != -1){
  		 out1.write(bytes, 0, read);
  	 }
  	 out1.flush();
  	 out1.close();
  	 
   	 Product product = new Product();
   	 product.setProductName(productName);
   
   	 product.setImageName(formDataContentDisposition.getFileName());
   	 product.setVideoName(formDataContentDisposition1.getFileName());
   	 ProductDAO productdao=new ProductDAO();
   	 productdao.addproduct(product);
   	 
   	}
	
   	
	@Path("getProducts")
	@GET
   @Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts(){
		System.out.println("data recieved " );
		ProductDAO productdao = new ProductDAO();
		List<Product> empList =productdao.getProductss();
		return empList;
	}
	
    @Path("regEmp")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String daoTest() {
    	User user = new User();
    	User user1 = new User();
    	user.setUserId(100);
    	user.setUserName("manasa");
    	user.setPassword("manu");
    	user.setFullName("K.Sai Manasa");
    	user.setAge(20);
    	user.setMobileNumber("9866");
    	user.setQualification("Inter");
    	user.setRole("Representative");
    	user1.setUserId(100);
    	user1.setUserName("manasa");
    	user1.setPassword("manu");
    	user1.setFullName("K.Sai Manasa");
    	user1.setAge(20);
    	user1.setMobileNumber("9866");
    	user1.setQualification("Inter");
    	user1.setRole("Farmer");
    	
    	UserDAO userdao = new UserDAO();
    	userdao.register(user);
    	userdao.register(user1);
    	Address address = new Address();
    	address.setAddressId(1);
    	address.setCity("Sanathnagar");
    	address.setStreet("Hanuman Street");
    	address.setState("Telangana");
    	address.setPincode(500018);
    	address.setUser(user);
    	AddressDAO addrdao = new AddressDAO();
    	addrdao.register(address);
    	Farm farm = new Farm();
    	farm.setAddress(address);
    	farm.setCrop("tomato");
    	farm.setCropQuantity(50);
    	farm.setDoj(null);
    	farm.setKindOfCrop("vegetable");
    	farm.setFieldArea(12);
    	FarmDAO farmdao = new FarmDAO();
    	farmdao.register(farm);
    	
    	return "Success";
    }
    @Path("sendsms")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public void sendsms() {
    	try {
    		// Construct data
    		System.out.println("into the method");
    		String apiKey = "apikey=" + "fa2Rh0DmyRg-K93SgJN6ltt4l7lY8PURkQwcMaTEnf   ";
    		String message = "&message=" + "Your farm has been succesfully uploaded!";
    		String sender = "&sender=" + "TXTLCL";
    		String numbers = "&numbers=" + "9059137979";
    		System.out.println("into the method1");
    		// Send da
    		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
    		String data = apiKey + numbers + message + sender;
    		conn.setDoOutput(true);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
    		conn.getOutputStream().write(data.getBytes("UTF-8"));
    		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		final StringBuffer stringBuffer = new StringBuffer();
    		String line;
    		while ((line = rd.readLine()) != null) {
    			//stringBuffer.append(line);
    			JOptionPane.showMessageDialog(null,"message" + line);
    		}
    		rd.close();
    		//return stringBuffer.toString();

    	} catch (Exception e) {

    		//System.out.println("Error SMS "+e);

    		//return "Error "+e;
    		JOptionPane.showMessageDialog(null,e);

    	}

    	// TODO Auto-generated method stub



    }

//consumer
    @Path("getAddressOfFarms")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	//first three methods are related view all farms thing i.e show db images component
  	public List<Address> getAddressOfFarms(){
  		FarmDAO farmdao = new FarmDAO();
  		List<Farm> farms = farmdao.getAllFarms();
  		List<Address> addresses = new ArrayList<Address>();
  		AddressDAO userdao = new AddressDAO();
  		for(Farm farm:farms){
  			System.out.println(farm);
  			Address addr = farm.getAddress();
  			int farmId = addr.getAddressId();
  			System.out.println("RECIEVED FARMiD"+farmId);
  			addresses.add(userdao.viewProfile(farmId));
  		}
  		System.out.println(addresses);
  		return addresses;
  		
  	}
  	@Path("getallfarms")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public List<Farm> getAllFarms(){
  		FarmDAO farmdao = new FarmDAO();
  		List<Farm> farms = farmdao.getAllFarms();
  		for(Object farm:farms){
  			System.out.println(farm);
  		}
  		return farms;
  		
  	}
     
  	// next three methods are related to filters
  	@Path("getproductsOfFarmsbykind/{kind}")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public List<Product> getproductsOfFarmsbykind(@PathParam("kind") String kind){
  		FarmDAO farmdao = new FarmDAO();
  		List<Farm> farms = farmdao.getFarmsbyKind(kind);
  		List<Product> products = new ArrayList<Product>();
  		ProductDAO prodao = new ProductDAO();
  		for(Farm farm:farms){
  			System.out.println(farm);
  			int farmId = farm.getFarmId();
  			System.out.println("RECIEVED FARMiD"+farmId);
  			products.add(prodao.getProductbyId(farmId));
  		}
  		System.out.println(products);
  		return products;
  	}
  	@Path("getAddressOfFarmsbykind/{kind}")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public List<Address> getAddressOfFarmsbykind(@PathParam("kind") String kind){
  		FarmDAO farmdao = new FarmDAO();
  		List<Farm> farms = farmdao.getFarmsbyKind(kind);
  		List<Address> addresses = new ArrayList<Address>();
  		AddressDAO userdao = new AddressDAO();
  		for(Farm farm:farms){
  			System.out.println(farm);
  			Address addr = farm.getAddress();
  			int farmId = addr.getAddressId();
  			System.out.println("RECIEVED FARMiD"+farmId);
  			addresses.add(userdao.viewProfile(farmId));
  		}
  		System.out.println(addresses);
  		return addresses;
  	}
  	@Path("getfarmsbykind/{kind}")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public List<Farm> getFarmsbykind(@PathParam("kind") String kind){
  		FarmDAO farmdao = new FarmDAO();
  		List<Farm> farms = farmdao.getFarmsbyKind(kind);
  		for(Object farm:farms){
  			System.out.println(farm);
  		}
  		return farms;
  		
  	}
  	// these 3 methods are related to single farm
  	@Path("getSingleFarm/{farmId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address getSingleFarm(@PathParam("farmId") int farmId){
		FarmDAO frdao = new FarmDAO(); 
		Farm farm =	frdao.getSingleFarmbyId(farmId);
		Address addr = farm.getAddress();
		System.out.println("data recieved"+farm);
		int addressId = addr.getAddressId();
		AddressDAO adddao = new AddressDAO();
		return adddao.viewProfile(addressId);	
	}
	
	
	@Path("getProductsbyId/{productId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductsbyId(@PathParam("productId") int productId){
		System.out.println("data recieved " );
		ProductDAO productdao = new ProductDAO();
		Product prod =productdao.getProductbyId(productId);
		return prod;
	}

	   @Path("testId/{farmId}/{consumerId}")
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public void test(@PathParam("farmId") int farmId,@PathParam("consumerId") int consumerId ){
		   FarmDAO farmDao = new FarmDAO();
		   Farm farm1 = farmDao.getSingleFarmbyId(farmId);
		   String Mobile = farm1.getAddress().getUser().getMobileNumber();
		   String CropName = farm1.getCrop();
		   String FarmerName = farm1.getAddress().getUser().getFullName();
		   
		   AddressDAO userDao = new AddressDAO();
			Address users= userDao.viewProfile1(consumerId);
		    String Address = users.getCity();
		    String Name = users.getUser().getFullName();
		    System.out.println(CropName);
		    System.out.println(Address);
		    System.out.println(Mobile);
		    System.out.println(Name);
		    System.out.println(FarmerName);
		  
		   consumersms(FarmerName,Mobile, Name,CropName,Address);
	   }
	   public void consumersms(String FarmerName,String Mobile, String Name, String CropName,String Address){
		   try {
				// Construct data
				
				System.out.println("into the method" + Mobile);
				String apiKey = "apikey=" + "1Da7j+g0ga8-LdgD7Ijw0CzCT6cxCIiTPxxrZl9o4q";
				String message = "&message=" + "Hi" +" "+ FarmerName+"!\n" + Name +" " + "from" + " " +Address +" " + "booked your" + " "+ CropName + " " + "yield\n You can use Video Call Option for further Dealings\nThank You\n-Agricitease"  ;
				String sender = "&sender=" + "TXTLCL";
				String numbers = "&numbers=" + Mobile;
				
				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				final StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					//stringBuffer.append(line);
					
				JOptionPane.showMessageDialog(null,"meassage" + line);
				}
				rd.close();
				
				//return stringBuffer.toString();
			} catch (Exception e) {
				//System.out.println("Error SMS "+e);
				//return "Error "+e;
				JOptionPane.showMessageDialog(null,e);
			}
		   
		     
		   }

//orders
	   @Path("getordersbyId/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Orders> getordersbyId(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			List<Orders> orders = orddao.getOrdersbyId(id);
			for(Object ordd:orders){
				System.out.println(ordd);
			}
			return orders;
		}
		@Path("getfarmsofOrders/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Farm> getfarmsofOrders(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			FarmDAO farmdao = new FarmDAO();
			List<Orders> orders = orddao.getOrdersbyId(id);
			List<Farm> farms = new ArrayList<Farm>();
			for(Orders ordd:orders){
				int farmId = ordd.getFarmId();
				Farm fr = farmdao.getSingleFarmbyId(farmId);
				System.out.println(fr);
				farms.add(fr);
			}
			return farms;		
		}
		@Path("getConsumersofOrders/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Address> getConsumersofOrders(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			AddressDAO adddao = new AddressDAO();
			List<Orders> orders = orddao.getOrdersbyId(id);
			List<Address> addre = new ArrayList<Address>();
			for(Orders ordd:orders){
				int userId = ordd.getConsumerId();
				Address addr = adddao.viewProfile(userId);
				System.out.println(addr);
				addre.add(addr);
			}
			return addre;		
		}
	//order component functions in myresorces
		@Path("getOrdersbyConsumerId/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Orders> getOrdersbyConsumerId(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			List<Orders> orders = orddao.getOrdersbyConsumerId(id);
			for(Object ordd:orders){
				System.out.println(ordd);
			}
			return orders;
		}
		@Path("getfarmsofConsumerOrders/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Farm> getfarmsofConsumerOrders(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			FarmDAO farmdao = new FarmDAO();
			List<Orders> orders = orddao.getOrdersbyConsumerId(id);
			List<Farm> farms = new ArrayList<Farm>();
			for(Orders ordd:orders){
				int farmId = ordd.getFarmId();
				Farm fr = farmdao.getSingleFarmbyId(farmId);
				System.out.println(fr);
				farms.add(fr);
			}
			return farms;		
		}
		@Path("getFarmersofConsumerOrders/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Address> getFarmersofConsumerOrders(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			AddressDAO adddao = new AddressDAO();
			List<Orders> orders = orddao.getOrdersbyConsumerId(id);
			List<Address> addre = new ArrayList<Address>();
			for(Orders ordd:orders){
				int userId = ordd.getAddressId();
				Address addr = adddao.viewProfile(userId);
				System.out.println(addr);
				addre.add(addr);
			}
			return addre;		
		}
		@Path("getProductsofConsumerOrders/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Product> getProductsofConsumerOrders(@PathParam("id") int id){
			OrdersDAO orddao = new OrdersDAO();
			ProductDAO prodao = new ProductDAO();
			List<Orders> orders = orddao.getOrdersbyConsumerId(id);
			List<Product> addre = new ArrayList<Product>();
			for(Orders ordd:orders){
				int farmId = ordd.getFarmId();
				Product prod = prodao.getProductbyId(farmId);
				System.out.println(prod);
				addre.add(prod);
			}
			return addre;		
		}
		@Path("orderUpload")
		@POST
	    @Consumes(MediaType.APPLICATION_JSON)
		public int orderUpload(Orders order){
			
			OrdersDAO farmdao = new OrdersDAO();
			farmdao.register(order);
			System.out.println("data recieved " + order);
			return order.getTransactionId();
		}
		
	    @Path("updateOrd")
	    @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void updateOrd(Orders order){
	    	OrdersDAO farmdao = new OrdersDAO();
	    	farmdao.updateEmp(order);
	    	System.out.println("Data recieved in update"+order);
	    }
	    //review

	    @Path("addReview")
	   	@POST
	       @Consumes(MediaType.APPLICATION_JSON)
	   	public void addReview(Blog blog){
	   		System.out.println("data recieved " + blog);
	   		BlogDAO blogdao = new BlogDAO();
	   		blogdao.register(blog);
	   		//return address.getUser();
	   	}

	    @Path("getallreviews")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Blog> getAllReviews(){
			System.out.println("Recieved in getAllAddress : " ); 
			BlogDAO addressDao = new BlogDAO();
			List<Blog> addressList = addressDao.getAllReviews() ;
			return addressList;	
		}
	    
	    //
	    
	    @Path("deletefarm")
		@DELETE
		@Produces(MediaType.APPLICATION_JSON)
		public void deletefarm(Farm farm){
	    	System.out.println("data recieved " );
			FarmDAO productdao = new FarmDAO();
			ProductDAO productdao1 = new ProductDAO();
			int farmId=farm.getFarmId();
			Farm farmid2 =productdao.getEmployee(farmId);
			Product product=productdao1.getProductbyId(farmId);
			productdao.deletefarm(farmid2);
			productdao1.deletefarm(product);
		}
	    //update
	    @Path("getSingleFarmbyId")
	    @PUT
	    @Produces(MediaType.APPLICATION_JSON)
	    public Farm singleFarmbyId(Orders order){
	    FarmDAO userdao = new FarmDAO();
	    Farm farm = userdao.getSingleFarmbyId(order.getFarmId());
	    OrdersDAO orddao = new OrdersDAO();
	    int totalQuantityBooked = 0;
	    List<Orders> orders = orddao.getOrdersbyId(order.getAddressId());
	    for(Orders ord:orders){
	    if(ord.getFarmId()==order.getFarmId() && ord.getStatus().equals("Accepted")){
	    System.out.println("the order is"+ord);
	    totalQuantityBooked = totalQuantityBooked + ord.getQuantityBooked();
	    
	    }
	    }
	    System.out.println(totalQuantityBooked);
	    System.out.println("Recieved order is"+order);
	    farm.setRemainingQuantity((farm.getCropQuantity() - totalQuantityBooked));
	    System.out.println("The remaining quantity is" + farm.getRemainingQuantity());
	    System.out.println(farm);
	    int up = userdao.updateEmp(farm);
	    System.out.println("the updated id is"+up);
	    
	    return farm;
	    }
	    @Path("updateProfile")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public void updateProfile(Address address){
			System.out.println("the recieved object is "+address);
			User usr = address.getUser();
			System.out.println("the recieved object full name is"+usr.getFullName());
			System.out.println("the recieved object mobileNumber is"+ usr.getMobileNumber());
			System.out.println("the recieved object age is"+usr.getAge());
			System.out.println("the recieved object state is"+address.getState());
			System.out.println("the recieved object pincode is"+address.getPincode());
			AddressDAO addrDAO = new AddressDAO();
			addrDAO.updateAddress(address);
		}
		//statics
	    @Path("statistics")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Statistics> getFarmerStatistics(){
			FarmDAO frdao = new FarmDAO();
			List<Farm> farmsList = frdao.getAllFarms();
			List<String> crops = new ArrayList<String>();
			for(Farm fr:farmsList){
				crops.add(fr.getCrop());
			}
			LinkedHashSet<String> lhSetCrops = new LinkedHashSet(crops);
			System.out.println(lhSetCrops);
			List<Long> cropCounts = new ArrayList<Long>();
			List<Statistics> sts = new ArrayList<Statistics>();
			for (String cro:lhSetCrops){
				cropCounts.add(frdao.getCropCount(cro));
				Statistics stss = new Statistics();
				stss.setCrop(cro);
				stss.setCounts((int) frdao.getCropCount(cro));
				sts.add(stss);
			}
			System.out.println(cropCounts);
			for(Statistics st:sts){
				System.out.println("the crop is"+st.getCrop()+"and the count is"+st.getCounts());
			}
			return sts;
			
		}
	    
}
