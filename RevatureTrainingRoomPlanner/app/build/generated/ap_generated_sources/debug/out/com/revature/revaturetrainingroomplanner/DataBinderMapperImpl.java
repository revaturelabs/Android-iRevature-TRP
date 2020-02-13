package com.revature.revaturetrainingroomplanner;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_BATCHROW = 1;

  private static final int LAYOUT_ROOMROW = 2;

  private static final int LAYOUT_TRAINERROW = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.batch_row, LAYOUT_BATCHROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.room_row, LAYOUT_ROOMROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.trainer_row, LAYOUT_TRAINERROW);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_BATCHROW: {
          if ("layout/batch_row_0".equals(tag)) {
            return new BatchRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for batch_row is invalid. Received: " + tag);
        }
        case  LAYOUT_ROOMROW: {
          if ("layout/room_row_0".equals(tag)) {
            return new RoomRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for room_row is invalid. Received: " + tag);
        }
        case  LAYOUT_TRAINERROW: {
          if ("layout/trainer_row_0".equals(tag)) {
            return new TrainerRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for trainer_row is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "model");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/batch_row_0", com.revature.revaturetrainingroomplanner.R.layout.batch_row);
      sKeys.put("layout/room_row_0", com.revature.revaturetrainingroomplanner.R.layout.room_row);
      sKeys.put("layout/trainer_row_0", com.revature.revaturetrainingroomplanner.R.layout.trainer_row);
    }
  }
}
