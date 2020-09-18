package com.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dto.Address;
import com.rest.dto.Farm;
import com.rest.dto.Product;
import com.rest.dto.User;
import com.ts.dao.AddressDAO;
import com.ts.dao.FarmDAO;
import com.ts.dao.ProductDAO;
import com.ts.dao.UserDAO;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    // Use This myresource for your project building
    @Path("registerUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(User user) {
		System.out.println("Data Recieved in Register : " + user); 
		UserDAO userDao = new UserDAO();
		userDao.register(user); 
	}
    @Path("viewProfile/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address viewProfile(@PathParam("userId") int userId){
		AddressDAO userDao = new AddressDAO();
		Address users= userDao.viewProfile1(userId);
		return users;
		
	}
    @Path("addfarms")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Farm addFarm(Farm farm){
    	System.out.println("Data received in addfarm: "+farm);
    	FarmDAO farmDao= new FarmDAO();
    	farmDao.register(farm);
		return farm;
		}    
   

   
    @Path("getAllAddress")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> getAllAddress(){
		System.out.println("Recieved in getAllAddress : " ); 
		AddressDAO addressDao = new AddressDAO();
		List<Address> addressList = addressDao.getAllAddress() ;
		return addressList;	
	}


    
    
    @Path("getFarmers/{userId}")
   	@GET
   	@Produces(MediaType.APPLICATION_JSON)
   	public List<User> getFarmers(@PathParam("userId") String qualification){
    	UserDAO userDao = new UserDAO();
   		List<User> users =  userDao.getFarmers(qualification);
   		return users;
   		
   	}
    
    
    @Path("getFarms/{addressId}")
   	@GET
   	@Produces(MediaType.APPLICATION_JSON)
   	public List<Farm> getFarms(@PathParam("addressId") int addressId){
    	FarmDAO farmdetailsDao = new FarmDAO();
    	List<Farm> farmdetails = farmdetailsDao.getFarmDetailsById(addressId);
   		return farmdetails;
   		
   	}
    @Path("updateEmp")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmp(Farm farm){
    	System.out.println("data");
    	System.out.println("Data recieved in update"+farm);
    }
    
   //consumer
    @Path("sendsms/{userId}")
   	@GET
   	@Produces(MediaType.APPLICATION_JSON)
    public void viewProfile1(@PathParam("userId") int userId){
		AddressDAO userDao = new AddressDAO();
		Address users= userDao.viewProfile1(userId);
   		smssending(users.getUser());
   	}
    public void smssending(User user) {
    	try {
			// Construct data
			
			String number = user.getMobileNumber();
			System.out.println("into the method" + number);
			String apiKey = "apikey=" + "1Da7j+g0ga8-LdgD7Ijw0CzCT6cxCIiTPxxrZl9o4q	";
			String message = "&message=" + "Your farm has been succesfully uploaded!";
			
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + number;
			
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
				stringBuffer.append(line);
				
			JOptionPane.showMessageDialog(null,"meassage" + line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			//return "Error "+e;
			JOptionPane.showMessageDialog(null,e);
		}
		// TODO Auto-generated method stub
		
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
    @Path("send/{userId}")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
   public String viewProfileo(@PathParam("userId") int userId){
	   int randomPin   =(int) (Math.random()*9000)+1000; 
       String otp  = String.valueOf(randomPin);
       System.out.println(otp);
      
		AddressDAO userDao = new AddressDAO();
		Address users= userDao.viewProfile1(userId);
		String Mobile = users.getUser().getMobileNumber();
		otpsending(Mobile,otp);
  		return otp;
  	
  	}
   public void otpsending(String Mobile, String otp) {
   	try {
			// Construct data
   		
			System.out.println("hi" + Mobile);
			String apiKey = "apikey=" + "1Da7j+g0ga8-LdgD7Ijw0CzCT6cxCIiTPxxrZl9o4q";
			String message = "&message=" + otp ;
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
		
		// TODO Auto-generated method stub
   	
	}
   //count
   
   @Path("getCount")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public long getCount(){
   	UserDAO userDao = new UserDAO();
  		long userCount = userDao.getCount();
  		return userCount;
  		
  	}
   @Path("getCountF")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public long getCountF(){
   	UserDAO userDao = new UserDAO();
  		long userCountF = userDao.getCountF();
  		return userCountF;
  		
  	}
   @Path("getCountC")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public long getCountC(){
   	UserDAO userDao = new UserDAO();
  		long userCountC = userDao.getCountC();
  		return userCountC;
  		
  	}
   @Path("getCountY")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public long getCountY(){
   	FarmDAO yieldDao = new FarmDAO();
  		long userCountY = yieldDao.getCountY();
  		return userCountY;
  		
  	}
   
   @Path("getCountYA")
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	public long getCountYA(){
   	FarmDAO yieldDao = new FarmDAO();
  		long userCountYA = yieldDao.getCountYA();
  		return userCountYA;
  		
  	}

    	
}
