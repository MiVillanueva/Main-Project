package com.example.mainproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {



    EditText btnClass;
    private DialogListener listener;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
         View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add Class Name")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {







                    }
                });

        btnClass = view.findViewById(R.id.btnClass);






        return builder.create();
    }



    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
        listener = (DialogListener) context;

    }catch (ClassCastException e) {

throw new ClassCastException(context.toString() + "must be implemented");
        }
        }





    public interface DialogListener{
       void  applyTexts(String input);






}

    }




