package com.example.proyectofinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {

    private List<Anuncio> mAnuncios;

    public AnunciosAdapter(List<Anuncio> anuncios) {
        mAnuncios = anuncios;
    }

    public interface OnAnuncioClickListener {
        void onAnuncioClick(Anuncio anuncio);
    }

    private OnAnuncioClickListener mListener;

    public void setOnAnuncioClickListener(OnAnuncioClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anuncio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anuncio anuncio = mAnuncios.get(position);
        holder.bind(anuncio);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAnuncioClick(anuncio);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAnuncios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNombreTextView;
        private TextView mDireccionTextView;
        private TextView mTipoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mNombreTextView = itemView.findViewById(R.id.nombreTextView);
            mDireccionTextView = itemView.findViewById(R.id.direccionTextView);
            mTipoTextView = itemView.findViewById(R.id.tipoTextView);
        }

        public void bind(Anuncio anuncio) {
            mNombreTextView.setText(anuncio.getNombre());
            mDireccionTextView.setText(anuncio.getDireccion());
            mTipoTextView.setText(anuncio.getTipo());

            byte[] imageBytes = Base64.decode(anuncio.getBase64(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            mImageView.setImageBitmap(bitmap);



        }
    }

}
