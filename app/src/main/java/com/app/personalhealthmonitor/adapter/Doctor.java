package com.app.personalhealthmonitor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.personalhealthmonitor.AppointmentActivity;
import com.app.personalhealthmonitor.ChatActivity;
import com.app.personalhealthmonitor.Common.Common;
import com.app.personalhealthmonitor.HomeActivity;
import com.app.personalhealthmonitor.R;
import com.app.personalhealthmonitor.SearchPatActivity;
import com.app.personalhealthmonitor.TestActivity;
import com.app.personalhealthmonitor.model.Doctor;
import com.app.ui.firestore.FirestoreRecyclerAdapter;
import com.app.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Doctor extends FirestoreRecyclerAdapter<Doctor, Doctor.DoctorHolder> {
    static String doc;
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference addRequest = db.collection("Request");

    public Doctor(@NonNull FirestoreRecyclerOptions<Doctor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final DoctorHolder doctorHolder, int i, @NonNull final Doctor doctor) {
        final TextView t = doctorHolder.title ;
        doctorHolder.title.setText(doctor.getName());
        doctorHolder.speciality.setText("Speciality : "+doctor.getSpeciality());
        final String idPat = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        final String idDoc = doctor.getEmail();
        // doctorHolder.image.setImageURI(Uri.parse("drawable-v24/ic_launcher_foreground.xml"));
        doctorHolder.addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> note = new HashMap<>();
                note.put("id_patient", idPat);
                note.put("id_doctor", idDoc);
                addRequest.document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Snackbar.make(t, "Demande envoyée", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                doctorHolder.addDoc.setVisibility(View.INVISIBLE);
            }
        });
        doctorHolder.appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc= doctor.getEmail();
                Common.CurreentDoctor = doctor.getEmail();
                openPage(v.getContext());

            }
        });

    }


    @NonNull
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,
                parent, false);
        return new DoctorHolder(v);
    }


    class DoctorHolder extends RecyclerView.ViewHolder {
        Button appointmentBtn;
        TextView title;
        TextView speciality;
        ImageView image;
        Button addDoc;
        Button load;
        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            addDoc = itemView.findViewById(R.id.addDocBtn);
            title= itemView.findViewById(R.id.doctor_view_title);
            speciality=itemView.findViewById(R.id.text_view_description);
            image=itemView.findViewById(R.id.doctor_item_image);
            appointmentBtn=itemView.findViewById(R.id.appointmentBtn);
        }
    }
    private void openPage(Context wf){
        Intent i = new Intent(wf, TestActivity.class);
        wf.startActivity(i);
    }


}

