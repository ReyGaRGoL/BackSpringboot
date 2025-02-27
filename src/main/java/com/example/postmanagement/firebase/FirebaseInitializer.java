package com.example.postmanagement.firebase;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitializer {
	
	@PostConstruct
	public void inifirestore() throws IOException {
		InputStream serviceAccount = getClass().getClassLoader().getSystemResourceAsStream("key.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://base.firebaseio.com/")
				  .build();

				if (FirebaseApp.getApps().isEmpty()) {
					FirebaseApp.initializeApp(options);
				}
				
	}
	
	public Firestore getFirestore() {
		return FirestoreClient.getFirestore();
	}

}
