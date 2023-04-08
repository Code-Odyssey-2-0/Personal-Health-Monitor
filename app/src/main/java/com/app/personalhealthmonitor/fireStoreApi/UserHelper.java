package com.app.personalhealthmonitor.fireStoreApi;

import android.widget.Toast;

import com.app .personalhealthmonitor.FirstSigninActivity;
import com.app.personalhealthmonitor.MainActivity;
import com.app.personalhealthmonitor.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference UsersRef = db.collection("User");

    public static void addUser(String name, String adresse, String tel,String type){
        User user = new User(name,adresse,tel,FirebaseAuth.getInstance().getCurrentUser().getEmail(),type);
        UsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(user);

    }
}
