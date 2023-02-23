package com.example.jobtes.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobtes.Model.Member;
import com.example.jobtes.R;
import com.example.jobtes.View.AdminContract;

import java.util.ArrayList;
import java.util.List;

public class ViewMemberAdapter extends RecyclerView.Adapter<ViewMemberAdapter.ViewHolder> {

    private List<Member> members;
    private AdminContract.Presenter presenter;

    public ViewMemberAdapter(AdminContract.Presenter presenter) {
        this.presenter = presenter;
        members = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMemberAdapter.ViewHolder holder, int position) {
        Member member = members.get(position);
        holder.tvKode.setText(String.valueOf(member.getKode_member()));
        holder.tvNama.setText(member.getNama());
        holder.tvTanggalLahir.setText(member.getTanggal_lahir());
        holder.tvAlamat.setText(member.getAlamat());
        holder.tvJenisKelamin.setText(member.getJenis_kelamin());
        holder.tvUsername.setText(member.getUsername());
        holder.tvPassword.setText(member.getPassword());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setMembers(List<Member> members) {
        this.members.clear();
        this.members.addAll(members);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvKode, tvNama, tvTanggalLahir, tvAlamat, tvJenisKelamin, tvUsername, tvPassword;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKode = itemView.findViewById(R.id.tv_kode_member);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTanggalLahir = itemView.findViewById(R.id.tv_tanggal_lahir);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJenisKelamin = itemView.findViewById(R.id.tv_jenis_kelamin);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvPassword = itemView.findViewById(R.id.tv_password);

            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
