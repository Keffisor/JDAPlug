package com.Keffisor21.Requests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    public String Address;
    public int ConnectTimeout;
    public int ReadWriteTimeout;
    public List<String> Cookies = new ArrayList<>();
    public String Referer = null;
    public String ContentType = null;
    public String UserAgent = null;
    public boolean KeepAlive = false;
    public boolean IgnoreProtocolErrors = true;
    private HashMap<String, String> headers = new HashMap<>();

    public HttpRequest() {
   
    }
    
    public Object Post(String URL, String toPost) {
        try {

                        URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			byte[] payloadAsBytes = toPost.getBytes(Charset.forName("UTF-8"));
			
                        
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadWriteTimeout);
                        
                   
 			conn.setRequestMethod("POST");
                        if(ContentType != null) {
			conn.addRequestProperty("Content-Type", ContentType);
                        }
                        if(Referer != null) {
                        conn.addRequestProperty("Referer", Referer);
                        }
                        
                        conn.setInstanceFollowRedirects(KeepAlive);
                     
                        if(!Cookies.isEmpty()) {
                        for(String cookie : Cookies) {
                        conn.addRequestProperty("Cookie", cookie+";");
                        }
                        }
                        if(UserAgent != null) {
                            conn.addRequestProperty("User-Agent", UserAgent);
                        }
                        if(!headers.isEmpty()) {
                      for(Map.Entry<String, String> entry : headers.entrySet()) {
                           conn.addRequestProperty(entry.getKey(), entry.getValue());
                           
                      }
                        }
                        
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
 			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			outStream.write(payloadAsBytes);
			outStream.flush();

                        outStream.close();
                        
                  
			
			InputStream inStream;
			try {
				inStream = conn.getInputStream();
                                
			} catch (Exception e) {
				inStream = conn.getErrorStream();
			}
			
			StringBuilder response = new StringBuilder();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inStream.read(buffer)) > 0) {
				response.append(new String(buffer,"UTF-8").substring(0,bytesRead));
                                  
			}
 
			return response.toString();
			      
                                  
                                  
		} catch (IOException e) {
                    if(IgnoreProtocolErrors) {
			System.out.println(e);
                    }
		}
		return null;
	}
    public Object Get(String URL) {
        try {

                        URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
  
   
			
                        
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadWriteTimeout);
                        
                   
 			conn.setRequestMethod("GET");
                        if(ContentType != null) {
			conn.addRequestProperty("Content-Type", ContentType);
                        }
                        if(Referer != null) {
                        conn.addRequestProperty("Referer", Referer);
                        }
                        
                        conn.setInstanceFollowRedirects(KeepAlive);
                     
                        if(!Cookies.isEmpty()) {
                        for(String cookie : Cookies) {
                        conn.addRequestProperty("Cookie", cookie+";");
                        }
                        }
                        if(UserAgent != null) {
                            conn.addRequestProperty("User-Agent", UserAgent);
                        }
                        if(!headers.isEmpty()) {
                      for(Map.Entry<String, String> entry : headers.entrySet()) {
                           conn.addRequestProperty(entry.getKey(), entry.getValue());
                           
                      }
                        }
                        
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
 			 

                       
                        
                  
			
			InputStream inStream;
			try {
				inStream = conn.getInputStream();
                                
			} catch (Exception e) {
				inStream = conn.getErrorStream();
			}
			
			StringBuilder response = new StringBuilder();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inStream.read(buffer)) > 0) {
				response.append(new String(buffer,"UTF-8").substring(0,bytesRead));
                                  
			}
 
			return response.toString();
			      
                                  
                                  
		} catch (IOException e) {
                    if(IgnoreProtocolErrors) {
			System.out.println(e);
                    }
		}
		return null;
	}

    public void addHeader(String a, String b) {
        headers.put(a, b);
    }
   /* public static void main(String[] args) {
         HttpRequest request = new HttpRequest();
     System.out.println(request.GetProxy("http://checkip.amazonaws.com/", "121.40.66.129:1080", Proxy.Type.SOCKS));
    }*/
   public Object Get(String URL, String proxy, Proxy.Type type) {
        try {
  String[] pf = proxy.split(":");
  String p1 = pf[0];
  int p2 = Integer.parseInt(pf[1]);
                        URL url = new URL(URL);
                        Proxy p = new Proxy(type, new InetSocketAddress(p1, p2));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection(p);
  
                   if(type == Proxy.Type.SOCKS) {
			 System.getProperties().put( "proxySet", "true" );
                         System.getProperties().put( "socksProxyHost", p1 );
                         System.getProperties().put( "socksProxyPort", p2 );
                   }
                        
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadWriteTimeout);
                        
                   
 			conn.setRequestMethod("GET");
                        if(ContentType != null) {
			conn.addRequestProperty("Content-Type", ContentType);
                        }
                        if(Referer != null) {
                        conn.addRequestProperty("Referer", Referer);
                        }
                        
                        conn.setInstanceFollowRedirects(KeepAlive);
                     
                        if(!Cookies.isEmpty()) {
                        for(String cookie : Cookies) {
                        conn.addRequestProperty("Cookie", cookie+";");
                        }
                        }
                        if(UserAgent != null) {
                            conn.addRequestProperty("User-Agent", UserAgent);
                        }
                        if(!headers.isEmpty()) {
                      for(Map.Entry<String, String> entry : headers.entrySet()) {
                           conn.addRequestProperty(entry.getKey(), entry.getValue());
                           
                      }
                        }
                        
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
 			 

                       
                        
                  
			
			InputStream inStream;
			try {
				inStream = conn.getInputStream();
                                
			} catch (Exception e) {
				inStream = conn.getErrorStream();
			}
			
			StringBuilder response = new StringBuilder();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inStream.read(buffer)) > 0) {
				response.append(new String(buffer,"UTF-8").substring(0,bytesRead));
                                  
			}
 
			return response.toString();
			      
                                  
                                  
		} catch (IOException e) {
                    if(IgnoreProtocolErrors) {
			System.out.println(e);
                    }
		}
		return null;
	}
   public Object Post(String URL, String toPost, String proxy, Proxy.Type type) {
        try {

                      String[] pf = proxy.split(":");
  String p1 = pf[0];
  int p2 = Integer.parseInt(pf[1]);
                        URL url = new URL(URL);
                        Proxy p = new Proxy(type, new InetSocketAddress(p1, p2));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection(p);
			byte[] payloadAsBytes = toPost.getBytes(Charset.forName("UTF-8"));
			
                        
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadWriteTimeout);
                        
                        if(type == Proxy.Type.SOCKS) {
			 System.getProperties().put( "proxySet", "true" );
                         System.getProperties().put( "socksProxyHost", p1 );
                         System.getProperties().put( "socksProxyPort", p2 );
                   }
                   
 			conn.setRequestMethod("POST");
                        if(ContentType != null) {
			conn.addRequestProperty("Content-Type", ContentType);
                        }
                        if(Referer != null) {
                        conn.addRequestProperty("Referer", Referer);
                        }
                        
                        conn.setInstanceFollowRedirects(KeepAlive);
                     
                        if(!Cookies.isEmpty()) {
                        for(String cookie : Cookies) {
                        conn.addRequestProperty("Cookie", cookie+";");
                        }
                        }
                        if(UserAgent != null) {
                            conn.addRequestProperty("User-Agent", UserAgent);
                        }
                        if(!headers.isEmpty()) {
                      for(Map.Entry<String, String> entry : headers.entrySet()) {
                           conn.addRequestProperty(entry.getKey(), entry.getValue());
                           
                      }
                        }
                        
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
 			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			outStream.write(payloadAsBytes);
			outStream.flush();

                        outStream.close();
                        
                  
			
			InputStream inStream;
			try {
				inStream = conn.getInputStream();
                                
			} catch (Exception e) {
				inStream = conn.getErrorStream();
			}
			
			StringBuilder response = new StringBuilder();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inStream.read(buffer)) > 0) {
				response.append(new String(buffer,"UTF-8").substring(0,bytesRead));
                                  
			}
 
			return response.toString();
			      
                                  
                                  
		} catch (IOException e) {
                    if(IgnoreProtocolErrors) {
			System.out.println(e);
                    }
		}
		return null;
	}

    }
