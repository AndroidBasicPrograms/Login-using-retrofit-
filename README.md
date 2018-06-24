# Login-using-retrofit

PHP service for login

add retrofit dependency

     compile  ‘com.squareup.retrofit2:retrofit:2.4.0’

add  gson convert factory dependency

     compile  ‘com.squareup.retrofit2:converter-gson:2.0.2’
     
create java class--apiclient class

create interface class--apiService  

apiclient.java

      create ratroit obj and create method for intialize it
      
apiservice.java

      create method for perform activity(login)
      
      @forUrlEncoded	
      @POST("pagename.php")
      Call<String> method name(parameters)

Activity

      -->create obj of apiservice
      -->intialize it with apiclient.getclient().create(apiservice.class)
      -->create obj of Call<String> call=obj of service.methodname();
      -->call.enque(new ..)
      -->for background process
      -->onresponse method()
      {
      take result into localvriable 
      call function(pass result)
      }	
