package com.app.personalhealthmonitor.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.personalhealthmonitor.R;
import com.app.personalhealthmonitor.model.ApointementInformation;
import com.app.personalhealthmonitor.model.Doctor;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ConfirmedAppointments  extends FirestoreRecyclerAdapter<AppointmentInfo, ConfirmedAppointments.ConfirmedAppointmentsHolder> {
        StorageReference pathReference ;
public ConfirmedAppointments(@NonNull FirestoreRecyclerOptions<AppointmentInfo> options) {
        super(options);
        }
@Override
protected void onBindViewHolder(@NonNull ConfirmedAppointmentsHolder confirmedAppointmentsHolder, int position, @NonNull final AppointmentInfo AppointmentInfo) {
        confirmedAppointmentsHolder.AppointmentDate.setText(AppointmentInfo.getTime());
        confirmedAppointmentsHolder.PatientName.setText(AppointmentInfo.getPatientName());
        confirmedAppointmentsHolder.AppointmentType.setText(AppointmentInfo.getAppointmentType());

        String imageId = AppointmentInfo.getPatientId()+".jpg"; //add a title image
        pathReference = FirebaseStorage.getInstance().getReference().child("DoctorProfile/"+ imageId); //store the image
        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
@Override
public void onSuccess(Uri uri) {
        Picasso.with(confirmedAppointmentsHolder.PatientImage.getContext())
        .load(uri)
        .placeholder(R.mipmap.ic_launcher)
        .fit()
        .centerCrop()
        .into(confirmedAppointmentsHolder.PatientImage);//Image location

        // profileImage.setImageURI(uri);
        }
        }).addOnFailureListener(new OnFailureListener() {
@Override
public void onFailure(@NonNull Exception exception) {
        // Handle any errors
        }
        });

        }

@NonNull
@Override
public ConfirmedAppointments.ConfirmedAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmed_appointments_item, parent, false);
        return new ConfirmedAppointments.ConfirmedAppointmentsHolder(v);
        }

class ConfirmedAppointmentsHolder extends RecyclerView.ViewHolder{
    TextView AppointmentDate;
    TextView PatientName;
    TextView AppointmentType;
    ImageView PatientImage;
    public ConfirmedAppointmentsHolder(@NonNull View itemView) {
        super(itemView);
        AppointmentDate = itemView.findViewById(R.id.appointment_date);
        PatientName = itemView.findViewById(R.id.patient_name);
        AppointmentType = itemView.findViewById(R.id.appointment_type);
        PatientImage = itemView.findViewById(R.id.patient_image);
    }
}
}
