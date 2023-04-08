package com.example.proyectofinal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private int espacio;

    public CustomItemDecoration(int espacio) {
        this.espacio = espacio;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // Establecer el espacio horizontal deseado para los elementos del RecyclerView
        outRect.right = espacio;

        // Establecer el espacio del lado izquierdo del primer elemento a la mitad del espacio horizontal
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = espacio / 2;
        }

        // Establecer el espacio del lado derecho del último elemento a la mitad del espacio horizontal
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.right = espacio / 2;
        }
    }
}

