package moon_lander;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FireBase {
    Firestore db;

    public void init() throws IOException {
        try {
            InputStream serviceAccount = new FileInputStream("Moonlander.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();

        }
        
        
    }
//    public void get() {
//    	// asynchronously retrieve all users
//    	ApiFuture<QuerySnapshot> query = db.collection("users").get();
//    	// ...
//    	// query.get() blocks on response
//    	QuerySnapshot querySnapshot = query.get();
//    	List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//    	for (QueryDocumentSnapshot document : documents) {
//    	  System.out.println("User: " + document.getId());
//    	  System.out.println("First: " + document.getString("first"));
//    	  if (document.contains("middle")) {
//    	    System.out.println("Middle: " + document.getString("middle"));
//    	  }
//    	  System.out.println("Last: " + document.getString("last"));
//    	  System.out.println("Born: " + document.getLong("born"));
//    	}
//    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
    	FireBase firebase = new FireBase();
    	firebase.init();
        DocumentReference docRef = firebase.db.collection("test").document("user");
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("id", "admin22");
        data.put("pw", "zxc");
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);	

        System.out.println("Update time : " + result.get().getUpdateTime());
    }


}
