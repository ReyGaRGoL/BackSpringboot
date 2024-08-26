package com.example.postmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postmanagement.DTO.PostDTO;
import com.example.postmanagement.firebase.FirebaseInitializer;
import com.example.postmanagement.service.PostManagementService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@Service
public class PostManagementServiceImpl implements PostManagementService {

	@Autowired
	private FirebaseInitializer firebase;

	@Override
	public List<PostDTO> list() {

		List<PostDTO> response = new ArrayList<>();
		PostDTO post;

		ApiFuture<QuerySnapshot> apiFuture = collection().get();

		try {
			for (DocumentSnapshot document : apiFuture.get().getDocuments()) {
				post = document.toObject(PostDTO.class);
				post.setId(document.getId());
				response.add(post);

			}
			return response;
		} catch (Exception e) {
			return null;

		}

	}

	@Override
	public boolean add(PostDTO post) {

		// guarda los datos en un map
		Map<String, Object> docData = mapear(post);

		ApiFuture<WriteResult> apiFuture = collection().document().create(docData);

		try {
			if (null != apiFuture.get()) {
				return true;
			}
			return false;
		} catch (InterruptedException | ExecutionException e) {
			return false;

		}

	}

	private CollectionReference collection() {
		return firebase.getFirestore().collection("post");
	}

	@Override
	public boolean edit(String id, PostDTO post) {

		Map<String, Object> docData = mapear(post);

		ApiFuture<WriteResult> apiFuture = collection().document(id).set(docData);

		try {
			if (null != apiFuture.get()) {
				return true;
			}
			return false;
		} catch (InterruptedException | ExecutionException e) {
			return false;

		}

	}

	private Map<String, Object> mapear(PostDTO post) {

		// Guarda los datos en un map
		Map<String, Object> docData = new HashMap<>();
		docData.put("title", post.getTitle());
		docData.put("content", post.getContent());
		return docData;
	}

	@Override
	public boolean delete(String id) {

		ApiFuture<WriteResult> apiFuture = collection().document(id).delete();

		try {
			if (null != apiFuture.get()) {
				return true;
			}
			return false;
		} catch (InterruptedException | ExecutionException e) {
			return false;

		}


	}

	@Override
	public PostDTO findById(String id) {
		
		ApiFuture<DocumentSnapshot> apiFuture= collection().document(id).get();
		PostDTO dto;
		try {
			dto = apiFuture.get().toObject(PostDTO.class);
			if (dto==null) {
				return null;
			}
			dto.setId(id);
			return dto;
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			
			return null;
			
		}
		
		
	}

}
