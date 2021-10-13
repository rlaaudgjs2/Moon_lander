package moon_lander;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseData {
    private static FirebaseDefaultCtrl firebaseDefaultCtrl = null;

    public class FirebaseDefaultCtrl {
        Firestore db;
        Create create;

        public void init() throws IOException {
            try {
                InputStream serviceAccount = new FileInputStream("login-aa8b5-firebase-adminsdk-6ohb9-a96e5e5aa8.json");
                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(credentials)
                        .build();
                FirebaseApp.initializeApp(options);
                db = FirestoreClient.getFirestore();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public FirebaseData() {
        if (firebaseDefaultCtrl == null) {
            try {
                firebaseDefaultCtrl = new FirebaseDefaultCtrl();

                firebaseDefaultCtrl.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void InputDBData(String id, String password) {
        if (firebaseDefaultCtrl != null) {
            try {
                DocumentReference docRef = firebaseDefaultCtrl.db.collection("Login").document(id);

                final String finalId = id;
                final String finalPassword = password;

                Map<String, Object> data = new HashMap<String, Object>() {{
                    put("id", finalId);
                    put("pw", finalPassword);
//                    put("score",0);
                }};

                ApiFuture<WriteResult> result = docRef.set(data);

                System.out.println("Update time : " + result.get().getUpdateTime());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void InputRankingData(String id, String password ,String score) {
        if (firebaseDefaultCtrl != null) {
            try {
                DocumentReference docRef = firebaseDefaultCtrl.db.collection("Login").document(id);

                final String finalId = id;
                final String finalPassword = password;
                final String finalScore = score;

                Map<String, Object> data = new HashMap<String, Object>() {{
                    put("id", finalId);
                    put("pw", finalPassword);
//                    put("score", finalScore);
                }};

                ApiFuture<WriteResult> result = docRef.set(data);

                System.out.println("Update time : " + result.get().getUpdateTime());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean CheckIsIdIsExists(String id) {
        boolean result = false;
        if (firebaseDefaultCtrl != null) {
            try {
                DocumentReference docRef = firebaseDefaultCtrl.db.collection("Login").document(id);

                ApiFuture<DocumentSnapshot> getApiFuture = docRef.get();
                DocumentSnapshot documentSnapshot = getApiFuture.get();

                if (documentSnapshot.exists()) {
                    System.out.println("Success");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Window();
                        }
                    });

                    result = true;
                } else {
                    System.out.println("is not");
                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return result;
    }
    public boolean CheckIsIdPwIsExists(String id, String pw) {
        boolean result = false;
        if (firebaseDefaultCtrl != null) {
            try {
                DocumentReference docRefid = firebaseDefaultCtrl.db.collection("Login").document(id);


                ApiFuture<DocumentSnapshot> getApiFuture = docRefid.get();
                DocumentSnapshot documentSnapshotid = getApiFuture.get();

                if (documentSnapshotid.exists()) {
                    System.out.println("Success");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Window();
                        }
                    });

                    result = true;
                } else {
                    System.out.println("is not");
                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return result;
    }



}
