package com.agotsulov.imagon.magic;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.agotsulov.imagon.ImagonException;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.IOException;

import static com.agotsulov.imagon.utils.Utils.assetFilePath;

public class Model {

    private Module module;
    private Vocabulary vocabulary;

    public Model(Context context, Vocabulary vocabulary) throws ImagonException {
        try {
            module = Module.load(assetFilePath(context, "model.pt"));
        } catch (IOException e) {
            throw new ImagonException();
        }
        this.vocabulary = vocabulary;
        Log.i("Model","MODEL LOADED");

    }

    public String forward(Bitmap bitmap) {
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap,
                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);

        Log.i("Model","FORWARD START");

        final Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();

        final long[] ids = outputTensor.getDataAsLongArray();

        String result = this.vocabulary.idxToWords(ids);

        Log.i("Model","FORWARD END");
        return result;
    }
}
