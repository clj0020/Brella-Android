package com.madmensoftware.com.features.detail.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.madmensoftware.com.R;
import com.madmensoftware.com.util.QrCodeHelper;

/**
 * Created by clj00 on 8/22/2017.
 */

public class QRCodeDialog extends DialogFragment {

    String mKey;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static QRCodeDialog newInstance(String key) {
        QRCodeDialog f = new QRCodeDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("key", key);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKey = getArguments().getString("key");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_qr_code, container, false);


        View qrCodeImageView = v.findViewById(R.id.dialog_image_qr_code);

        QrCodeHelper qrCodeHelper = new QrCodeHelper();

        try {
            Bitmap bitmap = qrCodeHelper.encodeAsBitmap(mKey);
            ((ImageView) qrCodeImageView).setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return v;
    }


}
